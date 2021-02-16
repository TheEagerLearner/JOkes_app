package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtJoke=findViewById(R.id.txtJoke);
        final TextView txtPunchline=findViewById(R.id.txtPunchline);
        final Button btnNext=findViewById(R.id.btnNext);
        final int i=0;
        final RequestQueue queue;
        queue=Volley.newRequestQueue(this);
        final String url="https://official-joke-api.appspot.com/random_joke";

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        if(i==0)
        {
            btnNext.setText("Next");
        }


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    String setup = response.getString("setup");
                    String punchline = response.getString("punchline");
                    txtJoke.setText(setup);
                    txtPunchline.setText(punchline);


                }
                catch(JSONException e){
                    Toast.makeText(MainActivity.this,"Error Mare!",Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"Connection Not Eastablished!\nPLease Turn on your Internet",Toast.LENGTH_SHORT).show();

                    }
                });


            queue.add(jsonObjectRequest);
            }
        });
    }
}
