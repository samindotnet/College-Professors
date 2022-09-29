package com.example.collegeprofessors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListItem {
    ConnectionHelper connectionHelper = new ConnectionHelper();
    String connResult = "";
    Boolean iscss = false;
    public List<Map<String,String>>getlist(){
        List<Map<String,String>>data = new ArrayList<Map<String,String>>();
        Connection connection = connectionHelper.connClass();
        try{
            if(connection != null){
                String qry = "select * from faculty";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(qry);
                while(rs.next()){
                    Map<String,String>content = new HashMap<String,String>();
                    content.put("tvFacultyID",rs.getString("facultyid"));
                    content.put("tvFirstName",rs.getString("firstname"));
                    content.put("tvLastName",rs.getString("lastname"));
                    data.add(content);
                }
                connResult="Sucess";
                iscss=true;
                connection.close();
            }
            else{
                connResult="Failure";
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }return data;
    }
}
