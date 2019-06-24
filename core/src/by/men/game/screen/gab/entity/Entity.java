package by.men.game.screen.gab.entity;

import java.util.List;

public class Entity {
    public static class JSONScriptEntity {
        public String place;
        public Script script;

        public void setPlace(String place) {
            this.place = place;
        }

        public void setScript(Script script) {
            this.script = script;
        }
    }

    public static class Choice {
        public String title;
        public String reference;

        public String getTitle() {
            return title;
        }

        public String getReference() {
            return reference;
        }
    }

    public interface ScriptEvent {
    }

    public static class ScriptText implements ScriptEvent {
        List<String> text;

        public List<String> getText() {
            return text;
        }
    }

    public static class StateChange implements ScriptEvent {
        Integer healthChange;
        Integer characterMove;

        public Integer getHealthChange() {
            return healthChange;
        }

        public Integer getCharacterMove() {
            return characterMove;
        }
    }

    public static class Script {
        public List<Choice> choice;
        public List<ScriptEvent> events;

        public List<Choice> getChoice() {
            return choice;
        }

        public List<ScriptEvent> getEvents() {
            return events;
        }
    }
}
