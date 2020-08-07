package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {


    private Button btnLogin;
    TextView txtRegister;
    EditText txtEmail, txtPass;

//   String loginUrl = "http://192.168.1.7/lab4/index.php";
   String loginUrl = "http://10.82.69.5/lab4/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtEmail = findViewById(R.id.edtUser);
        txtPass = findViewById(R.id.edtPass);
        txtRegister = findViewById(R.id.txt_Register);

        txtEmail.setText("duy@gmail.com");
        txtPass.setText("123");
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(txtRegister);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringrequest = new StringRequest(
                        Request.Method.POST, loginUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        xulyLogin(response);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(LoginActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
                        Log.d("loi",error.toString());

                    }
                }
                ) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<String,String>();

                        param.put("email",txtEmail.getText().toString());
                        param.put("password",txtPass.getText().toString());
                        param.put("tag","login");
                        return param;
                    }
                };
                Volley.newRequestQueue(LoginActivity.this).add(stringrequest);
            }

        });
    }

    private void xulyLogin(String response) {
        String thanhcong="";
        String name = "";
        try {
            JSONObject jsonobject=new JSONObject(response);
            thanhcong=jsonobject.getString("thanhcong");
            //doc tat ca du lieu tu json bo vao ArrayList
            if(Integer.parseInt(thanhcong)==1)//thanh cong
            {
                JSONObject user = jsonobject.getJSONObject("user");
                name = user.getString("name");
                Toast.makeText(this, "Đăng nhập", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
                finish();
            }
            else //that bai
            {
                Log.d("login","LoginFail");
                Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}