package by.mkwt.engine.system.render;

import by.mkwt.engine.component.graphic.SpriteShakeComponent;
import by.mkwt.engine.component.graphic.TransformComponent;
import by.mkwt.engine.component.physic.PhysicComponent;
import by.mkwt.engine.util.CMHolder;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class SpriteShakeSystem extends IteratingSystem {

    private float time = 0;

    public SpriteShakeSystem() {
        super(Family.all(TransformComponent.class, SpriteShakeComponent.class, PhysicComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        time += deltaTime;
        super.update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float k = MathUtils.sin(time * 32);

        TransformComponent trans = CMHolder.transform.get(entity);

        SpriteShakeComponent spriteShake = CMHolder.spriteShake.get(entity);
        PhysicComponent physic = CMHolder.physic.get(entity);

        Vector2 newPos = new Vector2(0f, 0.05f * MathUtils.sin(time * 16));
        trans.position.set(trans.position.x + newPos.x, trans.position.y + newPos.y);

        if (physic.body.getLinearVelocity().len() > 0.5f) {
            float currentRotateVal = spriteShake.shakeForce * k;
            trans.origin.set(trans.size.x / 2f, trans.size.y / 2f);

            trans.rotation = currentRotateVal;
        }
    }

}