package mate.academy.internetshop.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private byte[] salt;
    private Set<Role> roles;

    public User(String name, String login, String password) {
        this.name = name;
        this.password = password;
        this.login = login;
        roles = new HashSet<>();
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
