package com.recipes.recipeproject.services;

import com.recipes.recipeproject.domain.Recipe;
import com.recipes.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile imagefile) {
        try{
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[imagefile.getBytes().length];

            int i=0;
            for(byte b: imagefile.getBytes()){
                byteObjects[i++]=b;
            }
            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);
        }catch (IOException e){
            log.error("Error occurred: ", e);

            e.printStackTrace();
        }
    }
}
