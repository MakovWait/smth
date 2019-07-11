package by.mkwt.engine.tiled.preprocessor;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;

public class MapPropertiesHandler {

    public enum DefaultProperties {
        id, gid, name, type, x, y, z, width, height, rotation,
    }

    public static void checkDefaultProperties(MapObject checkedObject) {
        for (DefaultProperties property : DefaultProperties.values()) {
            if (property.equals(DefaultProperties.gid) || property.equals(DefaultProperties.type)) {
                //TODO gid (type) property really do not be handled?
                continue;
            }
            if (property.equals(DefaultProperties.name)) {
                if (checkedObject.getName() == null) {
                    checkedObject.getProperties().put(property.toString(), getDefaultPropertyValue(property));
                } else {
                    checkedObject.getProperties().put(property.toString(), checkedObject.getName());
                }
            } else if (checkedObject.getProperties().get(property.toString()) == null) {
                checkedObject.getProperties().put(property.toString(), getDefaultPropertyValue(property));
            }
        }

        setCurrentPositionWithFuckingTiledRotation(checkedObject);
    }

    private static void setCurrentPositionWithFuckingTiledRotation(MapObject checkedObject) {
        float rotation = (float) checkedObject.getProperties().get("rotation");

        float x = (float) checkedObject.getProperties().get("x");
        float y = (float) checkedObject.getProperties().get("y");

        float w = (float) checkedObject.getProperties().get("width");
        float h = (float) checkedObject.getProperties().get("height");

        float offsetX = w / 2f;
        float offsetY = h / 2f;

        Vector2 center = new Vector2();

        if (checkedObject instanceof RectangleMapObject) {
            y += h;
            center.set(x + offsetX, y - offsetY);
        } else {
            center.set(x + offsetX, y + offsetY);
        }

        Vector2 toCenter = new Vector2(center.sub(x, y)).rotate(-rotation);
        Vector2 currentCenter = new Vector2(x, y).add(toCenter);

        x = currentCenter.x - offsetX;
        y = currentCenter.y - offsetY;

        checkedObject.getProperties().put("x", x);
        checkedObject.getProperties().put("y", y);
    }

    private static Object getDefaultPropertyValue(DefaultProperties property) {
        switch (property) {
            case rotation:
                return 0.0f;
            case z:
                return "0.0";
        }
        return null;
    }

}
