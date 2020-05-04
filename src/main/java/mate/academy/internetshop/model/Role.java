package mate.academy.internetshop.model;

public class Role {
    private Long id;
    private Roles role;

    public Role(Long id, Roles role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    enum Roles {
        USER,
        ADMIN;
    }
}
