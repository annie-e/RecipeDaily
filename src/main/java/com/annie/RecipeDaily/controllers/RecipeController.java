/*
 * Author:  Annie Kim
 * Created: May 26, 2022
 * File: RecipeController.java
 */

package com.annie.RecipeDaily.controllers;

import com.annie.RecipeDaily.data.RecipeJdbc;
import com.annie.RecipeDaily.data.RecipeRepo;
import com.annie.RecipeDaily.models.Alphabet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date Created: 05-26-2022
 * @author Annie Kim
 */

@Controller
public class RecipeController {
    
    @Autowired
    RecipeRepo recipeRepo; 
    
    @Autowired 
    RecipeJdbc recipeJdbc;
    
    //Index Page with Alphabet enum 
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("alphabets", Alphabet.values());
        return "index";
    }
    
    //Retrieve and display recipes by the first character clicked
    @RequestMapping("/{firstAlphabet}")
    public String getPage(Model model, @PathVariable String firstAlphabet) {
        model.addAttribute("alphabets", Alphabet.values());
        model.addAttribute("recipes", recipeJdbc.getRecipeByName(firstAlphabet));
        return "recipeByName";
    }
}
