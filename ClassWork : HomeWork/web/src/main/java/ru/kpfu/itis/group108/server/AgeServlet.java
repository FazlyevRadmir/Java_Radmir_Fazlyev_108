package ru.kpfu.itis.group108.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet(name = "ageServlet", urlPatterns = "/age")

public class AgeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("enterAge.ftl");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String day = req.getParameter("day");
        String month = req.getParameter("month");
        String year = req.getParameter("year");

        HttpSession httpSession = req.getSession();
        httpSession.setMaxInactiveInterval(60 * 60);
        httpSession.setAttribute("name", name);

        String BD = year + "-" + month + "-" + day;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(BD, formatter);
        LocalDate endDate = LocalDate.now();
        Period lifeTime = Period.between(startDate, endDate);

        day = String.valueOf(lifeTime.getDays());
        month = String.valueOf(lifeTime.getMonths());
        year = String.valueOf(lifeTime.getYears());

        int monthTime = Integer.parseInt(year) * 12;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long dayTime;
        try {
            Date date = simpleDateFormat.parse(BD);
            long dateTime = date.getTime();
            long timeMillis = System.currentTimeMillis();
            dayTime = (timeMillis - dateTime) / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        httpSession.setAttribute("day", day);
        httpSession.setAttribute("month", month);
        httpSession.setAttribute("year", year);
        httpSession.setAttribute("dayTime", dayTime);
        httpSession.setAttribute("monthTime", monthTime);

        req.getRequestDispatcher("age.ftl").forward(req, resp);
    }
}
