package com.recipes.recipeproject.converters;

import com.recipes.recipeproject.commands.UnitOfMeasureCommand;
import com.recipes.recipeproject.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(unitOfMeasureCommand==null)return null;
        final UnitOfMeasure uod = new UnitOfMeasure();
        uod.setId(unitOfMeasureCommand.getId());
        uod.setDescription(unitOfMeasureCommand.getDescription());
        return uod;
    }
}
