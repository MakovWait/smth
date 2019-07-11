package by.men.game.ecs.system.interactive;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class MessageSystem extends IteratingSystem {
    public MessageSystem(Family family) {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

//    private Entity target;
//    private Array<Entity> messageEntities = new Array<>();
//    private ShowMessageMediator showMessageMediator;
//
//    public MessageSystem(ShowMessageMediator showMessageMediator) {
//        super(Family.one(MessageComponent.class, PlayerComponent.class).get());
//        //Game.daggerMainComponent.inject(this);
//        this.showMessageMediator = showMessageMediator;
//    }
//
//    @Override
//    public void update(float deltaTime) {
//        super.update(deltaTime);
//
//        messageEntities.forEach(messageEntity -> {
//            TransformComponent transformComponent = CMHolder.transform.get(messageEntity);
//            if (transformComponent.position.cpy().sub(CMHolder.transform.get(target).position).len() < CMHolder.message.get(messageEntity).radius) {
//                if (!CMHolder.message.get(messageEntity).isShowed) {
//                    showMessageMediator.showMessage(new SimpleMessage(CMHolder.message.get(messageEntity).title, CMHolder.message.get(messageEntity).msg));
//                    CMHolder.message.get(messageEntity).isShowed = true;
//                }
//            } else {
//                if (CMHolder.message.get(messageEntity).isShowed) {
//                    showMessageMediator.hideMessage();
//                    CMHolder.message.get(messageEntity).isShowed = false;
//                }
//            }
//        });
//
//        messageEntities.clear();
//    }
//
//    @Override
//    protected void processEntity(Entity entity, float deltaTime) {
//        if (CMHolder.player.has(entity)) {
//            target = entity;
//        } else {
//            messageEntities.add(entity);
//        }
//    }

}
