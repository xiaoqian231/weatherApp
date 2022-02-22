import java.sql.*;

public class UserDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public UserDAO() {
        try {
            createTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * if table does not exist creates table for data
     * @throws SQLException
     */
    private void createTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS user"
                + "  (id        int(11)     NOT NULL AUTO_INCREMENT,"
                + "   login     text        NOT NULL,"
                + "   password  text        NOT NULL,"
                + "   city      int(11)     NOT NULL,"
                + "   PRIMARY KEY (`id`))"
                + "   ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";

        connection = getConnection();
        Statement stmt = connection.createStatement();
        stmt.execute(sqlCreate);
    }

    /**
     * gets connection
     * @return connection
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/weather", "root", "");
        return conn;
    }

    /**
     * method is used to loggin in
     * @param login
     * @param password
     * @return user object with received data from database
     */
    public User logginIn(String login, String password) {
        User user = new User();
        try {
            String query = "SELECT * FROM user where login = ? AND password = ?";

            connection = getConnection();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setCity(resultSet.getInt("city"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return user;
    }

    /**
     * adds user to database
     * @param login
     * @param password
     * @param city
     * @return true if user created, false user already exist or some database error
     */
    public boolean addUser(String login, String password, int city) {
        boolean result = false;
        try {
            String query = "SELECT login FROM user where login =?";

            connection = getConnection();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                String query2 = "INSERT INTO user(id, login, password, city) VALUES (NULL, ?, ?, ?)";

                preparedStatement = connection.prepareStatement(query2);

                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.setInt(3, city);

                preparedStatement.executeUpdate();
                result = true;
                System.out.println("User has been registered");
            } else {
                System.out.println("User already exists");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return result;
    }

}
