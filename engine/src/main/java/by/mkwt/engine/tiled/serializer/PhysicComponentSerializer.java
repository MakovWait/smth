package by.mkwt.engine.tiled.serializer;

import by.mkwt.engine.util.CoreCMHolder;
import by.mkwt.engine.ecs.component.graphic.TransformComponent;
import by.mkwt.engine.ecs.component.physic.PhysicComponent;
import by.mkwt.engine.tiled.TiledEntity;
import by.mkwt.engine.util.CoordsConverter;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class PhysicComponentSerializer extends ComponentSerializer implements Json.Serializer<PhysicComponent> {

    private TiledEntity entity;
    private World world;

    public PhysicComponentSerializer(World world){
        this.world = world;
    }

    @Override
    public void write(Json json, PhysicComponent object, Class knownType) {
        json.writeObjectStart();
        TransformComponent transformComponent = CoreCMHolder.transform.get((Entity) object.body.getUserData());

        if (!object.isRectangleMapObject) {
//            json.writeValue("x", CoordsConverter.MetersToPixels(transformComponent.position.x - transformComponent.offset.x));
//            json.writeValue("y", CoordsConverter.MetersToPixels(transformComponent.position.y - transformComponent.offset.y));
            json.writeValue("offsetX", CoordsConverter.MetersToPixels(transformComponent.offset.x));
            json.writeValue("offsetY", CoordsConverter.MetersToPixels(transformComponent.offset.y));
            json.writeValue("width", CoordsConverter.MetersToPixels(object.size.x));
            json.writeValue("height", CoordsConverter.MetersToPixels(object.size.y));
            json.writeValue("angle", object.body.getTransform().getRotation() * MathUtils.radDeg);
        }

        json.writeValue("type", object.body.getType().name());
        json.writeValue("isSensor", object.body.getFixtureList().get(0).isSensor());
        json.writeValue("isFixedRotation", object.body.isFixedRotation());
        json.writeValue("density", object.body.getFixtureList().get(0).getDensity());
        json.writeValue("friction", object.body.getFixtureList().get(0).getFriction());
        json.writeValue("restitution", object.body.getFixtureList().get(0).getRestitution());
        json.writeObjectEnd();
    }

    @Override
    public PhysicComponent read(Json json, JsonValue jsonData, Class type) {
        TransformComponent transformComponent = CoreCMHolder.transform.get(entity);
        MapObject mapObject = entity.getMapObject();

        PhysicBodyBlank blank = json.fromJson(PhysicBodyBlank.class, (String) mapObject.getProperties().get("PhysicComponent"));
        transformComponent.offset.set(CoordsConverter.pixelsToMeters(blank.offsetX), CoordsConverter.pixelsToMeters(blank.offsetY));
        PhysicComponent physicComponent = new PhysicComponent();

        blank.width = CoordsConverter.pixelsToMeters(blank.width);
        blank.height = CoordsConverter.pixelsToMeters(blank.height);

        if (mapObject instanceof RectangleMapObject) {
            blank.x = transformComponent.position.x;
            blank.y = transformComponent.position.y;
            blank.width = transformComponent.size.x;
            blank.height = transformComponent.size.y;
            blank.offsetX = 0;
            blank.offsetY = 0;
            blank.angle = transformComponent.rotation;
            physicComponent.isRectangleMapObject = true;
        }

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = blank.type;

        bodyDef.position.set(
                transformComponent.position.x + transformComponent.offset.x + blank.width / 2f,
                transformComponent.position.y + transformComponent.offset.y + blank.height / 2f
        );

        bodyDef.angle = blank.angle * MathUtils.degRad;

        bodyDef.linearDamping = 10f;

        Body body = world.createBody(bodyDef);

        PolygonShape polygon = new PolygonShape();

        polygon.set(new float[]{
                -blank.width / 2f, blank.height / 2,
                blank.width / 2f, blank.height / 2f,
                blank.width / 2f, -blank.height / 2f,
                -blank.width / 2f, -blank.height / 2f
        });

        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.shape = polygon;
        fixtureDef.density = blank.density;
        fixtureDef.friction = blank.friction;
        fixtureDef.restitution = blank.restitution;
        fixtureDef.isSensor = blank.isSensor;

        body.setFixedRotation(blank.isFixedRotation);

        body.createFixture(fixtureDef);
        body.setUserData(entity);

        physicComponent.body = body;
        physicComponent.size = new Vector2(blank.width, blank.height);

        polygon.dispose();

        return physicComponent;
    }

    public void setEntity(TiledEntity entity) {
        this.entity = entity;
    }

    public static class PhysicBodyBlank {
        public float x = 0;
        public float y = 0;
        public float width = 0;
        public float height = 0;
        public float angle;
        public float offsetX = 0;
        public float offsetY = 0;
        public BodyDef.BodyType type = BodyDef.BodyType.StaticBody;
        public boolean isSensor = false;
        public boolean isFixedRotation = false;
        public float density = 0;
        public float friction = 0;
        public float restitution = 0;
        public String userData = "null";

        @Override
        public String toString() {
            return "PhysicBodyBlank{" +
                    "x=" + x +
                    ", y=" + y +
                    ", width=" + width +
                    ", height=" + height +
                    ", angle=" + angle +
                    ", offsetX=" + offsetX +
                    ", offsetY=" + offsetY +
                    ", type=" + type +
                    ", isSensor=" + isSensor +
                    ", isFixedRotation=" + isFixedRotation +
                    ", density=" + density +
                    ", friction=" + friction +
                    ", restitution=" + restitution +
                    ", userData='" + userData + '\'' +
                    '}';
        }
    }
}
