package kr.ac.uos.cs.thechoice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Hoon Kim on 2014-11-27.
 */
public class ComsumerWriteContent extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_consumer_write_content);


    }
}