package gameframework.test;

import gameframework.Unit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitTest {
    Unit unit;
    @Before
    public void setup() {
        unit = new Unit(1000);
    }
    @Test
    public void testType( ) {
        System.out.println("\nTesting setting/getting the type property.");
        unit.setType("infantry");
        assertEquals("infantry", unit.getType());
        System.out.println("Test passed");
    }

    @Test
    public void testUnitSpecificProperty() {
        System.out.println("\nTesting setting/getting a unit-specific property.");
        unit.setProperty("hitPoints", new Integer(25));
        assertEquals(new Integer(25), unit.getProperty("hitPoints"));
        System.out.println("Test passed");
    }

    @Test
    public void testChangeProperty() {
        System.out.println("\nTesting changing an existing property's value.");
        unit.setProperty("hitPoints", new Integer(25));
        unit.setProperty("hitPoints", new Integer(15));
        assertEquals(new Integer(15), unit.getProperty("hitPoints"));
        System.out.println("Test passed");
    }

    @Test
    public void testNonExistentProperty() {
        System.out.println("\nTesting getting a non-existent property's value.");
        assertEquals(null, unit.getProperty("strength"));
        System.out.println("Test passed");
    }
}