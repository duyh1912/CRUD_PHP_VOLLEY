package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister, btnCancel;
    EditText txtName, txtEmail, txtPass;

//    String registerUrl = "http://10.82.65.212/lab4/index.php";
    String registerUrl = "http://10.82.69.5/lab4/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnCancel=findViewById(R.id.btnCancel);
        btnRegister=findViewById(R.id.btnRegister);
        txtName = findViewById(R.id.edtName);
        txtEmail = findViewById(R.id.edtUser);
        txtPass = findViewById(R.id.edtPass);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringrequest = new StringRequest(
                        Request.Method.POST, registerUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        xulyRegister(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(RegisterActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
                        Log.d("loi",error.toString());
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<String,String>();

                        param.put("name",txtName.getText().toString());
                        param.put("email",txtEmail.getText().toString());
                        param.put("password",txtPass.getText().toString());
                        param.put("tag","register");

                        return param;
                    }
                };
                Volley.newRequestQueue(RegisterActivity.this).add(stringrequest);

            }
        });
    }

    private void xulyRegister(String response) {

        String thanhcong="";
        try {
            JSONObject jsonobject=new JSONObject(response);
            thanhcong=jsonobject.getString("thanhcong");

            //doc tat ca du lieu tu json bo vao ArrayList
            if(Integer.parseInt(thanhcong)==1)//thanh cong
            {
                Toast.makeText(this, "Register Done", Toast.LENGTH_SHORT).show();
                finish();
            }
            else //that bai
            {
                Toast.makeText(this, "Register Fail", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
