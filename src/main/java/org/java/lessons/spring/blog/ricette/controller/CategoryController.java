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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/category-list")
    public String showCategoryList(Model model) {
        List<Category> categoriesList = categoryRepository.findAll();
        model.addAttribute("categories", categoriesList);
        if (categoriesList.isEmpty()) {
            model.addAttribute("messaggio", "Nessuna offerta disponibile.");
        }
        return "category/categoryList";
    }

    @GetMapping("/category-create")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/categoryForm";
    }

    @PostMapping("/category-create")
    public String doCreateCategory(@ModelAttribute("category") Category category) {
        categoryRepository.save(category);
        return "redirect:/category-list";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategory(@PathVariable("id") int id, Model model) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            model.addAttribute("category", category);
            return "category/editCategoryForm";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit-category/{id}")
    public String doEditCategory(@PathVariable("id") int id,
                                 @Valid @ModelAttribute("category") Category categoryForm,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/editCategoryForm";
        }
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(categoryForm.getName());
            category.setDescription(categoryForm.getDescription());
            categoryRepository.save(category);
            return "redirect:/category-list";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/delete-category/{id}")
    public String deleteSpecialOffer(@PathVariable("id") Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            for (Recipe recipe : category.getRecipes()) {
                recipe.setCategory(null);
                recipeRepository.save(recipe);
            }
            categoryRepository.delete(category);
            return "redirect:/category-list";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
