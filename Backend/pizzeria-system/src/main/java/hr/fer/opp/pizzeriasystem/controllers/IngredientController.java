package hr.fer.opp.pizzeriasystem.controllers;

import hr.fer.opp.pizzeriasystem.models.Ingredient;
import hr.fer.opp.pizzeriasystem.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    //svi sastojci
    @GetMapping("/ingredient")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return new ResponseEntity<>(ingredientService.findAll(),HttpStatus.OK);
    }

    //1 sastojak
    @GetMapping("/ingredient/{id}")
    public ResponseEntity<Ingredient> getAllIngredients(@PathVariable Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id not found!");
        }
        Ingredient ingredient = ingredientService.findOne(id);

        if (ingredient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }
}
