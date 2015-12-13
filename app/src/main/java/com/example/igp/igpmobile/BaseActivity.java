package com.example.igp.igpmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.igp.igpmobile.utilities.database.SqliteHelper;
import com.example.igp.igpmobile.common.BaseFragment;
import com.example.igp.igpmobile.utilities.network.NetworkManager;

import org.json.JSONObject;

/**
 * Created by vimal on 9/12/15.
 */

public class BaseActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    private static final String TAG = "IGP:BaseAct:";
    public static final int MAIN_ACTIVITY_CONTAINER_ID = R.id.mainContainer;
    public static final int SEC_ACTIVITY_CONTAINER_ID = R.id.secContainer;

    protected NetworkManager networkManager;
    protected SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sqliteHelper = new SqliteHelper(this);
        networkManager = NetworkManager.newInstance(this);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        Boolean isAppComingFromBackground = Prefs.getAppBackgroundPauseState(this);
        if(isAppComingFromBackground){
            Log.i(BaseFragment.LOG_TAG, "APP COMING TO FOREGROUND");
            BaseFragment.logIntercomEvent(IntercomEvents.INTERCOM_EVENT_APPLICATION_FOREGROUND, null);
            BaseFragment.updateUserInfoToIntercom(this);
            OrobindPreferences.saveAppBackgroundPauseState(this, false);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public BaseFragment getBaseFragmentInContainer(){
        String activityName = this.getClass().getSimpleName();
        Log.d(TAG, "Activity: " + activityName);
        return BaseFragment.getFragmentFromCurrentActivity(this);
    }

    @Override
    public void onErrorResponse(Request request, VolleyError error) {
        Log.d(TAG, "VolleyError"+ error);
    }

    @Override
    public void onResponse(Request<JSONObject> request, JSONObject response) {
        Log.d(TAG, "Response"+response);
    }
}
