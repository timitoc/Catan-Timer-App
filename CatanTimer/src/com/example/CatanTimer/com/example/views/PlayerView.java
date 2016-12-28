package com.example.CatanTimer.com.example.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.CatanTimer.R;
import com.example.CatanTimer.com.example.models.PlayerItem;

/**
 * Created by timi on 26.12.2016.
 */
public class PlayerView extends LinearLayout {

    private TextView playerNameTextView;
    private TextView playerTimeTextView;
    public PlayerItem playerItem;

    public PlayerView(Context context) {
        super(context);
        init();
    }

    public PlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlayerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.player, this);
        playerNameTextView = (TextView) findViewById(R.id.pl_name);
        playerTimeTextView = (TextView) findViewById(R.id.pl_time);
    }

    public void setContent(PlayerItem item) {
        this.playerItem = item;
        this.invalidate();
    }

    @Override
    public void invalidate() {
        playerNameTextView.setText(playerItem.name);
        playerTimeTextView.setText(playerItem.timeLeft.toString());

        super.invalidate();
    }

    public void giveTurn() {
        this.setBackgroundResource(R.drawable.bordered_background);
    }

    public void getTurn() {
        this.setBackgroundColor(Color.parseColor("#1515ff"));
    }
}
