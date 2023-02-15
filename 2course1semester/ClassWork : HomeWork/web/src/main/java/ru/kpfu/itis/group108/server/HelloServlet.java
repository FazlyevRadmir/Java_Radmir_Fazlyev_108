package ru.kpfu.itis.group108.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "helloServlet", urlPatterns = "/hello")

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        http://localhost:8080/hello?number1=1&number2=7
        int a = Integer.parseInt(req.getParameter( "number1"));
        int b = Integer.parseInt(req.getParameter( "number2"));
        PrintWriter printWriter = resp.getWriter();
        int answer = a + b;
        printWriter.write("GetResult = " + answer);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        http://localhost:8080/form.html
        int a = Integer.parseInt(request.getParameter( "number1"));
        int b = Integer.parseInt(request.getParameter( "number2"));
        request.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        int answer = a + b;
        printWriter.write("PostResut = " + answer);
    }
}
