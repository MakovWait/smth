package by.men.game.screen.gab.scriptImpl;

import by.men.game.screen.gab.Commands;
import by.men.game.screen.gab.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ScriptImplsContainer {
    private static Map<Commands, Implementation> impls = new HashMap<>();

    static {
        impls.put(Commands.CHOICE, param -> {
            List<Entity.Choice> choices = (List<Entity.Choice>) param;
            for (Entity.Choice choice : choices) {
                System.out.println(choice.reference);
                System.out.println(choice.title);
            }
        });
        impls.put(Commands.TEXT, param -> {
            Entity.ScriptText text = (Entity.ScriptText) param;
            System.out.println(text.getText());
        });
        impls.put(Commands.HEALTH_CHANGE, param -> {
            Integer changeValue = ((Entity.StateChange) param).getHealthChange();
            System.out.println(changeValue);
        });
        impls.put(Commands.CHARACTER_MOVE, param -> {
            Integer move = ((Entity.StateChange) param).getCharacterMove();
            System.out.println(move);
        });
    }

    public static Map<Commands, Implementation> getImpls() {
        return impls;
    }

    public static void impl(Commands command, List<?> params) {
        impls.get(command).execute(params);
    }

    public static void impl(Commands command, Object param) {
        impls.get(command).execute(param);
    }
}
