package tr.com.t2giants.ring.server.data.builder;

import tr.com.t2giants.ring.server.data.Coordinates;

/**
 * User: soner
 * Date: 1/15/13
 */
public class CoordinatesBuilder extends Builder<Coordinates, CoordinatesBuilder> {

    private Double lat;
    private Double lon;

    @Override
    public Coordinates build() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(lat);
        coordinates.setLon(lon);
        return coordinates;
    }

    public CoordinatesBuilder latitude(Double latitude) {
        this.lat = latitude;
        return this;
    }

    public CoordinatesBuilder longitude(Double longitude) {
        this.lon = longitude;
        return this;
    }

    public CoordinatesBuilder coordinates(Double latitude, Double longitude) {
        this.lat = latitude;
        this.lon = longitude;

        return this;
    }

}
