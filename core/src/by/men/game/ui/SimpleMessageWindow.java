package by.men.game.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;


public class SimpleMessageWindow extends Window {

    private SimpleMessage simpleMessage;
    private Label contentLabel;
    private TextButton nextContentButton;
    private TextButton closeContentButton;

    public SimpleMessageWindow(Skin skin) {
        super("Message", skin);

        contentLabel = new Label("", skin);
        contentLabel.setWrap(true);
        contentLabel.setAlignment(Align.center);
        nextContentButton = new TextButton("далее", skin);
        closeContentButton = new TextButton("X", skin);

        nextContentButton.setVisible(false);

        //layout
        this.add(closeContentButton).right().top();
        this.row();
        this.getTitleLabel().setAlignment(Align.center);
        this.add(contentLabel).width(500);
        this.row().pad(10, 10, 10, 10);
        this.add(nextContentButton);
        //this.debug();

        nextContentButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                simpleMessage.next();
                setContent(simpleMessage.getCurrentContent());
                if (simpleMessage.isLastContent()) {
                    nextContentButton.setVisible(false);
                }
            }
        });

        closeContentButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reset();
                setVisible(false);
            }
        });
    }

    public void setTitle(String title) {
        getTitleLabel().setText(title);
    }

    public void setMessage(SimpleMessage simpleMessage) {
        this.simpleMessage = simpleMessage;
        setTitle(simpleMessage.getTitle());
        setContent(simpleMessage.getCurrentContent());
        if (!simpleMessage.isLastContent()) {
            nextContentButton.setVisible(true);
        }
    }

    public Label getContentLabel() {
        return contentLabel;
    }

    public void setContent(String content) {
        contentLabel.setText(content);
        this.pack();
    }

    public void reset() {
        setTitle("");
        setContent("");
    }
}
