package game.arup.gamedev;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ArupPc on 21-12-2016.
 */
public class GameRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "GameRenderer";
    private Context context;
    public static float[] mVPMatrix = new float[16];
    public static float[] mProjectionMatrix = new float[16];
    public static float[] mViewMatrix = new float[16];
    private Starfield starfield;

    public GameRenderer(Context gameContext) {
        context = gameContext;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        starfield = new Starfield();

    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

    }

    @Override
    public void onDrawFrame(GL10 unused) {

        float[] matrix = new float[16];
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        starfield.draw(mVPMatrix);

    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES30.glCreateShader(type);
        GLES30.glShaderSource(shader,shaderCode);
        GLES30.glCompileShader(shader);
        return shader;
    }

    public static void checkGLError(String glOperation){
        int error;
        while((error = GLES30.glGetError())!= GLES30.GL_NO_ERROR){
            Log.i(TAG,glOperation + ": glError :" + error);
            throw new RuntimeException(glOperation + ": glError :" + error);

        }
    }
}
