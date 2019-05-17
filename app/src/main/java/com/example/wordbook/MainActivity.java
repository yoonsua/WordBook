package com.example.wordbook;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText search_word;
    Button search_button;
    ListView wordlistview;
    String search_value;

    XmlPullParser xpp;
    String key = "8EF659444211743AA4FD058B030395E4";

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
    }

    private void setViews() {
        search_word = (EditText) findViewById(R.id.search_word);
        search_button = (Button) findViewById(R.id.search_button);
        wordlistview = (ListView) findViewById(R.id.wordlistview);

        search_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                search_value = search_word.getText().toString();
                searchMethod(search_value);
                System.out.println(search_value);
                break;
        }
    }

    public void searchMethod(String search_InValue) {
        StrictMode.enableDefaults();
        // ArrayList 설정
        ArrayList<String> wordlist = new ArrayList<String>();
        ArrayAdapter<String> Adapter;
        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wordlist);

        // api xml 값 확인 변수
        boolean initem = false, inword = false, indefinition = false, inpos = false;
        // 값 저장하는 변수
        String word = null, definition = null, pos = null;

        String queryUrl = "https://opendict.korean.go.kr/api/search?certkey_no=710&key=8EF659444211743AA4FD058B030395E4&target_type=search&part=word&q=" + search_InValue + "&sort=dict&start=1&num=20";

        try {
            URL url = new URL(queryUrl);

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("word")) {
                            inword = true;
                        }
                        if(parser.getName().equals("definition")) {
                            indefinition = true;
                        }
                        if(parser.getName().equals("pos")) {
                            inpos = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(inword) {
                            word = parser.getText();
                            inword = false;
                        }
                        if(indefinition) {
                            definition = parser.getText();
                            indefinition = false;
                        }
                        if(inpos) {
                            pos = parser.getText();
                            inpos = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")) {
                            wordlist.add(word + "\n" + definition);
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            System.out.println("error !");
        }
        wordlistview.setAdapter(Adapter);
    }
}
