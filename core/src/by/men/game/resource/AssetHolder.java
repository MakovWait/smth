package by.men.game.resource;

import by.mkwt.engine.tiled.loader.TmxMapLoaderWithTemplates;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class AssetHolder {

    static final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    private AssetManager assetManager;
    private BitmapFont font24;
    private Skin skin;

    public AssetHolder() {
        assetManager = new AssetManager();

        assetManager.setLoader(TiledMap.class, new TmxMapLoaderWithTemplates(new InternalFileHandleResolver()));
        //assetManager.setLoader(TiledMap.class, new AtlasTmxMapLoader(new InternalFileHandleResolver()));
//        assetManager.setLoader(TiledMap.class, new TmxMapLoaderWithTemplates(new InternalFileHandleResolver()));

        defaultLoad();
    }

    public void defaultLoad() {
//        assetManager.load("map/lobby.tmx", TiledMap.class);
//        assetManager.load("map/lobby1.tmx", TiledMap.class);

        //assetManager.load("map/tavern_interior.tmx", TiledMap.class);
        //assetManager.load("map/tavern_exterior.tmx", TiledMap.class);
        //assetManager.load("map/city.tmx", TiledMap.class);

        assetManager.load("map/club-outside.tmx", TiledMap.class);

        assetManager.load("ui/skin/default/skin/uiskin.atlas", TextureAtlas.class);
//        assetManager.load("map/lobby2.tmx", TiledMap.class);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/16103.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.characters = FONT_CHARS;
        params.size = 24;
        params.color = Color.BLACK;
        font24 = generator.generateFont(params);

        assetManager.finishLoading();

        this.skin = new Skin();
        this.skin.addRegions(getAsset("ui/skin/default/skin/uiskin.atlas"));
        this.skin.load(Gdx.files.internal("ui/skin/default/skin/uiskin.json"));
        this.skin.add("default-font", getFont24());

        this.skin.get(TextButton.TextButtonStyle.class).font = getFont24();
        this.skin.get(Window.WindowStyle.class).titleFont = getFont24();
        this.skin.get(Label.LabelStyle.class).font = getFont24();
    }

    public <T> T getAsset(String assetName) {
        return assetManager.get(assetName);
    }

    public BitmapFont getFont24() {
        return font24;
    }

    public Skin getSkin() {
        return skin;
    }

    public void dispose() {
        skin.dispose();
        assetManager.dispose();
    }

}
