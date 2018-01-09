package subway;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Subway {
    private List<Station> stations;
    private List<Connection> connections;
    private Map<Station, List<Station>> network;

    public Subway() {
        this.stations = new LinkedList<>();
        this.connections = new LinkedList<>();
        this.network = new HashMap();
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

            addToNetwork(station1, station2);
            addToNetwork(station2, station1);
        } else {
            throw new RuntimeException("Invalid connection!");
        }

    }

    private void addToNetwork(Station station1, Station station2) {
        if (network.keySet().contains(station1)) {
            List connectingStations = network.get(station1);
            if (!connectingStations.contains(station2)) {
                connectingStations.add(station2);
            }
        } else {
            List connectingStations = new LinkedList();
            connectingStations.add(station2);
            network.put(station1,connectingStations);
        }
    }

    public boolean hasConnection(String station1Name, String station2Name, String lineName) {
        return connections.contains(new Connection(new Station(station1Name),
                                                   new Station(station2Name),
                                                   lineName));
    }

    public List getDirections(String startStationName, String endStationName) {
        if (!this.hasStation(startStationName) || !this.hasStation(endStationName)) {
            throw new RuntimeException("Stations entered do not exist on this subway");
        }
        // Dijkstra algorithm
        Station start = new Station(startStationName);
        Station end = new Station(endStationName);
        List<Connection> route =  new LinkedList();
        List<Station> reachableStations = new LinkedList<>();
        Map previousStation = new HashMap();
        // connect directly
        List<Station> neighbors = network.get(start);
        for (Station station : neighbors) {
            if (station.equals(end)) {
                route.add(getConnection(start, end));
                return route;
            } else {
                reachableStations.add(station);
                previousStation.put(station, start);
            }
        }

        // find the shortest path
        List<Station> nextStations = new LinkedList();
        nextStations.addAll(neighbors);
        Station currentStation = start;

        searchLoop:
        for (int i = 1; i < stations.size(); i++) {
            List tmpNextStations = new LinkedList();
            for (Station station : nextStations) {
                reachableStations.add(station);
                currentStation = station;
                List<Station> currentNeighbors = network.get(currentStation);
                for (Station neighbor : currentNeighbors) {
                    if (neighbor.equals(end)) {
                        reachableStations.add(neighbor);
                        previousStation.put(neighbor, currentStation);
                        break searchLoop;
                    } else if (!reachableStations.contains(neighbor)) {
                        reachableStations.add(neighbor);
                        tmpNextStations.add(neighbor);
                        previousStation.put(neighbor, currentStation);
                    }
                }
            }
            nextStations = tmpNextStations;
        }

        // We've found the path by now
        boolean keepLooping = true;
        Station keyStation = end;
        Station station;
        while (keepLooping) {
            station = (Station)previousStation.get(keyStation);
            route.add(0, getConnection(station, keyStation));
            if (start.equals(station)) {
                keepLooping = false;
            }
            keyStation = station;
        }

        return route;
    }

    private Connection getConnection(Station start, Station end) {
        for (Connection connection : connections) {
            if (start.equals(connection.getStation1()) && end.equals(connection.getStation2())) {
                return connection;
            }
        }
        return null;
    }
}
