package org.java.lessons.spring.blog.ricette.controller;

import org.java.lessons.spring.blog.ricette.model.Recipe;
import org.java.lessons.spring.blog.ricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public String index(Model model) {
        List<Recipe> recipesList = recipeRepository.findAll();
        model.addAttribute("recipes", recipesList);
        if (recipesList.isEmpty()) {
            model.addAttribute("messaggio", "Nessuna Ricetta disponibile.");
        }
        return "/recipe/list";
    }

    @GetMapping("/show/{pizzaId}")
    public String show(@PathVariable("pizzaId") Integer id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipeFromDB = recipeOptional.get();
            model.addAttribute("recipe", recipeFromDB);
            return "/recipe/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
