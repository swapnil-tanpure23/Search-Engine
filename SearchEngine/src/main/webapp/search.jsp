<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Accio.SearchResults"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Simple Search Engine</h1>

<form action="Search">
    <input type="text" name="keyword"></input>
    <button text="submit">Search</button>
</form>
<form action="History">
    <button text="submit">History</button>
</form>
    <table border = 2 class="resultTable">
        <tr>
            <th>title</th>
            <th>link</th>
        </tr>
            <%
                ArrayList<SearchResults> result = (ArrayList<SearchResults>)request.getAttribute("results");
                for(SearchResults result: results){
            %>
           <tr>

                    <td><%out.println(result.getTitle());%></td>
                    <td><a href="<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>

           </tr>
           <%
                }
           %>
    </table>
</body>
</html>