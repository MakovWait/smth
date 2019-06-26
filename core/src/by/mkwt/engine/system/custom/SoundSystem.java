package by.mkwt.engine.system.custom;

import by.mkwt.engine.component.graphic.StepSoundComponent;
import by.mkwt.engine.component.physic.PhysicComponent;
import by.mkwt.engine.util.CMHolder;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class SoundSystem extends IteratingSystem {
    private float deltaTimeSum = 0;

    public SoundSystem() {
        super(Family.all(StepSoundComponent.class, PhysicComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicComponent physic = CMHolder.physic.get(entity);
        deltaTimeSum += deltaTime;
        if (physic.body.getLinearVelocity().len() > 0) {
            StepSoundComponent stepSound = CMHolder.stepSound.get(entity);
            if (deltaTimeSum > 0.17) {
                stepSound.sound.play();
                deltaTimeSum = 0;
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

}