package com.recipes.recipeproject.controllers;

import com.recipes.recipeproject.commands.RecipeCommand;
import com.recipes.recipeproject.services.ImageService;
import com.recipes.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/image")
    public String getImageForm(@PathVariable Long id, Model model){
        RecipeCommand recipeCommand=recipeService.findCommandById(id);
        model.addAttribute("recipe", recipeCommand);

        return "recipe/imageuploadform";
    }


    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable Long id, @RequestParam("file")MultipartFile file){
        imageService.saveImageFile(id, file);
        System.out.println("Image is saved: "+file.getName());
        return "redirect:/recipe/"+id+"/show";

    }
}
