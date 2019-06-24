package by.mkwt.engine.system.render;

import box2dLight.RayHandler;
import by.men.game.Game;
import by.mkwt.engine.util.CMHolder;
import by.mkwt.engine.component.graphic.PointLightComponent;
import by.mkwt.engine.component.graphic.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

import javax.inject.Inject;

public class LightingSystem extends IteratingSystem {

    @Inject
    OrthographicCamera camera;
    @Inject
    World world;
    @Inject
    RayHandler rayHandler;

    float time = 0;

    public LightingSystem() {
        super(Family.all(TransformComponent.class, PointLightComponent.class).get());
        Game.daggerMainComponent.inject(this);
        //rayHandler.setAmbientLight(0.1f, 0.1f, 0.1f, 1f);
    }

    @Override
    public void update(float deltaTime) {
        time+=deltaTime;
        super.update(deltaTime);

        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PointLightComponent pointLightComponent = CMHolder.pointLight.get(entity);
        TransformComponent transformComponent = CMHolder.transform.get(entity);

        pointLightComponent.pointLight.setPosition(transformComponent.position.x + transformComponent.size.x / 2f, transformComponent.position.y + transformComponent.size.y / 2f);
    }

}
