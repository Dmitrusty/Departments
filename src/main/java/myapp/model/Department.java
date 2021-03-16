package myapp.model;

import myapp.utils.validator.constraints.department.CheckUniqueName;
import net.sf.oval.constraint.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table( name = "departments" )
public class Department {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Department", orphanRemoval = true)
    private int id;

    @NotNull(message = "Название должно быть задано.")
    @NotEmpty(message = "Пожалуйста, введите название.")
    @Length(min = 2, max = 35, message = "Название 2...35 символов.")
    @CheckWith(value = CheckUniqueName.class, message = "Это имя уже занято.")
    // todo правильно ввел контроль русских букв и _ ?
    @MatchPattern(pattern = "[\\w_ А-Яа-я]+", message = "Допустимы только буквы, цифры и _")
    private String name;

    public Department() {
    }

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

    public void setId(int id) {
        this.id = id;
    }
}

