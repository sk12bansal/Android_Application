package com.suraj.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView sumTextview;
    TextView resultTextview;
    TextView scoreTextView;
    TextView timerTextView,timeout;
    Button playagainButton,startButton;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    Button button0,button1,button2,button3;
    int locationOfCorrectAnswer;
    int score=0,numberQuestion=0;


    public  void playAgain(View v){

        score=0;
        numberQuestion=0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextview.setText("");
        playagainButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        generateQuestion();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millUntilFinished) {
                timerTextView.setText(String.valueOf(millUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timeout.setVisibility(View.VISIBLE);
                playagainButton.setVisibility(View.VISIBLE);
                gameRelativeLayout.setVisibility(View.INVISIBLE);
                timerTextView.setText("0s");
                resultTextview.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(numberQuestion));

            }
        }.start();

    }
    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextview.setText("Correct!");

        }
        else
        {
            resultTextview.setText("Wrong!");

        }
        numberQuestion++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberQuestion));
        generateQuestion();

    }

    private void generateQuestion() {
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sumTextview.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();
        int inCorrectAnswer;
        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                inCorrectAnswer=rand.nextInt(41);
                while (inCorrectAnswer==a+b){
                    inCorrectAnswer=rand.nextInt(41);
                }
                answers.add(inCorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(playagainButton);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextview=(TextView)findViewById(R.id.sumTextview);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        resultTextview=(TextView)findViewById(R.id.resultTextview);
        scoreTextView=(TextView)findViewById(R.id.scoreText);
        timerTextView=(TextView)findViewById(R.id.timerText);
        playagainButton=(Button)findViewById(R.id.playagainButton);
        gameRelativeLayout=(RelativeLayout)findViewById(R.id.gameRelativeLayout);
        startButton=(Button)findViewById(R.id.startButton);
        timeout=(TextView)findViewById(R.id.timeout);
    }
}
