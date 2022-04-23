package a10.jflores.mindmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    private GameView gameView; //2. need to make make new gameview on oncreate function
    //3. need to transfer to this gameactivity from our main activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        gameView = new GameView(this, screenSize);
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        // 1. need to call gameviews pause so need private member of type gameview
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}