package com.example.assignment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment.Model.Student;

import java.util.HashMap;
import java.util.Map;

public class Edit_Activity extends AppCompatActivity {

    String Url = "http://10.82.69.5/lab4/suasv.php";
    EditText edtName, edtMssv, edtAge;
    Button btnSuaSinhVien;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);
        AnhXa();
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("dataStd");
//        Toast.makeText(this, sinhVien.getName(), Toast.LENGTH_SHORT).show();

        id = student.getId();
        edtName.setText(student.getName());
        edtMssv.setText(student.getMssv());
        edtAge.setText(student.getAge());
        // nút sửa
        btnSuaSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String mssv = edtMssv.getText().toString().trim();
                String age = edtAge.getText().toString().trim();

                if (name.isEmpty() || mssv.isEmpty() ||age.isEmpty()  ) {
                    Toast.makeText(Edit_Activity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    UpdateSinhVien(Url);
                }
            }
        });
    }

    private void UpdateSinhVien(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Thanh cong")){
                    Toast.makeText(Edit_Activity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Edit_Activity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(Edit_Activity.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Activity.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                params.put("name", edtName.getText().toString().trim());
                params.put("mssv", edtMssv.getText().toString().trim());
                params.put("age", edtAge.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        edtName = findViewById(R.id.ed_Name);
        edtMssv = findViewById(R.id.ed_Mssv);
        edtAge = findViewById(R.id.ed_Age);
        btnSuaSinhVien = findViewById(R.id.btn_updateData);
    }


}