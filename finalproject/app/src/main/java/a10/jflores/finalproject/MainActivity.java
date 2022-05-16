package a10.jflores.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static TextView text_score, best_score_text, game_over_score_text, title_view;
    public static RelativeLayout game_over_rl;
    private GameView game_view;
    private static Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        ConstValues.SCREEN_WIDTH = displaymetrics.widthPixels;
        ConstValues.SCREEN_HEIGHT = displaymetrics.heightPixels;
        setContentView(R.layout.activity_main);

        start_button = findViewById(R.id.start_button);
        title_view = findViewById(R.id.title_view);
        text_score = findViewById(R.id.text_score);
        best_score_text = findViewById(R.id.best_score_text);
        game_over_score_text = findViewById(R.id.game_over_score_text);
        game_over_rl = findViewById(R.id.game_over_rl);
        game_view = findViewById(R.id.game_view);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game_view.setStart(true);
                text_score.setVisibility(View.VISIBLE);
                start_button.setVisibility(View.INVISIBLE);
                title_view.setVisibility(View.INVISIBLE);
            }
        });

        game_over_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_button.setVisibility(View.VISIBLE);
                title_view.setVisibility(View.VISIBLE);
                game_over_rl.setVisibility(View.INVISIBLE);
                game_view.setStart(false);
                game_view.reset();
            }
        });
    }
}