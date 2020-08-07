package com.example.assignment.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.example.assignment.Edit_Activity;
import com.example.assignment.MainActivity;
import com.example.assignment.Model.Student;
import com.example.assignment.R;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<Student> studentList;

    public StudentAdapter(MainActivity context, int layout, List<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder {
        TextView txtName, txtEmail, txtCode;
        Button btnDelete, btnEdit;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder =new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);
            holder.txtName = view.findViewById(R.id.txt_name);
            holder.txtEmail = view.findViewById(R.id.txt_mssv);
            holder.txtCode = view.findViewById(R.id.txt_age);
            holder.btnDelete = view.findViewById(R.id.btnDelete);
            holder.btnEdit = view.findViewById(R.id.btnEdit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Student student = studentList.get(i);
        holder.txtName.setText("Tên: "+student.getName());
        holder.txtEmail.setText("MSSV: "+student.getMssv());
        holder.txtCode.setText("Age: "+student.getAge());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.deleteStudnet(student.getId());
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Edit_Activity.class);
                intent.putExtra("dataStd", student);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
