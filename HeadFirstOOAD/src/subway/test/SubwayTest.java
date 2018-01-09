package subway.test;

import org.junit.Before;
import org.junit.Test;
import subway.Subway;
import subway.SubwayLoader;
import subway.SubwayPrinter;

import java.io.File;
import java.io.IOException;

public class SubwayTest {
    Subway objectVille;

    @Before
    public void setUp() {
        SubwayLoader loader = new SubwayLoader();
        try {
            objectVille = loader.loadFromFile(new File("HeadFirstOOAD\\src\\subway\\test\\ObjectvilleSubway.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrintDirections() {
        SubwayPrinter printer = new SubwayPrinter(System.out);
        printer.printDirections(objectVille.getDirections("DRY Drive", "Infinite Circle"));
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: SubwayTester [startStation] [endStation]");
            System.exit(-1);
        }

        try {
            SubwayLoader loader = new SubwayLoader();
            Subway objectVille = loader.loadFromFile(new File("HeadFirstOOAD\\src\\subway\\test\\ObjectvilleSubway.txt"));

            if (objectVille.hasStation(args[0])) {
                System.err.println(args[0] + " is not a station in ObjectVille.");
                System.exit(-1);
            } else if (!objectVille.hasStation(args[1])) {
                System.err.println(args[0] + " is not a station in ObjectVille.");
                System.exit(-1);
            }

            SubwayPrinter printer = new SubwayPrinter(System.out);
            printer.printDirections(objectVille.getDirections(args[0], args[1]));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}