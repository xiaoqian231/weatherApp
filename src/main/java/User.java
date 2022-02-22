public class User {
    private Integer id = null;
    private String login;
    private String password;
    private int city;
    private String cityName;

    public User() {
    }

    public User(String login, String password, int city, String cityName) {
        this.login = login;
        this.password = password;
        this.city = city;
        this.cityName = cityName;
    }

    public User(int id, String login, String password, int city, String cityName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.city = city;
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getCity() {
        return city;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(int city) {
        this.city = city;
    }
}
