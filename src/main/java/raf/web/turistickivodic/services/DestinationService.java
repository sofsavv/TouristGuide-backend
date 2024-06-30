package raf.web.turistickivodic.services;

import raf.web.turistickivodic.entities.Destination;
import raf.web.turistickivodic.repositories.destination.DestinationRepository;


import javax.inject.Inject;
import java.util.List;

public class DestinationService {

    @Inject
    private DestinationRepository destinationRepository;

    public Destination addDestination(Destination destination) {
        return this.destinationRepository.addDestination(destination);
    }

    public List<Destination> allDestinations() {
        return this.destinationRepository.allDestinations();
    }

    public Destination findDestination(Integer id) {
        return this.destinationRepository.findDestination(id);
    }

    public Destination updateDestination(Integer id, Destination destination){
        return this.destinationRepository.editDestination(id, destination);
    }
    public void deleteDestination(Integer id) {
        this.destinationRepository.deleteDestination(id);
    }

}
