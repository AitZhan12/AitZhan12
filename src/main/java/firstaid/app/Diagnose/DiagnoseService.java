package firstaid.app.Diagnose;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnoseService {

    @Autowired
    private DiagnoseRepository diagnoseRepository;

    public List<Diagnose> getAll() {
        return diagnoseRepository.findAll();
    }

    public Diagnose save(Diagnose diagnose) {
        diagnose =  diagnoseRepository.save(diagnose);
        return diagnose;
    }

    public void deleteById(Long id) throws NotFoundException {
        Optional<Diagnose> diagnose = diagnoseRepository.findById(id);

        if(diagnose.isPresent()) {
            diagnoseRepository.deleteById(id);
        } else {
            throw new NotFoundException("could not find any diagnose with such id");
        }
    }

    public Optional<Diagnose> getById(Long id) throws NotFoundException {
        Optional<Diagnose> diagnose = diagnoseRepository.findById(id);
        if(diagnose.isPresent()) {
            return diagnose;
        } else {
            throw new NotFoundException("could not find any diagnose with such id");
        }
    }
}
