package ru.kpfu.itis304.service;

import ru.kpfu.itis304.dto.UserDto;
import ru.kpfu.itis304.dto.UserRegistrationDto;
import ru.kpfu.itis304.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserService {

    public User getUser(HttpServletRequest req, HttpServletResponse res) {
        return (User) req.getSession().getAttribute("user");
    }

    public void authUser(User user, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getSession().setAttribute("user", user);
    }

    public void deleteUser(User user, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getSession().removeAttribute("user");
    }

}
