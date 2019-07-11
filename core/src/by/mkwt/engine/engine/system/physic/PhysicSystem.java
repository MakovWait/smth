package by.mkwt.engine.system.physic;

import by.mkwt.engine.Constants;
import by.mkwt.engine.util.CMHolder;
import by.mkwt.engine.component.graphic.TransformComponent;
import by.mkwt.engine.component.physic.PhysicComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicSystem extends IteratingSystem {

    private World world;

    public PhysicSystem(World world) {
        super(Family.all(TransformComponent.class, PhysicComponent.class).get());
        this.world = world;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        //world.step(1/60f, 6, 2);

        world.step(deltaTime, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);

        /*float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= Constants.TIME_STEP) {
            world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
            accumulator -= Constants.TIME_STEP;
        }*/
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = CMHolder.transform.get(entity);
        PhysicComponent physic = CMHolder.physic.get(entity);

        Vector2 newPos = physic.body.getPosition();

        trans.position.set(newPos.x - physic.size.x / 2f - trans.offset.x, newPos.y - physic.size.y / 2f - trans.offset.y);
        //trans.origin.set(physic.size.x / 2f + trans.offset.x, physic.size.y / 2f + trans.offset.y);
//        physic.body.setAngularVelocity(1);

        //trans.rotation = MathUtils.radiansToDegrees * physic.body.getAngle();

        //TODO newVector
//        newPos = new Vector2(0f, 0.1f * MathUtils.sin((GameEngine.time) * 16));
//        trans.polygon.setPosition(trans.polygon.getX() + newPos.x, trans.polygon.getY() + newPos.y);
        // trans.polygon.setRotation(MathUtils.radiansToDegrees * body.body.getAngle());
    }

}
