package by.mkwt.engine.system.controller;

import by.mkwt.engine.util.CMHolder;
import by.mkwt.engine.component.alias.PlayerComponent;
import by.mkwt.engine.component.graphic.TransformComponent;
import by.mkwt.engine.util.CoordsConverter;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CameraMovementSystem extends IteratingSystem {

    private OrthographicCamera camera;
    private Vector2 direction = new Vector2(0, 0);
    private final float maxLen = 7;

    public CameraMovementSystem(OrthographicCamera camera) {
        super(Family.all(PlayerComponent.class, TransformComponent.class).get());

        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = CMHolder.transform.get(entity);

        Vector2 mouseIn2DWorld = CoordsConverter.mouseIn2DWorld(camera);

        float dst = Vector2.dst(mouseIn2DWorld.x, mouseIn2DWorld.y, transform.position.x, transform.position.y);

        direction.scl(0);

        if (dst > maxLen) {
            direction = new Vector2(mouseIn2DWorld.x - transform.position.x, mouseIn2DWorld.y - transform.position.y);
            direction.nor();
            direction.scl(dst - maxLen);
        }

        float newX = (transform.position.x + mouseIn2DWorld.x - direction.x) / 2f;
        float newY = (transform.position.y + mouseIn2DWorld.y - direction.y) / 2f;

        camera.position.x = MathUtils.lerp(camera.position.x, newX, 10f * deltaTime);
        camera.position.y = MathUtils.lerp(camera.position.y, newY, 10f * deltaTime);
    }

}
