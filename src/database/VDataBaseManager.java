package database;

import models.Coordinates;
import models.User;
import models.Vehicle;
import models.VehicleType;


import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class VDataBaseManager {
    private final ConnectionManager connectionManager;
    public VDataBaseManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    public Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }
    public Set<Vehicle> loadVehicles() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles");
        ResultSet result = statement.executeQuery();

        Set<Vehicle> vehicles = new HashSet<>();
        while (result.next()){
        Float enginePower = result.getFloat("enginePower");
        if (result.wasNull()) enginePower = null;
        Vehicle vehicle = new Vehicle(result.getString("name"),
                new Coordinates(result.getLong("x"), result.getFloat("y")),
                result.getDate("creation_date"),
                enginePower, result.getLong("capacity"), result.getFloat("fuelConsumption"),
                result.getString("type") == null ? null : VehicleType.valueOf(result.getString("type")));
        vehicle.setId(result.getInt("id"));
        vehicles.add(vehicle);
        }
        connection.close();
        return vehicles;
    }
    public int addVehicle(User user, Vehicle vehicle) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO vehicles(name, x, y, enginePower, capacity, fuelConsumption, type," +
                         "creator_id" + "VALUES (?, ?, ?, ?, ?, ?, ?::genre," +
                "(SELECT id FROM users WHERE users.name=?)) " +
                "RETURNING id");
        statement.setString(1, vehicle.getName());
        statement.setLong(2, vehicle.getCoordinates().getX());
        statement.setFloat(3, vehicle.getCoordinates().getY());

        statement.setLong(5, vehicle.getCapacity());


        statement.setFloat(6, vehicle.getFuelConsumption());


        if (null == vehicle.getEnginePower())
            statement.setNull(4, Types.FLOAT);
        else statement.setFloat(4, vehicle.getEnginePower());
        if (null == vehicle.getType())
            statement.setNull(7, Types.VARCHAR);
        else statement.setString(7, vehicle.getEnginePower().toString());


        statement.setInt(8, user.getId());


        ResultSet result = statement.executeQuery();

        connection.close();

        result.next();

        return result.getInt(1);
    }
    public int updateVehicle(User user, int vehicleId, Vehicle newVehicle) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE vehicles SET name = ?, x = ?, y = ?, enginePower = ?, " +
                "capacity = ?, fuelConsumption = ?, type = ?::type " +
                "WHERE id = ? AND creator_id = ?");
        statement.setString(1, newVehicle.getName());
        statement.setLong(2, newVehicle.getCoordinates().getX());
        statement.setFloat(3, newVehicle.getCoordinates().getY());
        statement.setLong(5, newVehicle.getCapacity());
        statement.setFloat(6, newVehicle.getFuelConsumption());
        if (null == newVehicle.getEnginePower())
            statement.setNull(4, Types.FLOAT);
        else statement.setFloat(4, newVehicle.getEnginePower());
        if (null == newVehicle.getType())
            statement.setNull(7, Types.VARCHAR);
        else statement.setString(7, newVehicle.getEnginePower().toString());
        statement.setInt(8, vehicleId);
        statement.setInt(9, user.getId());
        int res = statement.executeUpdate();
        connection.close();
        return res;
    }
    public int clearVehicle(User user) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement_vehicles = connection.prepareStatement(
                "DELETE FROM musicbands WHERE creator_id = ?");
        statement_vehicles.setInt(1, user.getId());
        int res = statement_vehicles.executeUpdate();
        connection.close();
        return res;
    }
    public int removeMusicBand(User user, Vehicle vehicle) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement statement_vehicle = connection.prepareStatement(
                "DELETE FROM vehicles WHERE creator_id = ? AND id = ?");

        statement_vehicle.setInt(1, user.getId());
        statement_vehicle.setInt(2, vehicle.getId());
        int res = statement_vehicle.executeUpdate();

        connection.close();
        return res;
    }

}
