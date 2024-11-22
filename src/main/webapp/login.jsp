<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/main.jsp">
    <h2>Авторизация</h2>
    <form action="" method="post">
        <div>
            <label for="login">Логин:</label>
            <input type="text" id="login" name="login" required>
        </div>
        <div>
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" >Войти</button>
    </form>
    <p>Нет аккаунта? <a href="/registration.jsp">Зарегистрируйтесь</a>.</p>
</form>
</body>
</html>
