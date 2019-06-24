package by.mkwt.engine.component.physic;

import by.mkwt.tiled.marker.MapPropertyComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class PhysicComponent implements Component, MapPropertyComponent {

    public Body body;
    public Vector2 size;
    public boolean isRectangleMapObject = false;

}
