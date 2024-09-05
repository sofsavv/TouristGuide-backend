package raf.web.turistickivodic.repositories.destination;

import raf.web.turistickivodic.entities.Destination;

import java.util.List;

public interface DestinationRepository {

    public Destination addDestination(Destination destination);
    public List<Destination> allDestinations(int currentPage, int pageSize);
    public Destination findDestination(Integer id);
    public void deleteDestination(Integer id);
    public Destination editDestination(Integer id, Destination destination);
}
