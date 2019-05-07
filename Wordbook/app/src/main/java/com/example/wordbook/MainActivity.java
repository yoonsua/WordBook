package com.example.wordbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ImageView wordquiz;
    Button search_start;
    EditText search_text;
    ListView wordlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
    }

    private void setViews() {
        wordquiz = (ImageView)findViewById(R.id.wordquiz);
        search_start = (Button) findViewById(R.id.search_start);
        search_text = (EditText) findViewById(R.id.search_text);
        wordlist = (ListView) findViewById(R.id.wordlist);


    }
}
