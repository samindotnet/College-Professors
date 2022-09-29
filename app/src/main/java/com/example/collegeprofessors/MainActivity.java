package com.example.collegeprofessors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText txtfacultyid, txtfirstname, txtlastname;
    Button btnAdd, btnDelete, btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtfacultyid = findViewById(R.id.txtFacultyID);
        txtfirstname = findViewById(R.id.txtFirstName);
        txtlastname = findViewById(R.id.txtLastName);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        ConnectionHelper connectionHelper = new ConnectionHelper();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = connectionHelper.connClass();
                try{
                    if(connection!=null){
                        String qry = "insert into faculty values('"+txtfacultyid.getText().toString()+"','"+
                                txtfirstname.getText().toString()+"','"+txtlastname.getText().toString()+"')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(qry);
                    }
                }catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = connectionHelper.connClass();
                try {
                    String qry = "update faculty set firstname ='"+txtfirstname.getText().toString()+
                            "',lastname='"+txtlastname.getText().toString()+"'where facultyid='"+txtfacultyid.getText().toString()+"'";
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(qry);
                }catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = connectionHelper.connClass();
                try {
                    String qry = "delete from faculty where facultyid ='"+txtfacultyid.getText().toString()+"'";
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(qry);
                }catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }
        });
    }
    SimpleAdapter adapter;
    public void GetList(View view){
        ListView lstw = findViewById(R.id.lstvw);
        List<Map<String,String>>MyDataList = null;
        ListItem MyData = new ListItem();
        MyDataList = MyData.getlist();
        String[] Mac = {"tvFacultyID","tvFirstName","tvLastName"};
        int[] os = {R.id.tvFacultyID,R.id.tvFirstName,R.id.tvLastName};
        adapter = new SimpleAdapter(MainActivity.this,MyDataList,R.layout.listitemtemplate,Mac,os);
        lstw.setAdapter(adapter);
    }
}