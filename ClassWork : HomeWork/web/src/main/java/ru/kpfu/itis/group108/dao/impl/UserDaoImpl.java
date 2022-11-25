package ru.kpfu.itis.group108.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.group108.dao.Dao;
import ru.kpfu.itis.group108.model.User;
import ru.kpfu.itis.group108.util.PostgresConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements Dao<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private final Connection connection = PostgresConnectionUtil.getConnection();


    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from users";
            ResultSet resultSet = statement.executeQuery(sql);

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            LOGGER.warn("Failed execute get all query");
            return List.of();
        }
    }

    @Override
    public void save(User user) {

    }
}