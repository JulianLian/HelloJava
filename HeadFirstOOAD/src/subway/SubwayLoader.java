package subway;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SubwayLoader {
    private Subway subway;

    public SubwayLoader() {
        this.subway = new Subway();
    }

    public Subway loadFromFile(File subwayFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(subwayFile));
        loadStations(subway, reader);
        String lineName = reader.readLine();
        while (lineName != null && lineName.length() > 0) {
            loadLine(subway, reader, lineName);
            lineName = reader.readLine();
        }

        return subway;
    }

    private void loadLine(Subway subway, BufferedReader reader, String lineName) throws IOException {
        String station1Name, station2name;
        station1Name = reader.readLine();
        station2name = reader.readLine();
        while (station2name != null && station2name.length() > 0) {
            subway.addConnection(station1Name, station2name, lineName);
            station1Name = station2name;
            station2name = reader.readLine();
        }
    }

    private void loadStations(Subway subway, BufferedReader reader) throws IOException {
        String currentLine = reader.readLine();
        while (currentLine.length() > 0) {
            subway.addStation(currentLine);
            currentLine = reader.readLine();
        }
    }
}
