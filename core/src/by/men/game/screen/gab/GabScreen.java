package by.men.game.screen.gab;

import by.men.game.screen.gab.entity.Entity;
import by.men.game.screen.gab.scriptImpl.ScriptImplsContainer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Json;

public class GabScreen implements Screen {

    public GabScreen() {
        Json json = new Json();
        Entity.JSONScriptEntity map = json.fromJson(Entity.JSONScriptEntity.class, Gdx.files.internal("try.json"));
        map.script.events.forEach(event -> ScriptImplsContainer.impl(defineCommandForEvent(event), event));
        ScriptImplsContainer.impl(Commands.CHOICE, map.script.choice);
    }

    private Commands defineCommandForEvent(Object o) {
        Class<?> type = o.getClass();
        if (type.equals(Entity.StateChange.class)) {
            Entity.StateChange stateChange = (Entity.StateChange) o;
            if (stateChange.getHealthChange() != null) {
                return Commands.HEALTH_CHANGE;
            }
            if (stateChange.getCharacterMove() != null) {
                return Commands.CHARACTER_MOVE;
            }
        } else if (type.equals(Entity.ScriptText.class)) {
            return Commands.TEXT;
        }
        return null;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
