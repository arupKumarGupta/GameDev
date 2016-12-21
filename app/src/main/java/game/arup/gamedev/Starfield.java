package game.arup.gamedev;

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


    public void draw(float[] mVPMatrix) {


    }
}
