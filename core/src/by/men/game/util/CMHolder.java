package by.men.game.util;

import by.mkwt.engine.ecs.component.graphic.LayerComponent;
import by.men.game.ecs.component.graphic.SpriteShakeComponent;
import by.men.game.ecs.component.interactive.MessageComponent;
import by.mkwt.engine.util.CoreCMHolder;
import com.badlogic.ashley.core.ComponentMapper;

public class CMHolder extends CoreCMHolder {

    public static ComponentMapper<SpriteShakeComponent> spriteShake = ComponentMapper.getFor(SpriteShakeComponent.class);
    public static ComponentMapper<MessageComponent> message = ComponentMapper.getFor(MessageComponent.class);

}
