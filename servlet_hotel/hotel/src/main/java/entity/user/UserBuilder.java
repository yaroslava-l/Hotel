package entity.user;

public class UserBuilder {

    private Long id;

    private String username;

    private String email;

    private String password;

    private String role;

    public UserBuilder setId(Long id) {
        this.id = id;
        return this;

    }

    public UserBuilder setName(String username) {
        this.username = username;
        return this;

    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;

    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public User build() {
        return new User(id, username, email, password, role);
    }
}
