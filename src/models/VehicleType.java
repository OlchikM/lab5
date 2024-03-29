package models;

public enum VehicleType {
    PLANE,
    BICYCLE,
    MOTORCYCLE,
    CHOPPER,
    HOVERBOARD;

    public static String list(){
        StringBuilder resultStr = new StringBuilder();
        for (var names : values()) {
            resultStr.append(names.name()).append("\n");
        }
        return resultStr.toString();
    }

}
