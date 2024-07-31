package raf.web.turistickivodic.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Destination {

    private Integer destinationId;
    @NotNull(message = "Destination name is required")
    @NotEmpty(message = "Destination name is required")
    private String destination;
    @NotNull(message = "Description about destination is required")
    @NotEmpty(message = "Description about destination is required")
    private String about;

    private Destination(){}
    public Destination(Integer destinationId, String destination, String about) {
        this.destinationId = destinationId;
        this.destination = destination;
        this.about = about;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
