package kr.ac.uos.cs.thechoice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;


public class SplashActivity extends Activity {

    ImageView splashImage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout);

        splashImage = (ImageView) findViewById(R.id.splash_logo);
        splashImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // KC수정
                //Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                Intent i = new Intent(getApplicationContext(), ArticleListActivity.class);

                startActivity(i);
            }
        });

    }

}
