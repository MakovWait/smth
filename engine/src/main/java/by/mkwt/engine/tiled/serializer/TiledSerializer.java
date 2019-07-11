package by.mkwt.engine.tiled.serializer;

import box2dLight.RayHandler;
import by.mkwt.engine.util.CoreCMHolder;
import by.mkwt.engine.ecs.component.graphic.LayerComponent;
import by.mkwt.engine.ecs.component.graphic.PointLightComponent;
import by.mkwt.engine.ecs.component.graphic.TextureComponent;
import by.mkwt.engine.ecs.component.graphic.TransformComponent;
import by.mkwt.engine.ecs.component.physic.PhysicComponent;
import by.mkwt.engine.tiled.TiledEntity;
import by.mkwt.engine.tiled.marker.MapPropertyComponent;
import by.mkwt.engine.util.CoordsConverter;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import javax.inject.Inject;
import java.util.Iterator;

public class TiledSerializer {

    private Json json;
    private World world;
    private ObjectMap<String, String> components;
    private RayHandler rayHandler;

//    private PhysicComponentSerializer physicComponentSerializer;
//    private PointLightComponentSerializer pointLightComponentSerializer;

    @Inject
    public TiledSerializer(Json json, World world, RayHandler rayHandler, ObjectMap<String, String> components) {
        this.json = json;
        this.world = world;
        this.rayHandler = rayHandler;
        this.components = components;

        json.setSerializer(PhysicComponent.class, new PhysicComponentSerializer(world));
        json.setSerializer(PointLightComponent.class, new PointLightComponentSerializer(rayHandler));

//        physicComponentSerializer = new PhysicComponentSerializer(world);
//        pointLightComponentSerializer = new PointLightComponentSerializer(rayHandler);
//
//        json.setSerializer(PhysicComponent.class, physicComponentSerializer);
    }

    public void prepare() {
        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);
        bodies.forEach(world::destroyBody);
        rayHandler.removeAll();
    }

    public <T> void setJsonSerializer(Class<T> clazz, Json.Serializer<T> serializer) {
        json.setSerializer(clazz, serializer);
    }

    public TiledEntity parse(MapObject mapObject) {
        TiledEntity entity = new TiledEntity(mapObject);

        if (mapObject.getProperties().get("layer") != null) {
            LayerComponent layerComponent = new LayerComponent();
            layerComponent.layerName = (String) mapObject.getProperties().get("layer");
            entity.add(layerComponent);
        }

        TransformComponent transformComponent = getTransformComponent(entity);
        entity.add(transformComponent);

        for (Iterator<String> it = mapObject.getProperties().getKeys(); it.hasNext(); ) {
            String key = it.next();
            if (components.containsKey(key)) {
                try {
                    Class<? extends Component> clazz = ClassReflection.forName(components.get(key));
                    if (json.getSerializer(clazz) instanceof ComponentSerializer) {
                        ((ComponentSerializer) json.getSerializer(clazz)).setEntity(entity);
                    }
                    entity.add(json.fromJson(clazz, (String) mapObject.getProperties().get(key)));
                } catch (ReflectionException e) {
                    e.printStackTrace();
                }
            }
        }

        return entity;
    }

    public MapObject update(TiledEntity entity) {
        TransformComponent transform = CoreCMHolder.transform.get(entity);
        MapObject mapObject = entity.getMapObject();

        Array<String> updatedProperties = new Array<>();

        mapObject.getProperties().put("x", CoordsConverter.MetersToPixels(transform.position.x));
        mapObject.getProperties().put("y", CoordsConverter.MetersToPixels(transform.position.y));
        mapObject.getProperties().put("width", CoordsConverter.MetersToPixels(transform.size.x * transform.scale.x));
        mapObject.getProperties().put("height", CoordsConverter.MetersToPixels(transform.size.y * transform.scale.y));
        mapObject.getProperties().put("type", "");
        mapObject.getProperties().put("rotation", -transform.rotation);

        if (CoreCMHolder.layer.has(entity)) {
            mapObject.getProperties().put("layer", CoreCMHolder.layer.get(entity).layerName);
        }

        updatedProperties.add("x");
        updatedProperties.add("y");
        updatedProperties.add("width");
        updatedProperties.add("height");
        updatedProperties.add("type");
        updatedProperties.add("rotation");
        updatedProperties.add("z");
        updatedProperties.add("gid");
        updatedProperties.add("id");

        for (Component component : entity.getComponents()) {
            if (component instanceof MapPropertyComponent) {
                mapObject.getProperties().put(component.getClass().getSimpleName(), json.toJson(component));
                updatedProperties.add(component.getClass().getSimpleName());
            }
        }

        mapObject.getProperties().getKeys().forEachRemaining(
                prop -> {
                    if (!updatedProperties.contains(prop, false) && components.containsKey(prop)) {
                        mapObject.getProperties().remove(prop);
                    }
                }
        );

        return mapObject;
    }

    public TransformComponent getTransformComponent(TiledEntity entity) {
        TransformComponent transformComponent = new TransformComponent();
        MapObject mapObject = entity.getMapObject();

        if (mapObject instanceof TiledMapTileMapObject) {
            TextureComponent textureComponent = new TextureComponent();
            textureComponent.region = ((TiledMapTileMapObject) mapObject).getTextureRegion();

            entity.add(textureComponent);

            transformComponent.size = new Vector2(
                    CoordsConverter.pixelsToMeters(textureComponent.region.getRegionWidth()),
                    CoordsConverter.pixelsToMeters(textureComponent.region.getRegionHeight())
            );

            transformComponent.scale.set(
                    (float) mapObject.getProperties().get("width") / textureComponent.region.getRegionWidth(),
                    (float) mapObject.getProperties().get("height") / textureComponent.region.getRegionHeight()
            );

        } else {
            transformComponent.size = new Vector2(
                    CoordsConverter.pixelsToMeters((float) mapObject.getProperties().get("width")),
                    CoordsConverter.pixelsToMeters((float) mapObject.getProperties().get("height"))
            );
        }

        transformComponent.position = new Vector2(
                CoordsConverter.pixelsToMeters((float) mapObject.getProperties().get("x")),
                CoordsConverter.pixelsToMeters((float) mapObject.getProperties().get("y"))
        );

        transformComponent.rotation -= (float) mapObject.getProperties().get("rotation");

        transformComponent.z = Float.valueOf((String) mapObject.getProperties().get("z"));

        transformComponent.origin = new Vector2(
                transformComponent.size.x / 2f,
                transformComponent.size.y / 2f
        );
        return transformComponent;
    }

}
