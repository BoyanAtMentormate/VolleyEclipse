
package com.example.volleysimplesample;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.volleysimplesample.cmn.SearchResponse;

public class VolleyActivity extends Activity {
    private ListView mListView;
    protected GalleryAdapter mAdapter;
    private RequestQueue mQueue;

    private void toast(int id) {
        String text = getResources().getString(id);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        mQueue = VolleyApp.getRequestQueue();
        mListView = (ListView) findViewById(android.R.id.list);

        refreshData();
    }

    private void refreshData() {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.BASE_URL, null, new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        SearchResponse responseObj = VolleyApp.getGsonInstance().fromJson(
                                response.toString(), SearchResponse.class);
                        if (responseObj != null && responseObj.responseData != null) {
                            mAdapter = new GalleryAdapter(VolleyActivity.this,
                                    responseObj.responseData.results);
                            mListView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        toast(R.string.connection_error);
                    }
                });

        jsonRequest.setTag(Constants.TAG_IMAGE_REQUEST);
        mQueue.add(jsonRequest);
    }

    public void onStop() {
        super.onStop();
        mQueue.cancelAll(Constants.TAG_IMAGE_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.volley, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refreshData();
                break;
            default:
                break;
        }
        return true;
    }
}
