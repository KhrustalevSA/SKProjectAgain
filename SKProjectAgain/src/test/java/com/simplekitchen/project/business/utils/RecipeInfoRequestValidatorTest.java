package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.dto.entity.recipe.RecipeRequestInfoImpl;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeInfoRequestValidatorTest {

    @Test
    public void validateSuccess() {
        RecipeInfoRequestValidator requestValidator = new RecipeInfoRequestValidator();
        RecipeRequestInfoImpl requestInfo = RecipeRequestInfoImpl.builder()
                .id(1L)
                .name("Name")
                .build();

        try {
            requestValidator.validate(requestInfo);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

    @Test
    public void validateForIdSuccess() {
        RecipeInfoRequestValidator requestValidator = new RecipeInfoRequestValidator();
        RecipeRequestInfoImpl requestInfo = RecipeRequestInfoImpl.builder()
                .id(1L)
                .build();

        try {
            requestValidator.validate(requestInfo);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

    @Test
    public void validateForNameSuccess() {
        RecipeInfoRequestValidator requestValidator = new RecipeInfoRequestValidator();
        RecipeRequestInfoImpl requestInfo = RecipeRequestInfoImpl.builder()
                .name("Name")
                .build();

        try {
            requestValidator.validate(requestInfo);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

    @Test
    public void validateFail() {
        RecipeInfoRequestValidator requestValidator = new RecipeInfoRequestValidator();
        RecipeRequestInfoImpl requestInfo = RecipeRequestInfoImpl.builder().build();

        Assert.assertThrows(ValidationException.class,() -> requestValidator.validate(requestInfo));

    }
}