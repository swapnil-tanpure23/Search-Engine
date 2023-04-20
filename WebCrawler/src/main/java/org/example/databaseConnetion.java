package org.example;

import com.mysql.cj.jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLPermission;

public class databaseConnetion {
    static Connection connection = null;
    public static Connection getConnection(){
        if(connection!=null) return connection;
        String user = "root";
        String pwd = "123456789";
        String db = "searchengine";

        return getConnection(user, pwd, db);
    }
    private static Connection getConnection(String user, String pwd, String db){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb" + db + "?username=" + user + "&password=" + pwd);
        }
        catch(SQLException | ClassNotFoundException sqlException){
            sqlException.printStackTrace();
        }
        return connection;
    }

}
