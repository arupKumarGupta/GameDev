package game.arup.gamedev;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by ArupPc on 21-12-2016.
 */
public class GameView extends GLSurfaceView{
    private final GameRenderer gameRenderer;
    public GameView(Context context) {
        super(context);
        setEGLContextClientVersion(3);
        setFitsSystemWindows(true);
        gameRenderer = new GameRenderer(context);
        setRenderer(gameRenderer);
    }
}
