/*
 * Author:  Annie Kim
 * Created: May 26, 2022
 * File: RecipeJdbc.java
 */

package com.annie.RecipeDaily.data;

import com.annie.RecipeDaily.models.Recipe;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeJdbc {

    @Autowired 
    protected NamedParameterJdbcTemplate jdbc; 
    
    //Retrieve recipes based on the first character of recipe name 
    public List<Recipe> getRecipeByName(String name){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
        String query = "SELECT recipe_name, ingredients FROM recipe WHERE recipe_name LIKE '" + name + "%'";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Recipe>(Recipe.class));
    }
}
