package kr.ac.uos.cs.thechoice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class RegisterActivity extends Activity {

    ImageView btnNext;
    RadioButton radioUserType, radioGender;
    EditText etNickName, etPassWd, etChkPassWd, etRecommender;
    String uGender, uType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        btnNext = (ImageView)findViewById(R.id.register_btn_next);
        radioUserType = (RadioButton)findViewById(R.id.register_radio_usertype_consumer);
        radioGender = (RadioButton)findViewById(R.id.register_radio_gender_male);

        etNickName = (EditText)findViewById(R.id.register_id);
        etPassWd = (EditText)findViewById(R.id.register_password);
        etChkPassWd = (EditText)findViewById(R.id.register_confirm);
        etRecommender = (EditText)findViewById(R.id.register_recommender);






        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adBuilder = new AlertDialog.Builder(v.getContext());

                // chk user information for registration
                if(radioGender.isChecked()) {
                    uGender = "male";
                    Log.i("log ", "남자");
                }else{
                    uGender = "female";
                }

                if(radioUserType.isChecked()){
                    Log.i("log ", "구매자");
                    uType = "consumer";
                }else{
                    uType = "seller";
                }


                // empty check
                if(etNickName.getText().toString().length() == 0){
                    adBuilder.setTitle("닉네임을 입력하세요.");
                    adBuilder.setPositiveButton("확인",null);
                    adBuilder.show();
                }else{
                    if(etPassWd.getText().toString().length() == 0 || etChkPassWd.getText().toString().length() == 0){
                        adBuilder.setTitle("비밀번호(비밀번호 확인)란을 입력하세요.");
                        adBuilder.setPositiveButton("확인",null);
                        adBuilder.show();
                    }else{
                        // password confirm
                        if(!etPassWd.getText().toString().equals(etChkPassWd.getText().toString())){

                            adBuilder.setTitle("비밀번호가 일치하지 않습니다.");
                            adBuilder.setPositiveButton("확인",null);
                            adBuilder.show();

                        }else{ // after password confirmation
                            //id confirm
                            etPassWd.getText().toString();

                            adBuilder.setTitle(etPassWd.getText().toString()+" 는 이미 사용 중 입니다.");
                            adBuilder.setPositiveButton("확인",null);
                            adBuilder.show();

                            addNewUser();

                            // register user data

                            finish(); // terminate activity
                        }

                    }

                }



/*
                Intent i = new Intent(getApplicationContext(), CoinActivity.class);
                i.putExtra("userType", userTypeSelectId);
                i.putExtra("nickName", etNickName.getText().toString());
                i.putExtra("passWord", etPassWd.getText().toString());
                i.putExtra("chkPassWord", etChkPassWd.getText().toString());
                i.putExtra("recommender", etRecommender.getText().toString());

                startActivity(i);
*/

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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


    private void addNewUser()
    {
        String serverHostAddr = "http://dlacodbs90.cafe24.com/";
//http://211.189.19.202/getarticle.php/
        AsyncHttpClient client = new AsyncHttpClient();
        String url = serverHostAddr+"updateuser.php";

        Log.i("KC",url);

        RequestParams entity = new RequestParams();

        //   EditText etNickName, etPassWd, etChkPassWd, etRecommender;
        entity.put("user_id",etNickName.getText().toString());
        entity.put("user_password",etPassWd.getText().toString());
        if(uGender.toString().equals("male")) // male 0
            entity.put("user_gender",0);
        else                                    // female 1
            entity.put("user_gender",1);

        if(uType.toString().equals("seller")) // seller code 0
            entity.put("user_type",0);
        else                                    // consumer code 1
            entity.put("user_type",1);

        entity.put("user_recommender",etRecommender.getText().toString());


        client.post(url, entity, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, JSONObject response) {
                Log.i("log","insert Complete");
            }


        });

    }
}
