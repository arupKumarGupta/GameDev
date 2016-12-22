package game.arup.gamedev;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by ArupPc on 21-12-2016.
 */
public class Starfield {

    static float squareQuads[] = {
            -1f, 1f, 0.0f,//Top Left
            -1f, -1f, 0.0f,//Bottom Left
            1f, -1f, 0.0f,//Bottom Right
            1f, 1f, 0.0f//Top Right
    };
    private final short drawOrder[] = {0, 1, 2, 0, 2, 3};

    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "attribute vec2 TexCoordIn;" +
                    "varying vec2 TexCoordOut;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "  TexCoordOut = TexCoordIn;" + "}";

    final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "uniform sampler2D TexCoordIn;" +
                    "varying vec2 TexCoordOut;" + "void main() {" +
                    " gl_FragColor = texture2D(TexCoordIn, vec2(TexCoordOut.x ,TexCoordOut.y));" +
                    "}";

    private float texture[] = {
            -1f, 1f,
            -1f, -1f,
            1f, -1f,
            1f, 1f,};


    private final FloatBuffer vertexBuffer ;
    private final ShortBuffer drawListBuffer;
    private final int mProgram;
    private int mPositionHandle, mColorHandle, mMVPMatrixHandle;
    static final int COORDS_PER_VERTEX = 3;
    public final int vertexStride = COORDS_PER_VERTEX*4;
    public Starfield(){
        ByteBuffer bb = ByteBuffer.allocateDirect(squareQuads.length*4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareQuads);
        vertexBuffer.position(0);
        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length*2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        int vertexShader = GameRenderer.loadShader(GLES30.GL_VERTEX_SHADER,vertexShaderCode);
        int fragmentShader = GameRenderer.loadShader(GLES30.GL_FRAGMENT_SHADER,fragmentShaderCode);

        mProgram = GLES30.glCreateProgram();
        GLES30.glAttachShader(mProgram,vertexShader);
        GLES30.glAttachShader(mProgram,fragmentShader);
        GLES30.glLinkProgram(mProgram);


    }
    public void draw(float[] mVPMatrix /*,float scroll*/ ) {

        GLES30.glUseProgram(mProgram);
        mPositionHandle = GLES30.glGetAttribLocation(mProgram,"vPosition");
        GLES30.glEnableVertexAttribArray(mPositionHandle);
        GLES30.glVertexAttribPointer(mPositionHandle,COORDS_PER_VERTEX,GLES30.GL_FLOAT,
                false,vertexStride,vertexBuffer);
        mMVPMatrixHandle = GLES30.glGetUniformLocation(mProgram,"uMVPMatrix");
        GameRenderer.checkGLError("glGetUniformLocation");
        GLES30.glUniformMatrix4fv(mMVPMatrixHandle,1,false,mVPMatrix,0);
        GameRenderer.checkGLError("glUniformMatrix4fv");
        GLES30.glDrawElements(GLES30.GL_TRIANGLES,drawOrder.length,
                GLES30.GL_UNSIGNED_SHORT,drawListBuffer);
        GLES30.glDisableVertexAttribArray(mPositionHandle);


    }
}
