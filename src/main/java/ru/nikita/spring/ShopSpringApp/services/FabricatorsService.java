package ru.nikita.spring.ShopSpringApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikita.spring.ShopSpringApp.models.Fabricator;
import ru.nikita.spring.ShopSpringApp.repositories.FabricatorsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FabricatorsService {

    private final FabricatorsRepository fabricatorsRepository;

    @Autowired
    public FabricatorsService(FabricatorsRepository fabricatorsRepository) {
        this.fabricatorsRepository = fabricatorsRepository;
    }

    public List<Fabricator> findAll() {
        return fabricatorsRepository.findAll();
    }

    public Fabricator findById(int id) {
        return fabricatorsRepository.findById(id).orElse(null);
    }

    public void save(Fabricator fabricator) {
        fabricatorsRepository.save(fabricator);
    }

    public void deleteById(int id) {
        fabricatorsRepository.deleteById(id);
    }
}
