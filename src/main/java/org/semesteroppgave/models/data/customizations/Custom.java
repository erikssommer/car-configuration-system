package org.semesteroppgave.models.data.customizations;

/**
 * Enum-klasse med konstante tilpasninger
 */

public enum Custom {
    GPS("GPS-system", 2000, 0),
    SUNROOF("Soltak", 5000, 1),
    TOWBAR("Tillhengerfeste", 1000, 2),
    AUTOPILOT("Autopilot", 50_000, 3);

    String customProperty;
    double price;
    int index;

    Custom(String customProperty, double price, int index) {
        this.customProperty = customProperty;
        this.price = price;
        this.index = index;
    }

    public String getCustomProperty() {
        return this.customProperty;
    }

    public double getPrice(){
        return this.price;
    }

    public int getIndex(){
        return this.index;
    }

    public String toFile() {
        return getCustomProperty() + ";" + getPrice();
    }

    public boolean equals(Custom obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else {
            return (obj.getCustomProperty().equals(customProperty) && obj.getPrice() == price);
        }
    }
}
