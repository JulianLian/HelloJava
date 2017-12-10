package guitar;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GuitarFinderTest {
    private Inventory inventory;
    @Before
    public void setUp() {
        inventory  = new Inventory();
        inventory.addGuitar(new Guitar("V95693", 1499.95,
                                          new GuitarSpec(Builder.FENDER, "Stratocastor", Type.ELECTIC, 12, Wood.ALDER, Wood.ALDER)));
        inventory.addGuitar(new Guitar("V95696", 1599.99,
                                          new GuitarSpec(Builder.FENDER, "Stratocastor", Type.ELECTIC, 12, Wood.ALDER, Wood.ALDER)));
    }
    @Test
    public void testSearch() {
        GuitarSpec searchSpec = new GuitarSpec(Builder.FENDER, "Stratocastor", Type.ELECTIC, 12, Wood.ALDER, Wood.ALDER);

        List<Guitar> guitars = inventory.search(searchSpec);

        if (guitars != null) {
            for (Guitar guitar : guitars) {
                System.out.println("You might like this " + guitar);
            }
        } else {
            System.out.printf("Sorry, we have nothing for you.");
        }
    }
}