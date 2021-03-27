package firstaid.app.Diagnose;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("diagnose")
public class DiagnoseController {

    @Autowired
    private DiagnoseService ds;

    @GetMapping("getAll")
    public List<Diagnose> getAll() {
        return ds.getAll();
    }

    @PostMapping("save")
    public Diagnose save (Diagnose diagnose) {
        return ds.save(diagnose);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        ds.deleteById(id);
    }

    @GetMapping("get/{id}")
    public Optional<Diagnose> getById(@PathVariable("id") Long id) throws NotFoundException {
        Optional<Diagnose> diagnose = ds.getById(id);
        return diagnose;
    }

}
