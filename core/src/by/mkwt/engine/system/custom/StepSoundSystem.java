package by.mkwt.engine.system.custom;

import by.mkwt.engine.component.audio.StepSoundComponent;
import by.mkwt.engine.component.physic.PhysicComponent;
import by.mkwt.engine.util.CMHolder;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class StepSoundSystem extends IteratingSystem {
    private float deltaTimeSum = 0;

    public StepSoundSystem() {
        super(Family.all(PhysicComponent.class, StepSoundComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicComponent physic = CMHolder.physic.get(entity);
        if (physic.body.getLinearVelocity().len() > 0) {
            StepSoundComponent stepSound = CMHolder.stepSound.get(entity);
            deltaTimeSum += deltaTime;
            if (deltaTimeSum > 0.17) {
                stepSound.getSound().play();
                deltaTimeSum = 0;
            }
        } else {
            deltaTimeSum = 0;
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

}