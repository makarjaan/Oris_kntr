<%@ page contentType="text/html; UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Main</title>
</head>

<body>
<%
    String user = null;
    String sessionUser = String.valueOf(session.getAttribute("user"));
    if (sessionUser == null) {
        response.sendRedirect("login.html");
    } else {
        user = sessionUser;
    }

    String cookieUser = null;
    String sessionId = null;
    Cookie [] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("user".equalsIgnoreCase(c.getName())) {
                cookieUser = c.getValue();
            } else if ("jsessionid".equalsIgnoreCase(c.getName())) {
                sessionId = c.getValue();
            }
        }
    } else {
        sessionId = session.getId();
    }
%>

<h3>
    Hello, <%=user%>! Login successful
</h3>

</body>


</html>