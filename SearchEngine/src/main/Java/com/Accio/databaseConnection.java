package com.Accio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    static Connection connection = null;
    public static Connection getConnection(){
        if(connection!=null) return connection;
        String user = "root";
        String pwd = "123456";
        String db = "SearchEngine";
        return getConnection(user,pwd,db);
    }
    private static Connection getConnection(String user, String pwd, String db){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?username=" + user + "&password=" + pwd);
        }
        catch(SQLException | ClassNotFoundException sqlException){
            sqlException.printStackTrace();
        }
        return connection;
    }

}