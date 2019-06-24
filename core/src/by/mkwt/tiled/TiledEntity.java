package by.mkwt.tiled;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;

public class TiledEntity extends Entity {

    private MapObject mapObject;

    public TiledEntity(MapObject mapObject) {
        this.mapObject = mapObject;
    }

    public MapObject getMapObject() {
        return mapObject;
    }

}
