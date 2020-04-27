package org.semesteroppgave.models.data.customizations;

/**
 * Abstrakt klasse for alle tilpasninger
 */

public abstract class Customization {
    private final String customProperty;
    private final double price;

    public Customization(String customProperty, double price) {
        this.customProperty = customProperty;
        this.price = price;
    }

    public String getCustomProperty() {
        return customProperty;
    }

    public double getPrice() {
        return price;
    }

    public String toFile() {
        return getCustomProperty() + ";" + getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof Customization) {
            Customization component = (Customization) obj;
            return (component.getCustomProperty().equals(customProperty) && component.getPrice() == price);
        }
        return false;
    }
}
