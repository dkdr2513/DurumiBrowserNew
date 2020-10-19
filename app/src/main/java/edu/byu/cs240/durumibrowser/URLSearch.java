package edu.byu.cs240.durumibrowser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

public class URLSearch extends AppCompatActivity implements View.OnClickListener{
    private ImageButton SearchURLButton;
    private EditText URLInput;
    private ImageButton HomeButton;
    private WebView SearchWebAddress;
    private ImageButton BackButton;

    String url;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_r_l_searcch);

        loadingBar = new ProgressDialog(this);

        BackButton = (ImageButton) findViewById(R.id.back_btn);
        SearchURLButton = (ImageButton) findViewById(R.id.search_btn);
        URLInput = (EditText) findViewById(R.id.url_txt);
        HomeButton = (ImageButton) findViewById(R.id.home_btn);
        SearchWebAddress = (WebView) findViewById(R.id.web_browser);

        url = getIntent().getExtras().get("url_address").toString();
        URLInput.setText(url);


        WebSettings webSettings = SearchWebAddress.getSettings();
        // prevent web address to open other area;
        webSettings.setJavaScriptEnabled(true);
        SearchWebAddress.loadUrl(url);
        SearchWebAddress.setWebViewClient(new WebViewClient());

//        loadingBar.setTitle("루딩주우우웅....:d");
//        loadingBar.setMessage("조급해씨, 참아.");
//        loadingBar.show();

        SearchURLButton.setOnClickListener(this);
        HomeButton.setOnClickListener(this);
        BackButton.setOnClickListener(this);

        URLInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    SearchWebAddress();
                }
                return false;
            }
        });

    }

    public void backPressed() {
        if (SearchWebAddress.canGoBack()) {
            SearchWebAddress.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        if (SearchWebAddress.canGoBack()) {
            overridePendingTransition(0, 0);
            SearchWebAddress.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    public void onEnterPressed() {

    }

    @Override
    public void onClick(View view) {
        if (view == HomeButton) {
            finish();

            Intent home_page = new Intent(URLSearch.this, MainActivity.class);
            overridePendingTransition(0, 0);
            home_page.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(home_page);
        }
        if (view == SearchURLButton) {
            SearchWebAddress();
        }
        if (view == BackButton) {
            backPressed();
        }
    }

    private void SearchWebAddress() {
//        loadingBar.setTitle("루딩주우우웅....:d");
//        loadingBar.setMessage("조급해씨, 참아.");
//        loadingBar.show();

        String URL_address = URLInput.getText().toString();
        if (TextUtils.isEmpty(URL_address)) {
            Toast empty = Toast.makeText(URLSearch.this, "가고싶은데가 어디냐아ㅏㅏㅏ", Toast.LENGTH_LONG);
            empty.show();
        } else {
            String url_Without_https = URL_address.replaceAll("https://www.", ""); // 뒤ㅏ에꺼랑 바꿈
            String https = "https://";
            String www = "www.";

            Intent search = new Intent(URLSearch.this, URLSearch.class);
            search.putExtra("url_address", https+www+url_Without_https);
            overridePendingTransition(0, 0);
            search.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(search);

            URLInput.setText("");
            URLInput.requestFocus();
        }
    }
}