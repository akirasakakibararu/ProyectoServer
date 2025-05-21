package service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.DetallesAlbaranRepository;
import pojos.DetallesAlbaran;

@Service
public class DetallesAlbaranService {

    @Autowired
    private DetallesAlbaranRepository repository;

    public List<DetallesAlbaran> guardarVariosDetalles(List<DetallesAlbaran> detalles) {
        return repository.saveAll(detalles);
    }
}
