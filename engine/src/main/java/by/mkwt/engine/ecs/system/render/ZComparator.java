package by.mkwt.engine.ecs.system.render;

import by.mkwt.engine.ecs.component.graphic.TransformComponent;
import by.mkwt.engine.util.CoreCMHolder;
import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {

    @Override
    public int compare(Entity entityA, Entity entityB) {
        TransformComponent pA = CoreCMHolder.transform.get(entityA);
        TransformComponent pB = CoreCMHolder.transform.get(entityB);

        float aZ = pA.position.y - pA.z;
        float bZ = pB.position.y - pB.z;

        if (aZ < bZ) {
            return 1;
        } else if (aZ > bZ) {
            return -1;
        }

        return 0;
    }

}
