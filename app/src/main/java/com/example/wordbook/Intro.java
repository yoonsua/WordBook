package com.example.wordbook;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Intro extends AppCompatActivity {
    TextView mainlogo;
    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            // 4초 뒤에 다음화면(MainActivity)으로 넘어가기 - Handler 사용
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent); // 다음 화면으로 넘어가기
            finish(); // Activity 화면 제거
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); // xml과 java소스 연결
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        // 다시 화면에 들어왔을 때 예약 걸어주기
        handler.postDelayed(r, 3000); // 3초 뒤에 Runnable 객체 수행
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 화면 벗어나면, handler에 예약해놓은 작업 취소
        handler.removeCallbacks(r); // 예약 취소
    }
}
