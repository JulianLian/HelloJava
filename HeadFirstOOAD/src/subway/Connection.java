package subway;

public class Connection {
    private Station station1, station2;
    private String lineName;

    public Connection(Station station1, Station station2, String lineName) {
        this.station1 = station1;
        this.station2 = station2;
        this.lineName = lineName;
    }

    public Station getStation1() {
        return station1;
    }

    public Station getStation2() {
        return station2;
    }

    public String getLineName() {
        return lineName;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Connection) {
            Connection otherConnection = (Connection)obj;
            return otherConnection.station1.equals(station1) &&
                    otherConnection.station2.equals(station2) &&
                    otherConnection.lineName.equalsIgnoreCase(lineName);
        }
        return false;
    }

    public int hashCode() {
        return station1.hashCode() & station2.hashCode() & lineName.hashCode();
    }
}
