package myapp.model;

import java.util.Objects;
import java.util.UUID;

public class Account {
    private String id = UUID.randomUUID().toString();
    private String login;
    private String password;
    private String accessLevel;

    public Account(String login, String password, String accessLevel) {
        this.login = login;
        this.password = password;
        this.accessLevel = "USER";
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return login.equals(account.login) && password.equals(account.password) && accessLevel.equals(account.accessLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, accessLevel);
    }
}
