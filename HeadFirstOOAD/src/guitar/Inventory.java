package guitar;

import java.util.LinkedList;
import java.util.List;

public class Inventory {
    private List<Guitar> guitars;

    public Inventory(List guitars) {
        this.guitars = guitars;
    }

    public Inventory() {
        guitars = new LinkedList();
    }

    public Guitar getGuitar(String serialNumber) {
        for (Guitar guitar : guitars) {
            if (guitar.getSerialNumber().equals(serialNumber)) {
                return guitar;
            }
        }
        return null;
    }

    public List<Guitar> search(GuitarSpec searchSpec) {
        List<Guitar> guitarList = new LinkedList<>();

        for (Guitar guitar : guitars) {
            GuitarSpec guitarSpec = guitar.getSpec();
            if (guitarSpec.matches(searchSpec)) {
                guitarList.add(guitar);
            }
        }
        return guitars;
    }

    public void addGuitar(Guitar guitar) {
        guitars.add(guitar);
    }
}
