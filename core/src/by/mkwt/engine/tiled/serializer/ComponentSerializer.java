package by.mkwt.tiled.serializer;

import by.mkwt.tiled.TiledEntity;
import com.badlogic.ashley.core.Entity;


public abstract class ComponentSerializer {

    private Entity entity;

    public abstract void setEntity(TiledEntity entity);

}
