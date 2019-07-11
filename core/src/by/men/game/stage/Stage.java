package by.mkwt.engine.tiled;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Stage {

    private String mapName;
    private TiledMap map;
    private Array<Entity> entities;

    public Stage(TiledMap map, String mapName) {
        this.map = map;
        this.mapName = mapName;
        entities = new Array<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public Array<Entity> getEntities() {
        return entities;
    }

    public TiledMap getMap() {
        return map;
    }

    public String getMapName() {
        return mapName;
    }

}
