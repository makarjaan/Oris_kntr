<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Weather Page</title>
</head>
<body>
<h1>Weather Information</h1>
<form action="/main" method="post">
    <input type="text" name="city" placeholder="Enter city name" required>
    <input type="submit" value="Get Weather">
</form>

<h2>Weather in ${city}</h2>
<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>
<c:if test="${empty error}">
    <p>Description: ${description}</p>
    <p>Tmperature: ${temperature}°C</p>
</c:if>
</body>
</html>