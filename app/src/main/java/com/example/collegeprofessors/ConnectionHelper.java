package com.example.collegeprofessors;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String ip,port,db,un,pass;
    public Connection connClass(){
        ip = "192.168.1.183";
        port = "49863";
        db="University";
        un="sa";
        pass="Test1234";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connURL ="jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databaseName="+db+";user="+un+";password="+pass+";";
            connection = DriverManager.getConnection(connURL);
        }catch(Exception exception){
            Log.e("Error",exception.getMessage());
        }return connection;
    }
}
