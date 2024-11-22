package ru.kpfu.itis304.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;


@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LogoutServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clear(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Запрошена страница выхода");
        clear(req, resp);
    }

    private void clear(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c: cookies) {
                c.setMaxAge(0);
                resp.addCookie(c);
            }
        }

        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }

        resp.sendRedirect("index.html");

    }
}
