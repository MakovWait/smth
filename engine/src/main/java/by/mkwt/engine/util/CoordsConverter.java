package by.mkwt.engine.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CoordsConverter {

    private static Vector2 result = new Vector2();
    private static final float PPM = 32.0f;

    public static final float PIXELS_TO_METRES = 1.0f / PPM;

    public static Vector2 mouseIn2DWorld(Camera camera) {
        Vector3 mouseInWorld3D = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mouseInWorld3D);
        return result.set(mouseInWorld3D.x, mouseInWorld3D.y).cpy();
    }

    public static float pixelsToMeters(float pixelValue) {
        return pixelValue * PIXELS_TO_METRES;
    }

    public static float MetersToPixels(float meterValue) {
        return meterValue * PPM;
    }

}
