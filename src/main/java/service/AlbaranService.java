package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojos.Albaranes;
import repository.AlbaranRepository;

@Service
public class AlbaranService {
    
    @Autowired
    private AlbaranRepository albarRepo;

    public List<Albaranes> obtenerTodosLosAlbaranes() {
        return albarRepo.findAll();
    }
}
