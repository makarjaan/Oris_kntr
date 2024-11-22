package ru.kpfu.itis304.servlet;

import ru.kpfu.itis304.dao.UserDao;
import ru.kpfu.itis304.entity.User;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("")
public class LoginServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {

            User user = userDao.getUser(login, password);

            if (user != null) {
                userDao.logLoginAttempt(login, true);
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("user", login);
                httpSession.setMaxInactiveInterval(60 * 60);

                Cookie cookie = new Cookie("user", login);
                cookie.setMaxAge(24 * 60 * 60);
                resp.addCookie(cookie);

                resp.sendRedirect("/main");
            } else {
                userDao.logLoginAttempt(login, false);
                resp.sendRedirect("/registration");
            }
        } catch (Exception e) {
            throw new ServletException("Error during login process", e);
        }
    }
}

