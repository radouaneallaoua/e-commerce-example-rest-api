package com.allaoua.e_commerce.repositories;

import com.allaoua.e_commerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
