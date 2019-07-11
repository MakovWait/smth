package by.men.game.ecs.system.render;

import by.men.game.ecs.component.graphic.FlippableMarkerComponent;
import by.men.game.util.CMHolder;
import by.mkwt.engine.ecs.component.graphic.TextureComponent;
import by.mkwt.engine.ecs.component.graphic.TransformComponent;
import by.mkwt.engine.util.CoordsConverter;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

public class FlippingSystem extends IteratingSystem {

    private Camera camera;

    public FlippingSystem(Camera camera) {
        super(Family.all(
                TransformComponent.class,
                TextureComponent.class,
                FlippableMarkerComponent.class
        ).get());

        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = CMHolder.transform.get(entity);
        TextureComponent tex = CMHolder.texture.get(entity);

        Vector2 mousePos = CoordsConverter.mouseIn2DWorld(camera);

        Vector2 dir = mousePos.cpy().sub(trans.position);

        if (dir.x > 0) {
            if (tex.region.isFlipX()) {
                tex.region.flip(true, false);
            }
        } else if (dir.x < 0) {
            if (!tex.region.isFlipX()) {
                tex.region.flip(true, false);
            }
        }

        /*if (trans.isNeedToFlip) {
            tex.region.flip(true, false);
            trans.isNeedToFlip = false;
        }*/
    }
}
