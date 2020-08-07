package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Add_Std_Activity extends AppCompatActivity {
//            String Url = "http://192.168.1.7/lab4/themsv.php";
    String Url = "http://10.82.69.5/lab4/themsv.php";
    EditText tvName,tvMssv,tvAge;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__std);

        tvName     = findViewById(R.id.tvName);
        tvMssv    = findViewById(R.id.tvMssv);
        tvAge  = findViewById(R.id.tvAge);
        btn_insert = findViewById(R.id.btnInsert);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData(Url);
            }
        });
    }

    private void insertData(String url) {

        final String name = tvName.getText().toString().trim();
        final String mssv = tvMssv.getText().toString().trim();
        final String age = tvAge.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(name.isEmpty()){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(mssv.isEmpty()){
            Toast.makeText(this, "Enter MSSV", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(age.isEmpty()){
            Toast.makeText(this, "Enter Age", Toast.LENGTH_SHORT).show();
            return;
        } else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, url,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.trim().equals("Data Inserted")){
                                Toast.makeText(Add_Std_Activity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Add_Std_Activity.this, MainActivity.class));
                                progressDialog.dismiss();
                            }
                            else{
                                Toast.makeText(Add_Std_Activity.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Add_Std_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("name",name);
                    params.put("mssv",mssv);
                    params.put("age",age);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Add_Std_Activity.this);
            requestQueue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
