package by.mkwt;

import by.mkwt.tiled.loader.TmxMapLoaderWithTemplates;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetHolder {

    final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    private AssetManager assetManager;
    private BitmapFont font24;

    public AssetHolder() {
        System.out.println("asset holder created");
        assetManager = new AssetManager();

        assetManager.setLoader(TiledMap.class, new TmxMapLoaderWithTemplates(new InternalFileHandleResolver()));
        //assetManager.setLoader(TiledMap.class, new AtlasTmxMapLoader(new InternalFileHandleResolver()));
//        assetManager.setLoader(TiledMap.class, new TmxMapLoaderWithTemplates(new InternalFileHandleResolver()));

        defaultLoad();
    }

    private void defaultLoad() {
//        assetManager.load("map/lobby.tmx", TiledMap.class);
//        assetManager.load("map/lobby1.tmx", TiledMap.class);

        //assetManager.load("map/tavern_interior.tmx", TiledMap.class);
        //assetManager.load("map/tavern_exterior.tmx", TiledMap.class);
        assetManager.load("map/city.tmx", TiledMap.class);

        assetManager.load("ui/skin/default/skin/uiskin.atlas", TextureAtlas.class);
//        assetManager.load("map/lobby2.tmx", TiledMap.class);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/16103.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.characters = FONT_CHARS;
        params.size = 24;
        params.color = Color.BLACK;
        font24 = generator.generateFont(params);

        assetManager.finishLoading();
    }

    public <T> T getAsset(String assetName) {
//        System.out.println(assetManager.getAssetNames());
        return assetManager.get(assetName);
    }

    public BitmapFont getFont24() {
        return font24;
    }

    public void dispose() {
        assetManager.dispose();
        getFont24().dispose();
    }
}
