package tr.com.t2giants.ring.server.data;

/**
 * User: sonic
 * Date: 1/11/13
 */
public class Coordinates {

    private Double lat;
    private Double lon;

    public Coordinates() {
    }

    public Coordinates(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        return Double.compare(that.lat, lat) == 0 && Double.compare(that.lon, lon) == 0;
    }


}
