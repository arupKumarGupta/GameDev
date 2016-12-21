package game.arup.gamedev;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private GameView myGameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        initGameView();

    }

    private void initGameView() {
        myGameView = new GameView(this);
        setContentView(myGameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*TODO: The following call pauses the redenring thread*/
           /*If your OpenGl application is memory intentsive then
            you should consider deallocating objects that consume
            significant memory here*/
        myGameView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: the following call resumes paused rendering thread
        /*If you de-allocatied objects in onPause then Re-allocate them here*/
        myGameView.onResume();
    }
}
