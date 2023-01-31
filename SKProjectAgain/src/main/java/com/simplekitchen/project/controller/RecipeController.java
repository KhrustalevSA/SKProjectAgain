//package com.simplekitchen.project.controller;
//
//import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
//import com.simplekitchen.project.dao.entity.recipe.api.Recipe;
//import com.simplekitchen.project.dao.service.api.RecipeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
///**
// * РЕСТ контроллер для работы с рецептами
// */
//@RestController
//public class RecipeController {
//
//    private final RecipeService recipeService;
//
//    @Autowired
//    public RecipeController(RecipeService recipeService) {
//        this.recipeService = recipeService;
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<Recipe> save(@RequestBody Recipe recipe) {
//        return recipeService.save(recipe).map(u -> new ResponseEntity<>(u, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
//    }
//
//    @PostMapping("/save/all")
//    public ResponseEntity<List<Recipe>> saveAll(@RequestBody List<Recipe> recipeList) {
//        return new ResponseEntity<>(recipeService.saveAll(recipeList), HttpStatus.OK);
//    }
//
//    @GetMapping("/get")
//    public ResponseEntity<Recipe> get(@RequestParam Long id) {
//        return recipeService.get(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
//    }
//
//    @GetMapping("/get/all")
//    public ResponseEntity<List<Recipe>> getAll() {
//        return new ResponseEntity<>(recipeService.getAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/get/all/ids")
//    public ResponseEntity<List<Recipe>> getAllById(@RequestBody List<Long> ids) {
//        return new ResponseEntity<>(recipeService.getAllById(ids), HttpStatus.OK);
//    }
//
//    @PostMapping("showUserEntity")
//    public Recipe showRecipeEntity(){
//        return RecipeImpl.builder().name("recipe").build();
//    }
//
//    @PostMapping("showUserListEntity")
//    public List<Recipe> showRecipeListEntity(){
//        List<Recipe> recipeList = new ArrayList<>();
//        recipeList.add(RecipeImpl.builder().id(1L).name("Recipe1").build());
//        recipeList.add(RecipeImpl.builder().id(2L).name("Recipe2").build());
//        return recipeList;
//    }
//
//}
