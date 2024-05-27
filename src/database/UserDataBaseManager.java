package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDataBaseManager {
    private final ConnectionManager connectionManager;
    public UserDataBaseManager(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }
    public Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }
    public int addUser() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO USERS(name, password_digest, salt, role)" +
                        "VALUES (?, ?, ?, 'USER_MIN') RETURNING id");
        

    }
}
