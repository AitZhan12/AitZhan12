package firstaid.app.Location;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("getAll")
    public List<Location> getAll () {
        return locationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Location> getById(@PathVariable("id") Long id) throws NotFoundException {
        return locationService.getById(id);
    }

    @PostMapping("save")
    public Location save(@RequestBody Location location) {
        return locationService.save(location);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        locationService.deleteById(id);
    }

    }

