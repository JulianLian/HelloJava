package subway;

import java.util.LinkedList;
import java.util.List;

public class Subway {
    private List<Station> stations;
    private List<Connection> connections;

    public Subway() {
        this.stations = new LinkedList<>();
        this.connections = new LinkedList<>();
    }

    public void addStation(String stationName) {
        if (!this.hasStation(stationName)) {
            Station station = new Station(stationName);
            stations.add(station);
        }
    }

    public boolean hasStation(String stationName) {
        return this.stations.contains(new Station(stationName));
    }

    public void addConnection(String station1Name, String station2Name, String lineNme) {
        if (this.hasStation(station1Name) && this.hasStation(station2Name)) {
            Station station1 = new Station(station1Name);
            Station station2 = new Station(station2Name);
            connections.add(new Connection(station1, station2, lineNme));
            connections.add(new Connection(station2, station1, lineNme));
        } else {
            throw new RuntimeException("Invalid connection!");
        }

    }

    public boolean hasConnection(String station1Name, String station2Name, String lineName) {
        return connections.contains(new Connection(new Station(station1Name),
                                                   new Station(station2Name),
                                                   lineName));
    }
}
