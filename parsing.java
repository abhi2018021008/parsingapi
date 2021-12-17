package com.jsonparsing;
 
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import java.util.ArrayList;
 
public class MainActivity extends AppCompatActivity {
    ListView fruitsList;
    String url = "https://s3.amazonaws.com/open-to-cors/assignment.json";
    ProgressDialog dialog;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        Subcategory = (ListView)findViewById(R.id.Subcategory);
        Title = (ListView)findViewById(R.id.Title );
        Price = (ListView)findViewById(R.id.Price);
        Popularity = (ListView)findViewById(R.id.Popularity);
 
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();
 
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
 
        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
    }
 
    void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray Subcategory = object.getJSONArray("Subcategory");
            JSONArray Title = object.getJSONArray("Title");
            JSONArray Price = object.getJSONArray("Price");
            JSONArray Popularity = object.getJSONArray("Popularity");
            ArrayList al = new ArrayList();
 
            for(int i = 0; i < fruitsArray.length(); ++i) {
                al.add(fruitsArray.getString(i));
            }
 
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);
            fruitsList.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
        dialog.dismiss();
    }
}