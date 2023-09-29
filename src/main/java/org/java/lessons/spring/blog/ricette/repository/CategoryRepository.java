package org.java.lessons.spring.blog.ricette.repository;

import org.java.lessons.spring.blog.ricette.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
