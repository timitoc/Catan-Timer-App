package com.example.CatanTimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.CatanTimer.com.example.models.PlayerItem;
import com.example.CatanTimer.com.example.utils.Global;
import com.example.CatanTimer.com.example.utils.TimeStat;
import com.example.CatanTimer.com.example.views.PlayerView;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }

    private int nrOfPlayers;
    private PlayerView[] playerViews;
    private TextView[] dices;
    private TimeStat initialTimeStat;
    private Button startButton, nextButton, rollButton;
    private int currentPlayer;
    private boolean running;

    Handler mHandler;

    private void init() {
        nrOfPlayers = 4;
        currentPlayer = 0;
        running = false;
        initialTimeStat = new TimeStat(0, 30, 0);
        playerViews = new PlayerView[nrOfPlayers];
        for (int i = 0; i < 4; i++) {
            playerViews[i] = (PlayerView) findViewById(getResources().getIdentifier("player" + (i+1), "id", getPackageName()));
            playerViews[i].setContent(new PlayerItem("Player " + (i+1), initialTimeStat.getCopy()));
        }
        playerViews[currentPlayer].getTurn();
        setLogic();
    }

    private void setLogic() {
        startButton = (Button) findViewById(R.id.start_button);
        nextButton = (Button) findViewById(R.id.next_button);
        rollButton = (Button) findViewById(R.id.roll_button);
        dices = new TextView[3];
        dices[0] = (TextView) findViewById(R.id.d1);
        dices[1] = (TextView) findViewById(R.id.d2);
        dices[2] = (TextView) findViewById(R.id.d3);

        mHandler = new Handler();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running) {
                    startButton.setText("Start");
                    pause();
                }
                else {
                    startButton.setText("Pause");
                    start();
                }
                running = !running;
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerViews[currentPlayer].giveTurn();
                currentPlayer = (currentPlayer + 1) % 4;
                playerViews[currentPlayer].getTurn();
            }
        });
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollTheDices();
            }
        });
    }

    private void rollTheDices() {
        dices[0].setText("" + Global.getRand(1, 6));
        dices[1].setText("" + Global.getRand(1, 6));
        dices[2].setBackgroundColor(Global.getRandColor());
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateStatus();
            } finally {
                mHandler.postDelayed(mStatusChecker, 1000);
            }
        }
    };

    void updateStatus() {
        playerViews[currentPlayer].playerItem.timeLeft.decrement();
        playerViews[currentPlayer].invalidate();
    }

    private void pause() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    private void start() {
        mStatusChecker.run();
    }
}
