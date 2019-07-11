package by.mkwt.engine.tiled.serializer;

import by.mkwt.engine.tiled.TiledEntity;
import com.badlogic.ashley.core.Entity;


public abstract class ComponentSerializer {

    protected Entity entity;

    public abstract void setEntity(TiledEntity entity);

}
