package models;

import managers.CollectionManager;
import utility.Element;

import java.util.HashSet;
import java.util.Objects;
import com.google.gson.annotations.SerializedName;

public class Vehicle extends Element {
    private static int nextId = 0;

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @SerializedName("name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float enginePower; //Значение поля должно быть больше 0
    private Long capacity; //Поле не может быть null, Значение поля должно быть больше 0
    private Float fuelConsumption; //Поле не может быть null, Значение поля должно быть больше 0
    private VehicleType type; //Поле может быть null

    public Vehicle(String name, Coordinates coordinates, java.util.Date creationDate, float enginePower, Long capacity, Float fuelConsumption, VehicleType type) {
        this.id = touchNextId();
        this.capacity = capacity;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.fuelConsumption = fuelConsumption;
        this.type = type;
    }

    @Override
    public boolean validate() {
        if (id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (enginePower <= 0) return false;
        if (capacity == null || capacity <= 0) return false;
        return (fuelConsumption != null && fuelConsumption > 0);
    }

    public void update(Vehicle vehicle) {
        this.name = vehicle.name;
        this.coordinates = vehicle.coordinates;
        this.creationDate = vehicle.creationDate;
        this.enginePower = vehicle.enginePower;
        this.fuelConsumption = vehicle.fuelConsumption;
        this.type = vehicle.type;
        this.capacity = vehicle.capacity;
    }

    public static int touchNextId() {
        return nextId++;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Float getFuelConsumption() {
        return fuelConsumption;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public int compareTo(Element element) {
        return this.id - element.getId();
    }

    public int compareTo(Vehicle vehicle) {
        return this.capacity.compareTo(vehicle.getCapacity());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id && Objects.equals(name, vehicle.name) && Objects.equals(coordinates, vehicle.coordinates)
                && Objects.equals(creationDate, vehicle.creationDate) && Objects.equals(enginePower, vehicle.enginePower)
                && Objects.equals(fuelConsumption, vehicle.fuelConsumption) && type == vehicle.type && Objects.equals(capacity, vehicle.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, capacity, enginePower, fuelConsumption, type);
    }

    @Override
    public String toString() {
        String info = "";
        info += "Транспортное средство №" + id;
        info += " (добавлен " + creationDate.toString() + ")";
        info += "\n Название: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Вместимось: " + capacity;
        info += "\n Тип: " + ((type == null) ? null : "'" + type + "'");
        info += "\n Мощность двигателя: " + enginePower;
        info += "\n Потребление топлива:    " + fuelConsumption;
        return info;
    }
    public void setId(int id){
        this.id = id;
    }

    public static void updateIdPoint(HashSet<Vehicle> collection) {
        nextId = collection.stream()
                .filter(Objects::nonNull)
                .map(Vehicle::getId)
                .mapToInt(Integer::intValue)
                .max().orElse(0);
    }
    public float getPrice(){
        PriceElement<Long, Float> price = (x, y, capacity, enginePower, fuelConsumption) -> (float) Math.sqrt(x*x + y*y) + capacity + enginePower + fuelConsumption;
        return price.getPriceElement(getCoordinates().getX(), getCoordinates().getY(), capacity, enginePower, fuelConsumption);
    }
}


