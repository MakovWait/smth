package by.mkwt.engine.system.render;

import by.mkwt.engine.util.CMHolder;
import by.mkwt.engine.component.graphic.TransformComponent;
import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {

    @Override
    public int compare(Entity entityA, Entity entityB) {
        TransformComponent pA = CMHolder.transform.get(entityA);
        TransformComponent pB = CMHolder.transform.get(entityB);

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
