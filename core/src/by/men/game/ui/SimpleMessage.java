package by.men.game.ui;

import com.badlogic.gdx.utils.Array;

public class SimpleMessage {

    private String title;
    private Array<String> content;
    private int currentContentIdx = 0;

    public SimpleMessage(String title, String... content) {
        this.title = title;
        this.content = new Array<>();
        this.content.addAll(content);
    }

    public SimpleMessage(String title, Array<String> content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getCurrentContent() {
        return content.get(currentContentIdx);
    }

    public void next() {
        currentContentIdx++;
    }

    public boolean isLastContent() {
        return currentContentIdx >= content.size - 1;
    }

    public Array<String> getContent() {
        return content;
    }
}
