package raf.web.turistickivodic.repositories.destination;

import raf.web.turistickivodic.entities.Destination;
import raf.web.turistickivodic.repositories.MySqlAbstractRepository;

import java.util.List;

public class MySqlDestRepository extends MySqlAbstractRepository implements DestinationRepository {


    @Override
    public Destination addDestination(Destination destination) {
        return null;
    }

    @Override
    public List<Destination> allDestinations() {
        return null;
    }

    @Override
    public Destination findDestination(Integer id) {
        return null;
    }

    @Override
    public void deleteDestination(Integer id) {

    }

    @Override
    public Destination editDestination(Integer id, Destination destination) {
        return null;
    }
}
