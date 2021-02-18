package myapp.model;

import myapp.utils.DatabaseConnection;
import net.sf.oval.constraint.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {
    private static int counter = 0;

    private final int id;

    @NotNull(message = "Название должно быть задано.")
    @NotEmpty(message = "Пожалуйста, введите название.")
    @Length(min = 2, message = "Название = минимум 2 символa.")
    @ValidateWithMethod(methodName = "isUniqueDepName", parameterType = String.class, message = "Это название уже занято.")
    @MatchPattern(pattern = "[\\w ]+", message = "Допустимы только буквы, цифры и _")
    private String name;

    public Department(String name) {
        this.id = ++counter;
        this.name = name;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }


    private boolean isUniqueDepName(String name) {
        int count = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(name) AS c FROM departments WHERE name = ?")) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("c");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count == 0;
    }

    public Department makeCopy() {
        return new Department(this.id, this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}

