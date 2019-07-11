package by.mkwt.engine.ecs.system.render;

import box2dLight.RayHandler;
import by.mkwt.engine.GameContext;
import by.mkwt.engine.ecs.component.graphic.PointLightComponent;
import by.mkwt.engine.ecs.component.graphic.TransformComponent;
import by.mkwt.engine.util.CoreCMHolder;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
        GameContext.injector.inject(this);

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
        PointLightComponent pointLightComponent = CoreCMHolder.pointLight.get(entity);
        TransformComponent transformComponent = CoreCMHolder.transform.get(entity);

        pointLightComponent.pointLight.setPosition(transformComponent.position.x + transformComponent.size.x / 2f, transformComponent.position.y + transformComponent.size.y / 2f);
    }

}
