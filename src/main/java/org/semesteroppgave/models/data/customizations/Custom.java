package org.semesteroppgave.models.data.customizations;

/**
 * Enum class with constant values for customizations
 */

public enum Custom {
    GPS("GPS-system", 2000, 0),
    SUNROOF("Sunroof", 5000, 1),
    TOWBAR("Towbar", 1000, 2),
    AUTOPILOT("Autopilot", 50_000, 3);

    private final String customProperty;
    private final double price;
    private final int index;

    Custom(String customProperty, double price, int index) {
        this.customProperty = customProperty;
        this.price = price;
        this.index = index;
    }

    public String getCustomProperty() {
        return this.customProperty;
    }

    public double getPrice() {
        return this.price;
    }

    //Index is placement in array when creating product
    public int getIndex() {
        return this.index;
    }

    public String toFileCsv() {
        return getCustomProperty() + ";" + getPrice();
    }
}
