package org.semesteroppgave;

public class SeatCover extends Component{

    private String component;

    public SeatCover(String version, double price) {
        super(version, price);
        this.component = "Setetrekk";
    }

    @Override
    public String getComponent() {
        return component;
    }

    @Override
    public void setComponent(String component) {
        this.component = component;
    }
}
