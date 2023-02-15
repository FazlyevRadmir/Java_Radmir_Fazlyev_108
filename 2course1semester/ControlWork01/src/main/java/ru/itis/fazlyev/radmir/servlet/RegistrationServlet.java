package ru.itis.fazlyev.radmir.servlet;

import ru.itis.fazlyev.radmir.dao.impl.UserDaoImpl;
import ru.itis.fazlyev.radmir.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("registration.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);

        User user = new User(username, password);

        UserDaoImpl userDao = new UserDaoImpl();
        userDao.save(user);
        resp.sendRedirect("authentication.ftl");
    }
}
