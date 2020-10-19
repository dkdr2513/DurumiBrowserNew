package edu.byu.cs240.durumibrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton SearchButtonHome;
    private EditText InputURL;

    private Button facebook_btn;
    private Button youtube_btn;
    private Button instagram_btn;
    private Button google_btn;
    private Button naver_btn;
    private Button lol_btn;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        loadingBar = new ProgressDialog(this);

        SearchButtonHome = (ImageButton) findViewById(R.id.search_btn);
        InputURL = (EditText) findViewById(R.id.url_txt);
        facebook_btn = (Button) findViewById(R.id.button1);
        youtube_btn = (Button) findViewById(R.id.button2);
        instagram_btn = (Button) findViewById(R.id.button3);
        google_btn = (Button) findViewById(R.id.button4);
        naver_btn = (Button) findViewById(R.id.button5);
        lol_btn = (Button) findViewById(R.id.button6);

//        loadingBar.setTitle("개구리 브라우저");
//        loadingBar.setMessage("반갑다냥");
//        loadingBar.show();

        SearchButtonHome.setOnClickListener(this);
        facebook_btn.setOnClickListener(this);
        youtube_btn.setOnClickListener(this);
        instagram_btn.setOnClickListener(this);
        google_btn.setOnClickListener(this);
        naver_btn.setOnClickListener(this);
        lol_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == SearchButtonHome) {
//            Intent SearchWeb = new Intent(MainActivity.this, URLSearch.class);
//            startActivity(SearchWeb);
            OpenWebSite();
        }
        if (view == facebook_btn) {
            Intent facebook = new Intent (MainActivity.this, URLSearch.class);
            facebook.putExtra("url_address", "https://www.facebook.com");
            startActivity(facebook);
        }
        if (view == youtube_btn) {
            Intent youtube = new Intent (MainActivity.this, URLSearch.class);
            youtube.putExtra("url_address", "https://www.youtube.com");
            startActivity(youtube);
        }
        if (view == instagram_btn) {
            Intent instagram = new Intent (MainActivity.this, URLSearch.class);
            instagram.putExtra("url_address", "https://www.instagram.com");
            startActivity(instagram);
        }
        if (view == google_btn) {
            Intent google = new Intent (MainActivity.this, URLSearch.class);
            google.putExtra("url_address", "https://www.google.com");
            startActivity(google);
        }
        if (view == naver_btn) {
            Intent naver = new Intent (MainActivity.this, URLSearch.class);
            naver.putExtra("url_address", "https://www.naver.com");
            startActivity(naver);
        }
        if (view == lol_btn) {
            Intent lol = new Intent (MainActivity.this, URLSearch.class);
            lol.putExtra("url_address", "https://www.leagueoflegends.com");
            startActivity(lol);
        }

    }

    private void OpenWebSite() {
//        loadingBar.setTitle("루딩주우우웅....:d");
//        loadingBar.setMessage("조급해씨, 참아.");
//        loadingBar.show();

        String URL_address = InputURL.getText().toString();
        if (TextUtils.isEmpty(URL_address)) {
            Toast empty = Toast.makeText(MainActivity.this, "가고싶은데가 어디냐아ㅏㅏㅏ", Toast.LENGTH_LONG);
            empty.show();
        } else {
            String url_Without_https = URL_address.replaceAll("https://www.", ""); // 뒤ㅏ에꺼랑 바꿈
            String https = "https://";
            String www = "www.";

            Intent search = new Intent(MainActivity.this, URLSearch.class);
            search.putExtra("url_address", https+www+url_Without_https);
            overridePendingTransition(0, 0);
            search.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(search);

            InputURL.setText("");
            InputURL.requestFocus();
        }
    }
}