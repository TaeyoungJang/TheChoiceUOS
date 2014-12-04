package kr.ac.uos.cs.thechoice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class ArticleListActivity extends Activity {
    private JSONObject json;
    private JSONArray result;

    private ArticleListAdapter aa = new ArticleListAdapter();

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_article_list);

        json = null;
        lv = (ListView)findViewById(R.id.articlelist_lv);

        getJsonResult();



        //Toast.makeText(this.getApplicationContext(),json.toString(), Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_article_list, menu);
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

    private void getJsonResult()
    {
        String serverHostAddr = "http://dlacodbs90.cafe24.com/";
//http://211.189.19.202/getarticle.php/
        AsyncHttpClient client = new AsyncHttpClient();
        String url = serverHostAddr+"getarticle.php";
        Log.i("KC",url);

        RequestParams entity = new RequestParams();
        //entity.add("count", "100");
        //entity.add("start_num", "0");
        entity.put("count",100);
        entity.put("start_num",0);
        client.post(url, entity, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, JSONObject response) {

                HashMap<String, Object> jsonData = null;
                JSONObject jObj = null;

                json = response;


                try{
                    result = json.getJSONArray("list");


                    for(int resIdx = 0 ; resIdx < result.length() ; resIdx++){

                        jObj = (JSONObject)result.get(resIdx);

                        jsonData = new HashMap<String, Object>();

                        jsonData.put("idx",jObj.get("writer_idx"));
                        jsonData.put("near_station",jObj.get("near_station"));
                        jsonData.put("title",jObj.get("title"));
                        jsonData.put("desc",jObj.get("desc"));
                        jsonData.put("photo_path", jObj.get("photo_path"));
                        jsonData.put("gender", (jObj.get("user_gender").toString().equals("0")) ? "Male" : "Female");

                        aa.add(jsonData);

                        Log.d("KC", "jObj : " + jObj.toString() + "/" + aa.getCount());

                    }


                }catch(JSONException e){
                    e.printStackTrace();
                }finally{
                    lv.setAdapter(aa);
                }

//                aa.add(jsonData);
                //jsonData.put()
                Log.e("KC", "res : " + json.toString());

            }
        });

    }

}
