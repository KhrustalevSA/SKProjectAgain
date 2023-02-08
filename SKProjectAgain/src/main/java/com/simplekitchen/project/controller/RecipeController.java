//package com.simplekitchen.project.controller;
//
//import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
//import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
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
//    public ResponseEntity<RecipeEntity> save(@RequestBody RecipeEntity recipe) {
//        return recipeService.save(recipe).map(u -> new ResponseEntity<>(u, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
//    }
//
//    @PostMapping("/save/all")
//    public ResponseEntity<List<RecipeEntity>> saveAll(@RequestBody List<RecipeEntity> recipeEntityList) {
//        return new ResponseEntity<>(recipeService.saveAll(recipeEntityList), HttpStatus.OK);
//    }
//
//    @GetMapping("/get")
//    public ResponseEntity<RecipeEntity> get(@RequestParam Long id) {
//        return recipeService.get(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
//    }
//
//    @GetMapping("/get/all")
//    public ResponseEntity<List<RecipeEntity>> getAll() {
//        return new ResponseEntity<>(recipeService.getAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/get/all/ids")
//    public ResponseEntity<List<RecipeEntity>> getAllById(@RequestBody List<Long> ids) {
//        return new ResponseEntity<>(recipeService.getAllById(ids), HttpStatus.OK);
//    }
//
//    @PostMapping("showUserEntity")
//    public RecipeEntity showRecipeEntity(){
//        return RecipeEntityImpl.builder().name("recipe").build();
//    }
//
//    @PostMapping("showUserListEntity")
//    public List<RecipeEntity> showRecipeListEntity(){
//        List<RecipeEntity> recipeEntityList = new ArrayList<>();
//        recipeEntityList.add(RecipeEntityImpl.builder().id(1L).name("Recipe1").build());
//        recipeEntityList.add(RecipeEntityImpl.builder().id(2L).name("Recipe2").build());
//        return recipeEntityList;
//    }
//
//}
