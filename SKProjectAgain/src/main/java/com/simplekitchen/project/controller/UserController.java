package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
//mapstract переделать ДжаваДоки Ломбок наружу ДТО Бины
    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @PostMapping("/save")
    public ResponseEntity<UserImpl> save(@RequestBody UserImpl user) {
        return userService.save(user).map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping("/save/all")
    public ResponseEntity<List<UserImpl>> saveAll(@RequestBody List<UserImpl> users) {
        return new ResponseEntity<>(userService.saveAll(users), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<UserImpl> get(@RequestParam Long id) {
       return userService.get(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<UserImpl>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get/all/ids")
    public ResponseEntity<List<UserImpl>> getAllById(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(userService.getAllById(ids), HttpStatus.OK);
    }

    @PostMapping("/showUserEntity")
    public UserImpl showUserEntity(){
        return UserImpl.builder().id(1L).name("Ivan").surname("").patronymic("").build();
    }

    @PostMapping("/showUserListEntity")
    public List<UserImpl> showUserListEntity(){
        List<UserImpl> userList = new ArrayList<>();
        userList.add(UserImpl.builder().id(1L).name("Ivan").surname("").patronymic("").build());
        userList.add(UserImpl.builder().id(2L).name("Petr").surname("").patronymic("").build());
        return userList;
    }

    @PostMapping("/showGetAllById")
    public List<Long> showGetAllById(){
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        return list;
    }

}
