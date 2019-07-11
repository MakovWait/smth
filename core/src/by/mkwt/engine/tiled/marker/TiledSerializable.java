package by.mkwt.tiled.marker;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

public interface TiledSerializable {

    void parse(Json json, MapObject mapObject, ObjectMap<String, String> components);

    MapObject update(Json json);

}
