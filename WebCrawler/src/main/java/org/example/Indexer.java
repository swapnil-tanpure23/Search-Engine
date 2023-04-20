package org.example;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexer {
    static Connection connection = null;
    Indexer(Document document, String urls){
        String title = document.title();
        String link = urls;
        String text = document.text();

        try {
            Connection conn = databaseConnetion.getConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("Insert into pages values(?, ?, ?);");
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, urls);
                preparedStatement.setString(3, text);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
