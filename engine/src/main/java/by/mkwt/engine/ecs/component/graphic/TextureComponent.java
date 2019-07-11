package by.mkwt.engine.ecs.component.graphic;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class TextureComponent implements Component, Json.Serializable {

    public TextureRegion region;

    @Override
    public void write(Json json) {
        String path = ((FileTextureData) region.getTexture().getTextureData()).getFileHandle().path();
        json.writeValue("texturePath", path);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        String filePath = jsonData.get("texturePath").asString();
        region = new TextureRegion(new Texture(filePath));
    }

}
