package kr.ac.uos.cs.thechoice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class LoginActivity extends Activity {

    EditText etNickName, etPassWd;
    Button bt_enter;

    private JSONObject json;
    private JSONArray result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        etNickName = (EditText)findViewById(R.id.et_nickname);
        etPassWd = (EditText)findViewById(R.id.et_passwd);

        bt_enter = (Button)findViewById(R.id.bt_enter);


        // 입장버튼 온 클릭
        bt_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJsonResult(view);

            }
        });

        // 닉네임 포커스 리스너
        etNickName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    Log.i("log", "focused");
                    etNickName.setText("");
                }else{
                    Log.i("log", "unfocused");
                    if(etNickName.getText().length() == 0){
                        etNickName.setText("닉네임");
                    }
                }
            }
        });

        // 비밀번호 포커스 리스너
        etPassWd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    etPassWd.setText("");
                }else{
                    Log.i("log", "unfocused");
                    if(etPassWd.getText().length() == 0){
                        etPassWd.setText("비밀번호");
                    }
                }
            }
        });


    }

    private void getJsonResult(View view)
    {
        String serverHostAddr = "http://dlacodbs90.cafe24.com/";
//http://211.189.19.202/getarticle.php/
        AsyncHttpClient client = new AsyncHttpClient();
        String url = serverHostAddr+"getuser.php";

        final View v = view;

        Log.i("KC",url);

        RequestParams entity = new RequestParams();
        //entity.add("count", "100");
        //entity.add("start_num", "0");
        entity.put("user_id",etNickName.getText());
        entity.put("user_pw",etPassWd.getText());

        client.post(url, entity, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, JSONObject response) {

                HashMap<String, Object> jsonData = null;
                JSONObject jObj = null;

                AlertDialog.Builder ab = new AlertDialog.Builder(v.getContext());

                json = response;

//                aa.add(jsonData);
                //jsonData.put()
                Log.e("KC", "res : " + json.toString());

                try{
                    if(json.get("error").equals(-1)){

                        // 닉네임 존재하지 않을 경우
                        ab.setMessage("로그인 정보를 확인하세요.");
                        ab.setPositiveButton("확인", null);
                        ab.show();

                    }else{
                        //확인 완료

                        Intent iArticleList = new Intent(getApplicationContext(), ArticleListActivity.class);

                        // bring user information
                        iArticleList.putExtra("user_id","ee");
                        iArticleList.putExtra("user_nickname","ee");
                        iArticleList.putExtra("user_gender","ee");
                        iArticleList.putExtra("user_type","ee");
                        iArticleList.putExtra("user_recommender_idx","ee");
                        iArticleList.putExtra("user_location","ee");
                        iArticleList.putExtra("user_photo_path","ee");
                        iArticleList.putExtra("user_total_deal","ee");
                        iArticleList.putExtra("user_coin","ee");
                        iArticleList.putExtra("user_division","ee");

                        startActivity(iArticleList);
                    }

                }catch(JSONException je){

                }
            }
        });

    }

}
