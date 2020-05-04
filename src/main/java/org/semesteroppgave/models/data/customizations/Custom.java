package org.semesteroppgave.models.data.customizations;

/**
 * Enum-klasse med konstante verdier for tilpasninger
 */

public enum Custom {
    GPS("GPS-system", 2000, 0),
    SUNROOF("Soltak", 5000, 1),
    TOWBAR("Hengerfeste", 1000, 2),
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

    public double getPrice(){
        return this.price;
    }

    //Index er plassering i array ved opprettelse av produkt
    public int getIndex(){
        return this.index;
    }

    public String toFileCsv() {
        return getCustomProperty() + ";" + getPrice();
    }
}
