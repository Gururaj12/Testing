package com.ranjeet.admin.testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    EditText et1,et2,et3,et4,et5,et6;
    Button b1;
    String URL = "http://192.168.0.1:8080/kalyaanavaibhavam/register.do";
//nnkfdjhjkgf
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.fname);
        et2= (EditText) findViewById(R.id.lname);
        et3= (EditText) findViewById(R.id.gender);
        et4= (EditText) findViewById(R.id.email);
        et5= (EditText) findViewById(R.id.pswd);
        et6= (EditText) findViewById(R.id.dob);
        b1= (Button) findViewById(R.id.registerButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("true")){
                            Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Can't Register", Toast.LENGTH_LONG).show();
                        }
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("firstname", et1.getText().toString());
                        parameters.put("lastname", et2.getText().toString());
                        parameters.put("gender", et3.getText().toString());
                        parameters.put("dateofbirth", et4.getText().toString());
                        parameters.put("password", et5.getText().toString());
                        parameters.put("email", et6.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
                rQueue.add(request);
            }
        });

    }
}
