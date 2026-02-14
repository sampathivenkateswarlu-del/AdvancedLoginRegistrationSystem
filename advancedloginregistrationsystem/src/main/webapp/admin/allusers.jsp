<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.User" %>

<%
    List<User> users = (List<User>) request.getAttribute("usersList");

    if (users == null) {
        response.sendRedirect(request.getContextPath() + "/admindashboard.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Users</title>
</head>
<body>

<h2>All Users</h2>

<a href="<%= request.getContextPath() %>/admin/admindashboard.jsp">Back to Dashboard</a>

<br><br>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>Role</th>
        <th>Action</th>
    </tr>

    <%
        for (User u : users) {
    %>
    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getUsername() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getMobileNumber() %></td>
        <td><%= u.getRole() %></td>
        <td>
    		<a href="<%= request.getContextPath() %>/delete-user?id=<%= u.getId() %>"
       			onclick="return confirm('Are you sure you want to delete this user?');">
       			Delete
    		</a>
		</td>
    </tr>
    <%
        }
    %>

</table>

</body>
</html>
