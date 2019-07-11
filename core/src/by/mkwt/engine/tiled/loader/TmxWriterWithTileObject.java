package by.mkwt.tiled.loader;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.StringBuilder;
import net.dermetfan.gdx.maps.tiled.TmxMapWriter;
import net.dermetfan.gdx.math.GeometryUtils;

import java.io.IOException;
import java.io.Writer;

import static com.badlogic.gdx.math.MathUtils.round;
import static net.dermetfan.gdx.maps.MapUtils.getProperty;

public class TmxWriterWithTileObject extends TmxMapWriter {

    public TmxWriterWithTileObject(Writer writer) {
        super(writer);
    }

    @Override
    public TmxMapWriter tmx(MapObject object) throws IOException {
        MapProperties props = object.getProperties();

        float rotation = (float) object.getProperties().get("rotation");

        float x = (float) object.getProperties().get("x");
        float y = (float) object.getProperties().get("y");
        float w = (float) object.getProperties().get("width");
        float h = (float) object.getProperties().get("height");

        Vector2 center = new Vector2(x + w / 2f, y + h / 2f);

        float offsetX = w / 2f;
        float offsetY = h / 2f;

        if (object instanceof RectangleMapObject) {
            y -= h;
            center.set(x + offsetX, y - offsetY);
        } else {
            center.set(x + offsetX, y + offsetY);
        }

        Vector2 toCenter = new Vector2(center.sub(x, y)).rotate(-rotation);
        Vector2 currentCenter = new Vector2(x, y).sub(toCenter);

        x = currentCenter.x + w / 2f;
        y = currentCenter.y + h / 2f;

        object.getProperties().put("x", x);
        object.getProperties().put("y", y);

        element("object");
        attribute("id", props.get("id"));
        attribute("name", getProperty(props, "name", ""));
        if (props.containsKey("type"))
            attribute("type", getProperty(props, "type", ""));
        if (props.containsKey("gid")) {
            attribute("gid", props.get("gid"));
        }

        float objectX = props.get("x", Float.class);
        float objectY = props.get("y", Float.class);

        if (object instanceof RectangleMapObject) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            int height = round(rect.height);
            attribute("x", objectX).attribute("y", toYDown(objectY + height));
            attribute("width", round(rect.width)).attribute("height", height);
        } else if (object instanceof EllipseMapObject) {
            Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
            int height = round(ellipse.height);
            attribute("x", objectX).attribute("y", toYDown(objectY + height));
            attribute("width", round(ellipse.width)).attribute("height", height);
            element("ellipse").pop();
        } else if (object instanceof CircleMapObject) {
            Circle circle = ((CircleMapObject) object).getCircle();
            attribute("x", objectX).attribute("y", objectY);
            attribute("width", round(circle.radius * 2)).attribute("height", round(circle.radius * 2));
            element("ellipse").pop();
        } else if (object instanceof PolygonMapObject) {
            attribute("x", objectX).attribute("y", toYDown(objectY));
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            element("polygon");
            FloatArray tmp = Pools.obtain(FloatArray.class);
            tmp.clear();
            tmp.addAll(polygon.getVertices());
            attribute("points", points(GeometryUtils.toYDown(tmp)));
            tmp.clear();
            Pools.free(tmp);
            pop();
        } else if (object instanceof PolylineMapObject) {
            attribute("x", objectX).attribute("y", toYDown(objectY));
            Polyline polyline = ((PolylineMapObject) object).getPolyline();
            element("polyline");
            FloatArray tmp = Pools.obtain(FloatArray.class);
            tmp.clear();
            tmp.addAll(polyline.getVertices());
            attribute("points", points(GeometryUtils.toYDown(tmp)));
            tmp.clear();
            Pools.free(tmp);
            pop();
        } else if (object instanceof TiledMapTileMapObject) {
            float height = props.get("height", Float.class);
            attribute("x", objectX).attribute("y", toYDown(objectY));
            attribute("width", props.get("width", Float.class)).attribute("height", height);
        }

        if (props.containsKey("rotation"))
            attribute("rotation", getProperty(props, "rotation", 0f));
        if (props.containsKey("visible"))
            attribute("visible", object.isVisible() ? 1 : 0);
        if (object.getOpacity() != 1)
            attribute("opacity", object.getOpacity());

        @SuppressWarnings("unchecked")
        Array<String> excludedKeys = Pools.obtain(Array.class);
        excludedKeys.clear();
        excludedKeys.add("id");
        excludedKeys.add("name");
        excludedKeys.add("type");
        excludedKeys.add("gid");
        excludedKeys.add("x");
        excludedKeys.add("y");
        excludedKeys.add("width");
        excludedKeys.add("height");
        excludedKeys.add("rotation");
        excludedKeys.add("visible");
        excludedKeys.add("opacity");
        tmx(props, excludedKeys);
        excludedKeys.clear();
        Pools.free(excludedKeys);

        pop();

        return this;
    }

    private String points(FloatArray vertices) {
        StringBuilder points = new StringBuilder();
        for (int i = 0; i < vertices.size; i++)
            points.append(round(vertices.get(i))).append(i % 2 != 0 ? i + 1 < vertices.size ? " " : "" : ",");
        return points.toString();
    }
}
