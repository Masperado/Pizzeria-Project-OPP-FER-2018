package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.Ingredient;
import hr.fer.opp.pizzeriasystem.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;


    @Transactional
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findOne(Integer id) {
        return ingredientRepository.findOne(id);
    }
}
