package models;

public interface PriceElement<T, N> {
    float getPriceElement(T x, N y, T capacity, N enginePower, N fuelConsumption);
}
