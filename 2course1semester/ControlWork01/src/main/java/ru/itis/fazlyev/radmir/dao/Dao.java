package ru.itis.fazlyev.radmir.dao;

import ru.itis.fazlyev.radmir.dto.UserDto;
import ru.itis.fazlyev.radmir.model.History;
import ru.itis.fazlyev.radmir.model.User;

import java.sql.SQLException;

public interface Dao<T> {

    String save(User user) throws SQLException;

    boolean authentication(UserDto userDto);

    String saveHistory(History history);
}
