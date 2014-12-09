package kr.ac.uos.cs.thechoice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;


public class SplashActivity extends Activity {

    ImageView splashImage ;
    Button btnLogin, btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout);


        btnJoin = (Button)findViewById(R.id.btn_join);
        btnLogin = (Button)findViewById(R.id.btn_login);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , RegisterActivity.class);
                startActivity(i);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , LoginActivity.class);
                startActivity(i);
            }
        });
    }

}
