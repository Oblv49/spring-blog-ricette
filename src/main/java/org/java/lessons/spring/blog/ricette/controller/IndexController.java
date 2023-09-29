package org.java.lessons.spring.blog.ricette.controller;

import jakarta.validation.Valid;
import org.java.lessons.spring.blog.ricette.model.Category;
import org.java.lessons.spring.blog.ricette.model.Recipe;
import org.java.lessons.spring.blog.ricette.repository.CategoryRepository;
import org.java.lessons.spring.blog.ricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model) {
        List<Recipe> recipesList = recipeRepository.findAllByOrderByCreationDateDesc();
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

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", categoryRepository.findAll());
        return "recipe/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Recipe recipeForm,
                           @RequestParam("category.id") int categoryId,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "recipe/form";
        }
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        optionalCategory.ifPresent(recipeForm::setCategory);
        recipeForm.setCreationDate(LocalDateTime.now());
        recipeRepository.save(recipeForm);
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Recipe> result = recipeRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("recipe", result.get());
            return "recipe/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("recipe") Recipe recipeForm,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "recipe/edit";
        }
        recipeRepository.save(recipeForm);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        recipeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam("q") String searchString, Model model) {
        List<Recipe> filteredPizzaList = recipeRepository.findByTitleContaining(searchString);
        if (filteredPizzaList.isEmpty()) {
            model.addAttribute("noResults", true);
        }
        model.addAttribute("recipes", filteredPizzaList);
        return "recipe/list";
    }
}
