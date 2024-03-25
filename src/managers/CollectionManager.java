package managers;

import models.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class CollectionManager {
    private HashSet<Vehicle> collection = new HashSet<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime initTime;
    private LocalDateTime lastSaveTime;
    private float maxPrice;

    public static String formatTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        if (localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                .equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))) {
            return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

    public HashSet<Vehicle> getCollection() {
        return collection;
    }

    public String getLastInitTime() {
        return formatTime(lastInitTime);
    }

    public String getLastSaveTime() {
        return formatTime(lastSaveTime);
    }

    public String getCollectionType() {
        return collection.getClass().getName();
    }

    public int getSize() {
        return collection.size();
    }

    public void clear() {
        collection.clear();
        lastInitTime = LocalDateTime.now();
    }

    public Vehicle getById(long id) {
        for (Vehicle element : collection) {
            if (element.getId() == id) return element;
        }
        return null;
    }
    public void addElement(Vehicle vehicle) {
        this.lastSaveTime = LocalDateTime.now();
        collection.add(vehicle);
        float newPrice = vehicle.getPrice();
        if (newPrice > maxPrice) maxPrice = newPrice;
    }
    public void removeElement(Vehicle vehicle) {
        collection.remove(vehicle);
        this.lastSaveTime = LocalDateTime.now();
    }
    public void editById(int id, Vehicle newVehicle) {
        Vehicle oldVehicle = getById(id);
        removeElement(oldVehicle);
        newVehicle.setId(id);
        addElement(newVehicle);
        this.lastSaveTime = LocalDateTime.now();
    }
    public float getMaxPrice(){
        return maxPrice;
    }
    public boolean checkExistById(long id) {
        return collection.stream().anyMatch(o -> o.getId() == id);
    }


}
