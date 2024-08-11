import javax.xml.transform.Result;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class DB {
    private final String url = "jdbc:mysql://localhost:3306/auth";
    private final String dbUserName = "root";
    private final String dbPassword = "Password0.!";
    private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public DB() throws ClassNotFoundException {
        try {
            Class.forName(jdbcDriver);
            Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void InsertUser(String newUser, String newPassword) {
        String query = "INSERT INTO Users (userName, hashedPW) VALUES ("
                + "'" + newUser + "',"
                + "'" + Authentication.HashPassword(newPassword) + "'"
                + ")";
        try {
            Class.forName(jdbcDriver);
            Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e ) {
            System.out.println("An error has occurred on user insertion");
            e.printStackTrace();
        }
    }

    public String loadUserPW(String userName) {
        String query = "SELECT hashedPW from Users WHERE userName='" + userName + "';";
        String hash = "";
        try {
            Class.forName(jdbcDriver);
            Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                hash = resultSet.getString(1);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e ) {
            System.out.println("An error has occurred during loading user password");
            e.printStackTrace();
        }
        return hash;
    }

    public void CreateUserTable() throws SQLException {
        String query = "CREATE TABLE Users ("
                + "userId INT(32) NOT NULL AUTO_INCREMENT,"
                + "userName VARCHAR(64) UNIQUE NOT NULL,"
                + "hashedPW VARCHAR(128) NOT NULL ,"
                + "PRIMARY KEY (userId))";
        try {
            Class.forName(jdbcDriver);
            Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e ) {
            System.out.println("An error has occured on Table Creation");
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
