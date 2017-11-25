package guitar;

public class Guitar {
    private String serialNumber;
    private double price;
    GuitarSpec spec;

    public Guitar(String serialNumber, double price, GuitarSpec spec) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.spec = spec;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSpec(GuitarSpec spec) {
        this.spec = spec;
    }

    public String getSerialNumber() {

        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public GuitarSpec getSpec() {
        return spec;
    }
}
