package by.mkwt.engine.tiled;

import by.mkwt.AssetHolder;
import by.mkwt.engine.tiled.loader.TmxWriterWithTileObject;
import by.mkwt.engine.tiled.preprocessor.MapPropertiesHandler;
import by.mkwt.engine.tiled.serializer.TiledSerializer;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import net.dermetfan.gdx.maps.tiled.TmxMapWriter;

import javax.inject.Inject;
import java.io.IOException;
import java.io.StringWriter;

public class StageLoader {

    private AssetHolder assetHolder;
    private Array<Stage> stages;
    private TiledSerializer tiledSerializer;
    private StringWriter stringWriter;
    private TmxMapWriter tmxMapWriter;

    @Inject
    public StageLoader(AssetHolder assetHolder, TiledSerializer tiledSerializer) {
        this.assetHolder = assetHolder;
        this.tiledSerializer = tiledSerializer;
        stringWriter = new StringWriter();

        tmxMapWriter = new TmxWriterWithTileObject(stringWriter);
        stages = new Array<>();
    }

    public void addStage(String mapName) {
        if (!stages.isEmpty()) {
            saveCurrentStage();
        }

        stages.add(parseStageFromTiledMap(mapName));
    }

    private Stage parseStageFromTiledMap(String mapName) {
        System.out.println("map name" + mapName);
        Stage stage = new Stage(assetHolder.getAsset(mapName), mapName);

        tiledSerializer.prepare();

        stage.getMap().getLayers().forEach(layer -> layer.getObjects().forEach(object -> {
            object.getProperties().put("layer", layer.getName());
            //TODO
            MapPropertiesHandler.checkDefaultProperties(object);
            stage.addEntity(tiledSerializer.parse(object));
        }));

        return stage;
    }

    public void saveCurrentStage() {
        Stage stage = stages.peek();

        for (Entity entity : stage.getEntities()) {
            if (entity instanceof TiledEntity) {
                tiledSerializer.update((TiledEntity) entity);
            }
        }

        try {
            tmxMapWriter.tmx(stage.getMap(), TmxMapWriter.Format.CSV);
            //TODO сохраняет калично
            FileHandle file = Gdx.files.local(stage.getMapName());
            //file.writeString(Base64Coder.encodeString(stringWriter.toString()), false);
            file.writeBytes(stringWriter.toString().getBytes("UTF-8"), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getCurrentStage() {
        return stages.peek();
    }

}
