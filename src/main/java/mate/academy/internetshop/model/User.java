package mate.academy.internetshop.model;

public class User {
    private String name;
    private String login;
    private String password;
    private Long id;

    public User(String name, String login, String password) {
        this.name = name;
        this.password = password;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{login = '" + login + '\''
                + ", id = " + id + '}';
    }
}
