/*
 * Author:  Annie Kim
 * Created: May 26, 2022
 * File: RecipeRepo.java
 */

package com.annie.RecipeDaily.data;

import com.annie.RecipeDaily.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepo extends CrudRepository<Recipe, Integer> {
    Recipe findByRecipeName(String recipeName);
}
