package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("keyword");
        Connection connection = databaseConnection.getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?,?);");
            preparedStatement.setString(1,keyword);
            preparedStatement.setString(2,"http://localhost:8080/SearchEngine/Search?keyword="+keyword);
            preparedStatement.executeUpdate();

            ResultSet resultSet = connection.createStatement().executeQuery("select pageTitle, pageLink, (length(lower(pageText)) - length(replace(lower(pageText),'" + keyword.toLowerCase() + "','')))/length('" + keyword.toLowerCase() + "') as count from pages order by count desc limit 30;");
            ArrayList<SearchResults> results = new ArrayList<SearchResults>();
            while (resultSet.next()) {
                SearchResults searchResults = new SearchResults();
                SearchResults.setTitle(resultSet.getString("pageTitle"));
                SearchResults.setLink(resultSet.getString("pageLink"));
                SearchResults SearchResults = new SearchResults();
                results.add(SearchResults);
            }
            for(SearchResults result:results){
                System.out.println(result.getTitle()+"\n"+result.getLink()+"\n");
            }

            request.getParameter("results : ", results);
            request.getRequestDispatcher("search.jsp").forward(request,response);

            response.setContentType("text/");
            PrintWriter out = response.getWriter();
        }
        catch(ServletException servletException){
            servletException.setStackTrace();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
