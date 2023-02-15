package ru.itis.fazlyev.radmir.servlet;

import ru.itis.fazlyev.radmir.dao.impl.UserDaoImpl;
import ru.itis.fazlyev.radmir.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AuthenticationServlet", urlPatterns = "/authentication")
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("authentication.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            if (userDao.authentication(userDto)) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("username", username);
                httpSession.setMaxInactiveInterval(60 * 60);
                if (req.getParameter("rememberCheck") != null) {
                    String remember = req.getParameter("rememberCheck");
                    System.out.println("remember : " + remember);
                    Cookie cookieRemember = new Cookie("cookieRemember", remember.trim());
                    cookieRemember.setMaxAge(60 * 60 * 24 * 15);
                    resp.addCookie(cookieRemember);
                    System.out.println(cookieRemember);
                }
                resp.sendRedirect("weather.ftl");
            } else {
                resp.sendRedirect("authentication.ftl");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userDao.authentication(userDto);
    }
}
