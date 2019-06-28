package by.mkwt.engine.system.custom;

import by.mkwt.engine.component.audio.ShootSoundComponent;
import by.mkwt.engine.component.audio.StepSoundComponent;
import by.mkwt.engine.component.audio.Stereo;
import by.mkwt.engine.component.physic.PhysicComponent;
import by.mkwt.engine.util.CMHolder;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class SoundSystem extends IteratingSystem {
    private float deltaTimeSum = 0;

    public SoundSystem() {
        super(Family.all(PhysicComponent.class).one(ShootSoundComponent.class, StepSoundComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicComponent physic = CMHolder.physic.get(entity);
        deltaTimeSum += deltaTime;
        if (physic.body.getLinearVelocity().len() > 0) {
            if (deltaTimeSum > 0.17) {
                for (Component component : entity.getComponents()) {
                    if (component instanceof Stereo) {
                        ((Stereo) component).getSound().play();
                        deltaTimeSum = 0;
                    }
                }
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

}