package by.mkwt.engine.component.graphic;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {

    //public Polygon polygon;

    public Vector2 position;

    public Vector2 size;

    public Vector2 scale = new Vector2(1, 1);

    public Vector2 origin;

    public float rotation = 0;

    public Vector2 offset = new Vector2(0, 0);

    public float z;

    public boolean isNeedToFlip = false;

    public boolean isHidden = false;

    @Override
    public String toString() {
        return "TransformComponent{" +
                "position=" + position +
                ", size=" + size +
                ", scale=" + scale +
                ", origin=" + origin +
                ", rotation=" + rotation +
                ", offset=" + offset +
                ", z=" + z +
                ", isNeedToFlip=" + isNeedToFlip +
                ", isHidden=" + isHidden +
                '}';
    }

}