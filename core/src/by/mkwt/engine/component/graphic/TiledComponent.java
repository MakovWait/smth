package by.mkwt.engine.component.graphic;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TiledComponent implements Component {

    public int gid = 0;
    public int id = 0;
    public float width = 0;
    public String name = "";
    public String type = "";
    public float height = 0;
    public float x = 0;
    public float y = 0;
    public float scaleX = 1;
    public float scaleY = 1;

    public float z = 0;
    public float rotation = 0;
    public TextureRegion region = null;

    @Override
    public String toString() {
        return "TiledComponent{" +
                "gid=" + gid +
                ", id=" + id +
                ", width=" + width +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                ", scaleX=" + scaleX +
                ", scaleY=" + scaleY +
                ", z=" + z +
                ", rotation=" + rotation +
                ", region=" + region +
                '}';
    }
}
