package ru.kpfu.itis304.servlet;


import ru.kpfu.itis304.dao.UserDao;

import ru.kpfu.itis304.entity.User;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("registration");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            User user = new User(login, password, email);

            boolean isAdded = userDao.addUser(user);

            userDao.logLoginAttempt(login, isAdded);

            if (isAdded) {
                resp.sendRedirect("main.jsp");
            } else {
                resp.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            try {
                userDao.logLoginAttempt(login, false);
            } catch (Exception logException) {
                throw new ServletException(logException);
            }
            throw new ServletException("Error during registration process", e);
        }
    }
}
