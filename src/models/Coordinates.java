package models;
import utility.Validatable;

import java.util.Objects;

public class Coordinates implements Validatable {
    private Integer x; //Поле не может быть null
    private Float y; //Поле не может быть null
    public Coordinates(int x, float y){
        this.x = x;
        this.y = y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    @Override
    public boolean validate(){
        return ((y == null) || (x == null));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Coordinates p = (Coordinates) o;
        return p.x == this.x && p.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates: (" + x + ", " + y + ")";
    }
}
