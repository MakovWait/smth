package by.mkwt.engine;

import by.mkwt.engine.system.controller.CameraMovementSystem;
import by.mkwt.engine.system.controller.PlayerControlSystem;
import by.mkwt.engine.system.custom.StepSoundSystem;
import by.mkwt.engine.system.interactive.MessageSystem;
import by.mkwt.engine.system.physic.PhysicSystem;
import by.mkwt.engine.system.render.LightingSystem;
import by.mkwt.engine.system.render.RenderSystem;
import by.mkwt.engine.system.render.SpriteShakeSystem;
import by.mkwt.provider.ShowMessageMediator;
import by.mkwt.tiled.Stage;
import by.mkwt.tiled.StageLoader;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import javax.inject.Inject;

public class GameEngine {

    private StageLoader stageLoader;
    private PooledEngine engine;
    private World world;
    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private OrthographicCamera camera;

    @Inject
    public GameEngine(StageLoader stageLoader, World world, OrthographicCamera camera) {
        this.stageLoader = stageLoader;
        this.world = world;
        this.camera = camera;

        camera.position.x = 10;
        camera.position.y = 6;

        System.out.println("game engine created");
        engine = new PooledEngine();

        RenderSystem renderSystem = new RenderSystem(camera);

        engine.addSystem(new CameraMovementSystem(camera));
        engine.addSystem(renderSystem);
        engine.addSystem(new LightingSystem());

        engine.addSystem(new MessageSystem(new ShowMessageMediator() {
            @Override
            public void showMessage(String msg) {
                System.out.println(msg);
            }
        }));

        engine.addSystem(new PhysicSystem(world));

        engine.addSystem(new SpriteShakeSystem());
        engine.addSystem(new PlayerControlSystem(camera));
        engine.addSystem(new StepSoundSystem());

        stageLoader.addStage("map/city.tmx");

        Stage stage = stageLoader.getCurrentStage();

        addEntities(stage.getEntities());

        renderSystem.setStage(stage.getMap());
    }

    public void update(float deltaTime) {
        engine.update(deltaTime);
        //debugRenderer.render(world, camera.combined);
    }

    public void save() {
        stageLoader.saveCurrentStage();
    }

    public void addEntities(Array<Entity> entities) {
        entities.forEach(entity -> engine.addEntity(entity));
    }

}
