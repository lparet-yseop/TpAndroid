package fr.louisparet.journeydiaries.models;

/**
 * Created by hugo.blanc on 11/10/17.
 */

public class Marker {

    private Long id;
    private Double longitude;
    private Double latitude;
    private String name;


    public Marker() {
    }

    public Marker(Long id, Double longitude, Double latitude, String name) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }

    public Marker(Double longitude, Double latitude, String name) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
