package com.sandipbhattacharya.onlinequizappglide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView ivShowImage;
    ArrayList<String> celebNames = new ArrayList<>();
    ArrayList<String> newCelebNames = new ArrayList<>();
    HashMap<String, String> map = new HashMap<>();
    int index;
    Random random;
    String[] answers = new String[4];
    Button btn1, btn2, btn3, btn4, btnRestart;
    int points = 0;
    TextView tvPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btnRestart = findViewById(R.id.btnRestart);
        tvPoints = findViewById(R.id.tvPoints);
        celebNames.add("Aamir Khan");
        celebNames.add("Akshay Kumar");
        celebNames.add("Mahendra Singh Dhoni");
        celebNames.add("Ranveer Singh");
        celebNames.add("Sachin Tendulkar");
        celebNames.add("Shahrukh Khan");
        celebNames.add("Virat Kohli");
        index = 0;
        map.put(celebNames.get(0), "https://sandipbhattacharya.com/android/some-indian-celebs/aamir-khan.png");
        map.put(celebNames.get(1), "https://sandipbhattacharya.com/android/some-indian-celebs/akshay-kumar.png");
        map.put(celebNames.get(2), "https://sandipbhattacharya.com/android/some-indian-celebs/mahendra-singh-dhoni.png");
        map.put(celebNames.get(3), "https://sandipbhattacharya.com/android/some-indian-celebs/ranveer-singh.png");
        map.put(celebNames.get(4), "https://sandipbhattacharya.com/android/some-indian-celebs/sachin-tendulkar.png");
        map.put(celebNames.get(5), "https://sandipbhattacharya.com/android/some-indian-celebs/shahrukh-khan.png");
        map.put(celebNames.get(6), "https://sandipbhattacharya.com/android/some-indian-celebs/virat-kohli.png");
        Collections.shuffle(celebNames);
        random = new Random();
        generateQuestions(index);
    }

    private void generateQuestions(int index) {
        Glide.with(this)
                .asBitmap()
                .load(map.get(celebNames.get(index)))
                .error(R.drawable.not_found)
                .into(ivShowImage);
        newCelebNames = (ArrayList<String>) celebNames.clone();
        newCelebNames.remove(index);
        Collections.shuffle(newCelebNames);
        int correctAnswerPosition = random.nextInt(4);
        for(int i=0; i < 4; i++){
            if(i == correctAnswerPosition){
                answers[i] = celebNames.get(index);
            } else{
                answers[i] = newCelebNames.get(i);
            }
        }
        btn1.setText(answers[0]);
        btn2.setText(answers[1]);
        btn3.setText(answers[2]);
        btn4.setText(answers[3]);
        btnRestart.setVisibility(View.GONE);
    }

    public void answerSelected(View view) {
        String answer = ((Button) view).getText().toString();
        if(answer.equals(celebNames.get(index))){
            points++;
            tvPoints.setText(points + "/7");
        }
        index++;
        if(index > celebNames.size() - 1){
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            btnRestart.setVisibility(View.VISIBLE);
        } else{
            generateQuestions(index);
        }
    }

    public void restart(View view) {
        if(index > 6){
            index = 0;
            points = 0;
            ivShowImage.setVisibility(View.VISIBLE);
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            btn4.setVisibility(View.VISIBLE);
            tvPoints.setText(points + "/7");
            Collections.shuffle(celebNames);
        }
        generateQuestions(index);
    }
}