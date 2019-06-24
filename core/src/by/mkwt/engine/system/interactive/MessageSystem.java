package by.mkwt.engine.system.interactive;

import by.mkwt.engine.component.alias.PlayerComponent;
import by.mkwt.engine.component.graphic.TransformComponent;
import by.mkwt.engine.component.interactive.MessageComponent;
import by.mkwt.engine.util.CMHolder;
import by.mkwt.provider.ShowMessageMediator;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

public class MessageSystem extends IteratingSystem {

    private Entity target;
    private Array<Entity> messageEntities = new Array<>();
    private ShowMessageMediator showMessageMediator;

    public MessageSystem(ShowMessageMediator showMessageMediator) {
        super(Family.one(MessageComponent.class, PlayerComponent.class).get());
        //Game.daggerMainComponent.inject(this);
        this.showMessageMediator = showMessageMediator;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        messageEntities.forEach(messageEntity -> {
            TransformComponent transformComponent = CMHolder.transform.get(messageEntity);
            if (transformComponent.position.cpy().sub(CMHolder.transform.get(target).position).len() < CMHolder.message.get(messageEntity).radius) {
                messageEntity.remove(MessageComponent.class);
                if (CMHolder.message.has(messageEntity))
                showMessageMediator.showMessage(CMHolder.message.get(messageEntity).msg);
            }
        });

        messageEntities.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (CMHolder.player.has(entity)) {
            target = entity;
        } else {
            messageEntities.add(entity);
        }
    }

}
