package by.mkwt.engine.system.render;

import by.mkwt.engine.util.CMHolder;
import by.mkwt.engine.component.graphic.LayerComponent;
import by.mkwt.engine.component.graphic.TextureComponent;
import by.mkwt.engine.component.graphic.TransformComponent;
import by.mkwt.engine.util.CoordsConverter;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class RenderSystem extends SortedIteratingSystem {

    private Camera camera;
    private SpriteBatch batch;
    private ObjectMap<String, Array<Entity>> renderQueue;
    private TiledMap stage;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public RenderSystem(Camera camera) {
        super(Family.all(TransformComponent.class, TextureComponent.class, LayerComponent.class).get(), new ZComparator());

        this.camera = camera;
        this.batch = new SpriteBatch();
        renderQueue = new ObjectMap<>();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (renderQueue.get(CMHolder.layer.get(entity).layerName) == null) {
            renderQueue.put(CMHolder.layer.get(entity).layerName, new Array<>());
        }

        renderQueue.get(CMHolder.layer.get(entity).layerName).add(entity);
    }

    @Override
    public void update(float deltaTime) {
        forceSort();
        super.update(deltaTime);

        camera.update();

        render();

        renderQueue.clear();
    }

    private void render() {
        //Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        drawAll();

        renderQueue.clear();

        batch.end();
        //debugRenderer.render(GameEngine.getInstance().getWorld(), GameEngine.getInstance().getGameView().getCamera().combined);
    }

    public void setStage(TiledMap stage) {
        this.stage = stage;
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(stage);
    }

    private void drawAll() {
        for (MapLayer layer : stage.getLayers()) {
            if (layer.isVisible()) {
                drawRenderQueue(layer.getName());
                if (layer instanceof TiledMapTileLayer) {
//                    for (int i = 0; i < ((TiledMapTileLayer) layer).getWidth(); i++) {
//                        for (int j = 0; j < ((TiledMapTileLayer) layer).getHeight(); j++) {
//                            //TODO возможна некоторая оптимизация (i, j)
//                            TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) layer).getCell(i, j);
//
//                            if (cell != null) {
//                                cell.getTile().setBlendMode(TiledMapTile.BlendMode.ALPHA);
//                                TextureRegion tex = cell.getTile().getTextureRegion();
//
//                                batch.draw(tex, i + cell.getTile().getOffsetX(), j + cell.getTile().getOffsetY(),
//                                        CoordsConverter.pixelsToMeters(tex.getRegionWidth()), CoordsConverter.pixelsToMeters(tex.getRegionHeight()));
//                            }
//                        }
//                    }
                    for (int i = (int)camera.position.x - 20; i < (int)camera.position.x + 20; i++) {
                        for (int j = (int)camera.position.y - 20; j < (int)camera.position.y + 20; j++) {
                            //TODO возможна некоторая оптимизация (i, j)
                            TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) layer).getCell(i, j);

                            if (cell != null) {
                                cell.getTile().setBlendMode(TiledMapTile.BlendMode.ALPHA);
                                TextureRegion tex = cell.getTile().getTextureRegion();

                                batch.draw(tex, i + cell.getTile().getOffsetX(), j + cell.getTile().getOffsetY(),
                                        CoordsConverter.pixelsToMeters(tex.getRegionWidth()), CoordsConverter.pixelsToMeters(tex.getRegionHeight()));
                            }
                        }
                    }
                }
            }
        }
//
//        orthogonalTiledMapRenderer.getBatch().setProjectionMatrix(camera.combined);
//        orthogonalTiledMapRenderer.setView((OrthographicCamera) camera);
//        orthogonalTiledMapRenderer.render();
    }

    private void drawRenderQueue(String layerName) {
        if (renderQueue.get(layerName) != null) {
            for (Entity entity : renderQueue.get(layerName)) {
                TextureComponent tex = CMHolder.texture.get(entity);
                TransformComponent trans = CMHolder.transform.get(entity);

                if (tex.region == null || trans.isHidden) {
                    continue;
                }

                batch.draw(tex.region,
                        trans.position.x, trans.position.y,
                        trans.origin.x, trans.origin.y,
                        trans.size.x * trans.scale.x, trans.size.y * trans.scale.y,
                        1, 1,
                        trans.rotation);
            }
        }
    }
}
