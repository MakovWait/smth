package by.mkwt.engine.ecs.component;


import by.mkwt.engine.ecs.component.alias.PlayerComponent;
import by.mkwt.engine.ecs.component.graphic.*;
import by.mkwt.engine.ecs.component.interactive.MessageComponent;
import by.mkwt.engine.ecs.component.physic.PhysicComponent;
import com.badlogic.ashley.core.ComponentMapper;

public class CoreCMHolder {

    public static ComponentMapper<TiledComponent> tiled = ComponentMapper.getFor(TiledComponent.class);
    public static ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static ComponentMapper<PhysicComponent> physic = ComponentMapper.getFor(PhysicComponent.class);
    public static ComponentMapper<PointLightComponent> pointLight = ComponentMapper.getFor(PointLightComponent.class);
    public static ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    public static ComponentMapper<LayerComponent> layer = ComponentMapper.getFor(LayerComponent.class);
    public static ComponentMapper<SpriteShakeComponent> spriteShake = ComponentMapper.getFor(SpriteShakeComponent.class);
    public static ComponentMapper<MessageComponent> message = ComponentMapper.getFor(MessageComponent.class);
    public static ComponentMapper<PlayerComponent> player = ComponentMapper.getFor(PlayerComponent.class);

    //public static ComponentMapper<>  = ComponentMapper.getFor();
}
