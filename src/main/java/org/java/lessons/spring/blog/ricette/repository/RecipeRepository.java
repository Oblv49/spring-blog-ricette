package org.java.lessons.spring.blog.ricette.repository;

import org.java.lessons.spring.blog.ricette.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
