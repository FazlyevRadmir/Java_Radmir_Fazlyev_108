package ru.itis.fazlyev.radmir.servlet;

import ru.itis.fazlyev.radmir.dao.impl.UserDaoImpl;
import ru.itis.fazlyev.radmir.model.History;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "WeatherServlet", urlPatterns = "/weather")
public class WeatherServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("weather.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");

        double celciy;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + "9bf73362c913f887d7d8ff91db6ff1ba");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

//            System.out.println(connection.getResponseCode());

            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(connection.getInputStream())
                         )) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                System.out.println(content.toString());

                String str = content.toString();
                String[] line = str.split(",\"feels_like\"");
                line = line[0].split("\"temp\":");
                String temperature = line[line.length - 1];
                celciy = Double.parseDouble(temperature) - 273.15;
                req.setAttribute("celciy", celciy);
                System.out.println("Weather in " + city + " = " + celciy);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String celciyTemp = (String) req.getAttribute(String.valueOf(celciy));
        resp.sendRedirect("temperature.ftl");
        History history = new History();

        UserDaoImpl historyDao = new UserDaoImpl();
        historyDao.saveHistory(history);
    }
}
