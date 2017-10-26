package fr.jmklab.tictactoe_v2;

import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String NONE = "N";
    private static final String PLAYER_ONE = "X";
    private static final String PLAYER_TWO = "O";

    private Boolean GAME_END;
    private String PLAYER;

    private int COLOR_GREEN;

    private String[] THE_GRID = {NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);

        GAME_END = false;
        PLAYER = PLAYER_ONE;
        COLOR_GREEN = ContextCompat.getColor(this, R.color.colorGreen);

    }

    public void onClickStart(View view) {

        String caseID = view.getResources().getResourceName(view.getId()).split("/")[1];

        int thePosition = Integer.parseInt(caseID.split("_")[1]);

        makeMove(thePosition);

    }

    public void onClickRestart(View view) {

        finish();
        startActivity(getIntent());
    }

    private void makeMove(int position) {

        detectWin();

        if (!GAME_END) {

            String n = PLAYER.equals(PLAYER_ONE) ? "HUMAN" : "AI";

            Log.d("LoL", "PREVIOUS CASE STATE " + position + " : " + THE_GRID[position]);

            THE_GRID[position] = PLAYER;

            int resId = getResIdFromId(position);
            Button button = (Button) findViewById(resId);

            button.setEnabled(false);
            button.setText(PLAYER);

            Log.d("LoL", n + " PLAY CASE " + position);

            PLAYER = PLAYER.trim().equals(PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;

            detectWin();

            if (PLAYER.trim().equals(PLAYER_TWO) && !GAME_END) {

                playIA();

            }
        }

    }

    private void playIA() {

        int position;
        String n = "DEFENSE";

        /**
         * Check defense horizontal 0,1 ||
         * Check defense vertical 5,8 ||
         * Check defense diagonal 4,6 &&
         * Grid position equals NONE
         */
        if (
                (
                    (THE_GRID[0].trim().equals(PLAYER_ONE) && THE_GRID[1].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[5].trim().equals(PLAYER_ONE) && THE_GRID[8].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[4].trim().equals(PLAYER_ONE) && THE_GRID[6].trim().equals(PLAYER_ONE))
                ) && THE_GRID[2].trim().equals(NONE)
                ) {
            position = 2;
        }
        /**
         * Check defense horizontal 0,2 ||
         * Check defense vertical 4,7 &&
         * Grid position equals NONE
          */
        else if(
                (
                    (THE_GRID[0].trim().equals(PLAYER_ONE) && THE_GRID[2].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[4].trim().equals(PLAYER_ONE) && THE_GRID[7].trim().equals(PLAYER_ONE))
                ) && THE_GRID[1].trim().equals(NONE)
                ) {
            position = 1;
        }
        /**
         * Check defense horizontal 1,2 ||
         * Check defense vertical 3,4 ||
         * Check defense diagonal 4,8 &&
         * Grid position equals NONE
         */
        else if(
                (
                    (THE_GRID[1].trim().equals(PLAYER_ONE) && THE_GRID[2].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[3].trim().equals(PLAYER_ONE) && THE_GRID[6].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[4].trim().equals(PLAYER_ONE) && THE_GRID[8].trim().equals(PLAYER_ONE))
                ) && THE_GRID[0].trim().equals(NONE)
                ) {
            position = 0;
        }
        /**
         * Check defense horizontal 3,4 ||
         * Check defense vertical 2,8 &&
         * Grid position equals NONE
         */
        else if(
                (
                    (THE_GRID[3].trim().equals(PLAYER_ONE) && THE_GRID[4].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[2].trim().equals(PLAYER_ONE) && THE_GRID[8].trim().equals(PLAYER_ONE))
                ) && THE_GRID[5].trim().equals(NONE)
                ) {
            position = 5;
        }
        /**
         * Check defense horizontal 3,5 ||
         * Check defense vertical 1,7 ||
         * Check defense diagonal 0,8 ||
         * Check defense diagonal 2,6 &&
         * Grid position equals NONE
         */
        else if(
                (
                    (THE_GRID[3].trim().equals(PLAYER_ONE) && THE_GRID[5].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[1].trim().equals(PLAYER_ONE) && THE_GRID[7].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[0].trim().equals(PLAYER_ONE) && THE_GRID[8].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[2].trim().equals(PLAYER_ONE) && THE_GRID[6].trim().equals(PLAYER_ONE))
                ) && THE_GRID[4].trim().equals(NONE)
                ) {
            position = 4;
        }
        /**
         * Check defense horizontal 4,5 ||
         * Check defense vertical 0,6 &&
         * Grid position equals NONE
         */
        else if (
                (
                    (THE_GRID[4].trim().equals(PLAYER_ONE) && THE_GRID[5].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[0].trim().equals(PLAYER_ONE) && THE_GRID[6].trim().equals(PLAYER_ONE))
                ) && THE_GRID[3].trim().equals(NONE)
                ) {
            position = 3;
        }
        /**
         * Check defense horizontal 6,7 ||
         * Check defense vertical 2,5 ||
         * Check defense diagonal 0,4 &&
         * Grid position equals NONE
         */
        else if(
                (
                    (THE_GRID[6].trim().equals(PLAYER_ONE) && THE_GRID[7].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[2].trim().equals(PLAYER_ONE) && THE_GRID[5].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[0].trim().equals(PLAYER_ONE) && THE_GRID[4].trim().equals(PLAYER_ONE))
                ) && THE_GRID[8].trim().equals(NONE)
                ) {
            position = 8;
        }
        /**
         * Check defense horizontal 7,8 ||
         * Check defense vertical 0,3 ||
         * Check defense diagonal 2,4 &&
         * Grid position equals NONE
         */
        else if(
                (
                    (THE_GRID[7].trim().equals(PLAYER_ONE) && THE_GRID[8].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[0].trim().equals(PLAYER_ONE) && THE_GRID[3].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[2].trim().equals(PLAYER_ONE) && THE_GRID[4].trim().equals(PLAYER_ONE))
                ) && THE_GRID[6].trim().equals(NONE)
                ) {
            position = 6;
        }
        /**
         * Check defense horizontal 6,8 ||
         * Check defense vertical 1,4 &&
         * Grid position equals NONE
         */
        else if(
                (
                    (THE_GRID[6].trim().equals(PLAYER_ONE) && THE_GRID[8].trim().equals(PLAYER_ONE)) ||
                    (THE_GRID[1].trim().equals(PLAYER_ONE) && THE_GRID[4].trim().equals(PLAYER_ONE))
                ) && THE_GRID[7].trim().equals(NONE)
                ) {
            position = 7;
        }
        /**
         * Random
         */
        else {
            n = "NORMAL";
            position = getRandom();
        }

        Log.d("LoL", "GAME TYPE " + n);
        makeMove(position);

    }

    private int getRandom() {

        Random random = new Random();

        int position = random.nextInt(THE_GRID.length);

        if (THE_GRID[position].trim().equals(NONE)) {

            return position;

        } else {

            return getRandom();
        }

    }

    private void detectEquality() {

        for (String a : THE_GRID) {

            if (a.trim().equals(NONE)) {

                return;
            }

        }

        GAME_END = true;
        PLAYER = NONE;

    }

    private void detectWin() {

        if (!GAME_END) {

            // Line 0,1,2
            if (
                (THE_GRID[0].trim().equals(PLAYER_ONE) && THE_GRID[1].trim().equals(PLAYER_ONE) && THE_GRID[2].trim().equals(PLAYER_ONE)) ||
                (THE_GRID[0].trim().equals(PLAYER_TWO) && THE_GRID[1].trim().equals(PLAYER_TWO) && THE_GRID[2].trim().equals(PLAYER_TWO))
                    ) {
                    GAME_END = true;
                    PLAYER = THE_GRID[0].trim().equals(PLAYER_ONE) ? PLAYER_ONE : PLAYER_TWO;
                    colorTextButton(new int[]{0, 1, 2});
            }

            // Line 3,4,5
            else if (
                (THE_GRID[3].trim().equals(PLAYER_ONE) && THE_GRID[4].trim().equals(PLAYER_ONE) && THE_GRID[5].trim().equals(PLAYER_ONE)) ||
                (THE_GRID[3].trim().equals(PLAYER_TWO) && THE_GRID[4].trim().equals(PLAYER_TWO) && THE_GRID[5].trim().equals(PLAYER_TWO))
                    ) {
                    GAME_END = true;
                    PLAYER = THE_GRID[3].trim().equals(PLAYER_ONE) ? PLAYER_ONE : PLAYER_TWO;
                    colorTextButton(new int[]{3, 4, 5});
            }

            // Line 6,7,8
            else if (
                (THE_GRID[6].trim().equals(PLAYER_ONE) && THE_GRID[7].trim().equals(PLAYER_ONE) && THE_GRID[8].trim().equals(PLAYER_ONE)) ||
                (THE_GRID[6].trim().equals(PLAYER_TWO) && THE_GRID[7].trim().equals(PLAYER_TWO) && THE_GRID[8].trim().equals(PLAYER_TWO))
                    ) {
                    GAME_END = true;
                    PLAYER = THE_GRID[6].trim().equals(PLAYER_ONE) ? PLAYER_ONE : PLAYER_TWO;
                    colorTextButton(new int[]{6, 7, 8});
            }

            // Line 0,3,6
            else if (
                (THE_GRID[0].trim().equals(PLAYER_ONE) && THE_GRID[3].trim().equals(PLAYER_ONE) && THE_GRID[6].trim().equals(PLAYER_ONE)) ||
                (THE_GRID[0].trim().equals(PLAYER_TWO) && THE_GRID[3].trim().equals(PLAYER_TWO) && THE_GRID[6].trim().equals(PLAYER_TWO))
                    ) {
                    GAME_END = true;
                    PLAYER = THE_GRID[0].trim().equals(PLAYER_ONE) ? PLAYER_ONE : PLAYER_TWO;
                    colorTextButton(new int[]{0, 3, 6});
            }

            // Line 1,4,7
            else if (
                (THE_GRID[1].trim().equals(PLAYER_ONE) && THE_GRID[4].trim().equals(PLAYER_ONE) && THE_GRID[7].trim().equals(PLAYER_ONE)) ||
                (THE_GRID[1].trim().equals(PLAYER_TWO) && THE_GRID[4].trim().equals(PLAYER_TWO) && THE_GRID[7].trim().equals(PLAYER_TWO))
                    ) {
                    GAME_END = true;
                    PLAYER = THE_GRID[1].trim().equals(PLAYER_ONE) ? PLAYER_ONE : PLAYER_TWO;
                    colorTextButton(new int[]{1, 4, 7});
            }

            // Line 2,5,8
            else if (
                (THE_GRID[2].trim().equals(PLAYER_ONE) && THE_GRID[5].trim().equals(PLAYER_ONE) && THE_GRID[8].trim().equals(PLAYER_ONE)) ||
                (THE_GRID[2].trim().equals(PLAYER_TWO) && THE_GRID[5].trim().equals(PLAYER_TWO) && THE_GRID[8].trim().equals(PLAYER_TWO))
                    ) {
                    GAME_END = true;
                    PLAYER = THE_GRID[2].trim().equals(PLAYER_ONE) ? PLAYER_ONE : PLAYER_TWO;
                    colorTextButton(new int[]{2, 5, 8});
            }

            // Diagonal 0,4,8
            else if (
                (THE_GRID[0].trim().equals(PLAYER_ONE) && THE_GRID[4].trim().equals(PLAYER_ONE) && THE_GRID[8].trim().equals(PLAYER_ONE)) ||
                (THE_GRID[0].trim().equals(PLAYER_TWO) && THE_GRID[4].trim().equals(PLAYER_TWO) && THE_GRID[8].trim().equals(PLAYER_TWO))
                    ) {
                    GAME_END = true;
                    PLAYER = THE_GRID[0].trim().equals(PLAYER_ONE) ? PLAYER_ONE : PLAYER_TWO;
                    colorTextButton(new int[]{0, 4, 8});
            }

            // Diagonal 2,4,6
            else if (
                (THE_GRID[2].trim().equals(PLAYER_ONE) && THE_GRID[4].trim().equals(PLAYER_ONE) && THE_GRID[6].trim().equals(PLAYER_ONE)) ||
                (THE_GRID[2].trim().equals(PLAYER_TWO) && THE_GRID[4].trim().equals(PLAYER_TWO) && THE_GRID[6].trim().equals(PLAYER_TWO))
                    ) {
                    GAME_END = true;
                    PLAYER = THE_GRID[2].trim().equals(PLAYER_ONE) ? PLAYER_ONE : PLAYER_TWO;
                    colorTextButton(new int[]{2, 4, 6});
            } else {

                detectEquality();
            }

            if (GAME_END) {
                displayWin();
                Log.d("LoL", "GAME OVER ");
            }

        }

    }

    private void displayWin() {

        String message;

        if (PLAYER.trim().equals(PLAYER_ONE)) {

            message = getResources().getString(R.string.victoryPlayer);

        } else if (PLAYER.trim().equals(PLAYER_TWO)) {

            message = getResources().getString(R.string.victoryIA);

        } else {

            message = getResources().getString(R.string.victoryEquality);

        }

        TextView textView = (TextView) findViewById(R.id.gameStatus);
        Button button = (Button) findViewById(R.id.restart_game);
        textView.setText(message);


        textView.setTextColor(COLOR_GREEN);
        button.setEnabled(true);

    }


    private void colorTextButton(int[] ids) {

        for (int id : ids) {

            Button button = (Button) findViewById(getResIdFromId(id));
            button.setTextColor(COLOR_GREEN);

        }

    }

    private int getResIdFromId(int id) {

        return getResources().getIdentifier("case_" + id, "id", getPackageName());

    }
}
