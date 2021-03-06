/*
 * Author:  Annie Kim
 * Created: May 26, 2022
 * File: RecipeController.java
 */

package com.annie.RecipeDaily.controllers;

import com.annie.RecipeDaily.data.RecipeJdbc;
import com.annie.RecipeDaily.data.RecipeRepo;
import com.annie.RecipeDaily.models.Alphabet;
import com.annie.RecipeDaily.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    // Index Page with Alphabet enum 
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("alphabets", Alphabet.values());
        return "index";
    }
    
    // Retrieve and display recipes by the first character clicked
    @RequestMapping("/{firstAlphabet}")
    public String getPage(Model model, @PathVariable String firstAlphabet) {
        model.addAttribute("alphabets", Alphabet.values());
        model.addAttribute("recipes", recipeJdbc.getRecipeByName(firstAlphabet));
        return "recipeByName";
    }
    
    // Adding new recipes 
    @RequestMapping("/addRecipe") 
    public String addRecipe(Model model) {   
        model.addAttribute("alphabets", Alphabet.values());
        model.addAttribute("recipe", new Recipe());
        return "addRecipe";
    }
    
    @RequestMapping(value="/addNewRecipe", params="save") 
    public String addNewRecipe(@ModelAttribute Recipe recipe) {
        recipeRepo.save(recipe);
        return "redirect:/";
    }
    
    @RequestMapping(value="/addNewRecipe", params="cancel")
    public String cancelAdd() {
        return "redirect:/";
    }
    
    // Individual recipes 
    @RequestMapping("/individualRecipe")
    public String getIndividualRecipe(Model model, @RequestParam String recipeName) {
        model.addAttribute("alphabets", Alphabet.values());
        model.addAttribute("recipe", recipeRepo.findByRecipeName(recipeName));
        return "individualRecipe";
    }
    
    // Change recipes
    @RequestMapping(value="/changeRecipe", params="edit")
    public String editRecipe(Model model, @RequestParam int recipeId) {
        model.addAttribute("editRecipe", recipeRepo.findById(recipeId));
        return "editRecipe";
    }
    
    @RequestMapping(value="/changeRecipe", params="delete") 
    public String deleteRecipe(Model model, @RequestParam int recipeId) {
        Recipe recipe = recipeRepo.findById(recipeId).get();
        model.addAttribute("deleteRecipe", recipe); 
        return "deleteRecipe";
    }
    
    @RequestMapping(value="/editRecipe", params="save")
    public String editRecipe(@ModelAttribute Recipe editRecipe) {
        recipeRepo.save(editRecipe);
        return "redirect:/";
    }
    
    @RequestMapping(value="/editRecipe", params="cancel")
    public String cancelEdit() {
        return "redirect:/";
    }
    
    @RequestMapping(value="/deleteRecipe", params="delete")
    public String deleteRecipe(@ModelAttribute Recipe deleteRecipe) { 
        recipeRepo.delete(deleteRecipe);
        return "redirect:/";
    }
    
    @RequestMapping(value="/deleteRecipe", params="cancel")
    public String cancelDelete() {
        return "redirect:/";
    }
}
