package com.hassanmahmud.hmrecipeproject.repositories;

import com.hassanmahmud.hmrecipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
