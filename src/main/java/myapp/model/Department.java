package myapp.model;

import myapp.utils.validator.constraints.department.CheckUniqueName;
import net.sf.oval.constraint.*;

public class Department {

    private final int id;

    @NotNull(message = "Название должно быть задано.")
    @NotEmpty(message = "Пожалуйста, введите название.")
    @Length(min = 2, max = 35, message = "Название 2...35 символов.")
    @CheckWith(value = CheckUniqueName.class, message = "Это имя уже занято.")
    @MatchPattern(pattern = "[\\w ]+", message = "Допустимы только буквы, цифры и _")
    private String name;

    public Department(String name) {
        this.id = 0;
        this.name = name;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
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

