package kr.ac.uos.cs.thechoice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;


public class CoinActivity extends Activity {

    ImageView splashImg;
    Button btnGotoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_coin);

        splashImg = (ImageView)findViewById(R.id.coindescription_img);

        splashImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recvIntent = getIntent();
                int userType = recvIntent.getIntExtra("userType", R.id.register_radio_usertype_consumer);
                Intent i = null;
                switch(userType){
                    case R.id.register_radio_usertype_consumer:
                        i = new Intent(getApplicationContext(), ConsumerProfileActivity.class);
                        startActivity(i);
                        break;

                    case R.id.register_radio_usertype_seller:
                        i = new Intent(getApplicationContext(), SellerProfileActivity.class);
                        startActivity(i);
                        break;
                }

            }
        });


        btnGotoLogin = (Button)findViewById(R.id.btn_goto_login);

        btnGotoLogin.setOnClickListener(new View.OnClickListener() { // 로그인 화면으로 이동
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
