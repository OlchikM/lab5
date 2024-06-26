package managers;
import database.ConnectionManager;
import database.UserDatabaseManager;
import loggers.Logger;
import loggers.StandardLogger;
import models.User;
import server.Configuration;

import java.sql.SQLException;

public class AuthManager {
    private static final Logger logger = new StandardLogger();
    private final UserDatabaseManager databaseManager = new UserDatabaseManager(
            new ConnectionManager(Configuration.getDbUrl(), Configuration.getDbLogin(), Configuration.getDbPass()));
    public boolean checkUserName(String name) {
        return databaseManager.checkUserName(name);
    }
    public boolean checkUserPass(String name, String password) {
        return databaseManager.checkUserPass(name, password);
    }
    public boolean register(User user){
        try{
            if (!databaseManager.checkUserName(user.getName())){
                int id = databaseManager.addUser(user);
                user.setId(id);
                return true;
            }
        } catch (SQLException e) {
            logger.write("Не получилось зарегистрироваться");
            logger.writeError(e.toString());
            return false;
        }
        return false;
    }
    public boolean auth(User user){
        try{
            if (databaseManager.checkUserPass(user.getName(), user.getPassword())){
                int id = databaseManager.getUserId(user.getName());
                user.setId(id);
                logger.write("Вход прошёл успешно");
                return true;
            }
        } catch (SQLException e){
            logger.write("Не получилось войти");
            logger.writeError(e.toString());
            return false;
        }
        return false;
    }

}
