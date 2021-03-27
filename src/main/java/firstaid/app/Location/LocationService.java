package firstaid.app.Location;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public void deleteById(Long id) throws NotFoundException {
        Optional<Location> location = locationRepository.findById(id);

        if(location.isPresent()) {
            locationRepository.deleteById(id);
        } else {
            throw new NotFoundException("could not find a location with such id" + id);
        }
    }

    public Optional<Location> getById(Long id) throws NotFoundException {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            return location;
        } else
        {
           throw new NotFoundException("could not found a location with such id" + id);
        }
    }
}
