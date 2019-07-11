package by.mkwt.engine.system.controller;

import by.mkwt.engine.Constants;
import by.mkwt.engine.util.CMHolder;
import by.mkwt.engine.component.alias.PlayerComponent;
import by.mkwt.engine.component.physic.PhysicComponent;
import by.mkwt.engine.util.CoordsConverter;
import by.mkwt.engine.util.Multiflexer;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

import javax.inject.Inject;

public class PlayerControlSystem extends IteratingSystem {

    private Vector2 target = new Vector2();

    private Vector2 direction = new Vector2();

    private boolean isTouched = false;

    private final float ALLOWABLE_LENGTH = 0.5f;

    private boolean isShift = false;

    private Vector2 tmp = new Vector2();

    private float pushForce = 10;

    private InputAdapter inputAdapter = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {

            if (keycode == Input.Keys.W) {
                direction.add(0, 1);
            }

            if (keycode == Input.Keys.A) {
                direction.add(-1, 0);
            }

            if (keycode == Input.Keys.S) {
                direction.add(0, -1);
            }

            if (keycode == Input.Keys.D) {
                direction.add(1, 0);
            }

            if (keycode == Input.Keys.SHIFT_LEFT) {
                isShift = true;
            }

            return false;
        }

        @Override
        public boolean keyUp(int keycode) {

            if (keycode == Input.Keys.W) {
                direction.add(0, -1);
            }

            if (keycode == Input.Keys.A) {
                direction.add(1, 0);
            }

            if (keycode == Input.Keys.S) {
                direction.add(0, 1);
            }

            if (keycode == Input.Keys.D) {
                direction.add(-1, 0);
            }

            if (keycode == Input.Keys.SHIFT_LEFT) {
                isShift = false;
            }

            return false;
        }
    };

    private Camera camera;

    public PlayerControlSystem(Camera camera) {
        super(Family.all(PhysicComponent.class, PlayerComponent.class).get());
        this.camera = camera;

        Multiflexer.getInstance().addProcessor(inputAdapter);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicComponent physicBody = CMHolder.physic.get(entity);

//        Vector2 vel = body.body.getLinearVelocity();
//        Vector2 pos = body.body.getPosition();
//
//        if (vel.len() < 4) {
//            body.body.applyLinearImpulse(direction.cpy().scl(pushForce), body.body.getWorldCenter(), true);
//        }

        float currentSpeed = pushForce;

        if (isShift) {
            currentSpeed *= 2;
        }

        tmp.set(direction);
        physicBody.body.setLinearVelocity(tmp.nor().scl(CoordsConverter.MetersToPixels(currentSpeed * deltaTime)));
    }
}
