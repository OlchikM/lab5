package database;

import managers.CollectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandDataBaseManager {
    private final ConnectionManager connectionManager;
    public CommandDataBaseManager(String url, String login, String password) {
        this.connectionManager = new ConnectionManager(url, login, password);
    }
    public CommandDataBaseManager(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }
    public Connection getConnection() throws SQLException{
        return connectionManager.getConnection();
    }
    public String getMinUserRole(String commandName) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT min_user_role FROM commands WHERE name = ?");
        statement.setString(1, commandName);
        ResultSet result = statement.executeQuery();
        connection.close();
        result.next();
        return result.getString("min_user_role");
    }
}
