package com.example.android.endoftheworldpreparednessquiz;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }
    /* Utility Methods
    //
    // */


    public void hideKeyboard(View view) {
        Context context = this;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void makeVisibleSummary(View view) {
        View summary = findViewById(R.id.summary);
        summary.setVisibility(View.VISIBLE);
    }

    public void makeVisibleHeader(View view) {
        View header = findViewById(R.id.header);
        header.setVisibility(View.VISIBLE);
    }

    /* End Utility Methods//
    //
    // */

    int survivorScore = 0;


    /* Counts RadioButton scores
    /
    /
    /
    */


    public int counter(View view) {
        boolean correctAnswer = ((RadioButton) view).isChecked();
        if (correctAnswer) {
            return 1;
        } else {
            return 0;
        }
    }
    /* Creates CheckBox Scores
    /
    /
    /
    */

    public int veryRightCounter(View view) {
        boolean checkboxVeryRight = ((CheckBox) view).isChecked();
        if (checkboxVeryRight) {
            return 2;
        } else {
            return 0;
        }
    }

    public int veryWrongCounter(View view) {
        boolean checkboxVeryWrong = ((CheckBox) view).isChecked();
        if (checkboxVeryWrong) {
            return -2;
        } else {
            return 0;
        }
    }

    public int rightCounter(View view) {
        boolean checkboxRight = ((CheckBox) view).isChecked();
        if (checkboxRight) {
            return 1;
        } else {
            return 0;
        }
    }

/* Makes header and summary view visible, runs Create Score, Create Summary, Create Proclamation methods
/  Makes toast
/
/
*/


    public void submit(View view) {
        View header = findViewById(R.id.header);
        makeVisibleHeader(header);

        View summary = findViewById(R.id.summary);
        makeVisibleSummary(summary);



        survivorScore = counter(findViewById(R.id.q1a3)) + counter(findViewById(R.id.q2a1));
        survivorScore = survivorScore + counter(findViewById(R.id.q3a2));
        survivorScore = survivorScore + counter(findViewById(R.id.q4a2));
        survivorScore = survivorScore + counter(findViewById(R.id.q5a3));
        survivorScore = survivorScore + counter(findViewById(R.id.q6a2));
        survivorScore = survivorScore + counter(findViewById(R.id.q7a2));
        survivorScore = survivorScore + veryWrongCounter(findViewById(R.id.q8a1));
        survivorScore = survivorScore + veryRightCounter(findViewById(R.id.q8a2));
        survivorScore = survivorScore + rightCounter(findViewById(R.id.q8a3));
        survivorScore = survivorScore + veryRightCounter(findViewById(R.id.q8a4));

        EditText questionNine = findViewById(R.id.questionNineAnswer);
        String questionNineAnswer = questionNine.getText().toString();
        if(questionNineAnswer.contains("rice")) {
            survivorScore = survivorScore + 100;

        }

        Log.v("MainActivity", "survivor score: " + survivorScore);
        displayMessage(createSummary(survivorScore));
        displayProclaim(createProclaim());
        displayScore(createScore());

        Toast toast = Toast.makeText(this, createScore(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 150);
        toast.show();


    }

    /* Creates Name Proclamation string
    /
    /
    /
    */
    public String createProclaim() {
        EditText name = findViewById(R.id.name);
        String userName = name.getText().toString();
        String outcome = "";
        if (survivorScore <= 4) {
            outcome = "You won't fare well, ";
        } else {
            outcome = "You will have a good run of it, ";
        }
        if (survivorScore == 11) {
            outcome = "You seem very prepared, ";
        }
        if (survivorScore == 12) {
            outcome = "You will rise above the rest, ";
        }

        if (survivorScore >= 13) {
            outcome = "You are a legend, ";
        }
        return outcome + userName;
    }

/* Creates Score string
/
/
/
*/

    public String createScore() {
        String score = "SCORE: " + survivorScore + "/12";
        return score;
    }


/* Creates Summary string
/
/
/
*/


    public String createSummary(int survivorScore) {
        String survivorSummary = "";
        if (survivorScore <= 4) {
            survivorSummary = getString(R.string.low_score);
        } else {
            survivorSummary = getString(R.string.med_score);
        }
        if (survivorScore == 11) {
            survivorSummary = getString(R.string.high_score);
        }
        if (survivorScore == 12) {
            survivorSummary = getString(R.string.highest_score);
        }
        if (survivorScore >= 13) {
            survivorSummary = getString(R.string.legend_score);
        }
        return survivorSummary;
    }

    private void displayProclaim(String messageP) {
        TextView proclaim = (TextView) findViewById(R.id.proclaim);
        proclaim.setText(messageP);
    }

    private void displayScore(String messageS) {
        TextView score = (TextView) findViewById(R.id.score);
        score.setText(messageS);
    }

    private void displayMessage(String message) {
        TextView summary = (TextView) findViewById(R.id.summary);
        summary.setText(message);
    }


}
