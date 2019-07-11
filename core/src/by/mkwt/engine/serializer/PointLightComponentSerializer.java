package by.mkwt.serializer;

import box2dLight.RayHandler;
import by.mkwt.engine.component.graphic.PointLightComponent;
import by.mkwt.engine.component.graphic.TransformComponent;
import by.mkwt.engine.util.CMHolder;
import by.mkwt.engine.util.LightBuilder;
import by.mkwt.tiled.TiledEntity;
import by.mkwt.tiled.serializer.ComponentSerializer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class PointLightComponentSerializer extends ComponentSerializer implements Json.Serializer<PointLightComponent> {

    private TiledEntity entity;
    private RayHandler rayHandler;

    public PointLightComponentSerializer(RayHandler rayHandler) {
        this.rayHandler = rayHandler;
    }

    @Override
    public void setEntity(TiledEntity entity) {
        this.entity = entity;
    }

    @Override
    public void write(Json json, PointLightComponent object, Class knownType) {
        json.writeObjectStart();
        json.writeValue("r", object.pointLight.getColor().r);
        json.writeValue("g", object.pointLight.getColor().g);
        json.writeValue("b", object.pointLight.getColor().b);
        json.writeValue("a", object.pointLight.getColor().a);
        json.writeValue("dist", object.pointLight.getDistance());
        json.writeObjectEnd();
    }

    @Override
    public PointLightComponent read(Json json, JsonValue jsonData, Class type) {
        MapObject mapObject = entity.getMapObject();
        TransformComponent transformComponent = CMHolder.transform.get(entity);

        PointLightBlank blank = json.fromJson(PointLightBlank.class, (String) mapObject.getProperties().get("PointLightComponent"));
        blank.x = transformComponent.position.x;
        blank.y = transformComponent.position.y;
        PointLightComponent pointLightComponent = new PointLightComponent();
        pointLightComponent.pointLight = LightBuilder.createPointLight(rayHandler, blank.x, blank.y, new Color(blank.r, blank.g, blank.b, blank.a), blank.dist);
        entity.add(pointLightComponent);

        return pointLightComponent;
    }

    public static class PointLightBlank {
        public float x;
        public float y;
        public float r;
        public float g;
        public float b;
        public float a;
        public float dist;
    }
}
