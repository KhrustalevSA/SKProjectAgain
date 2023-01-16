//package com.simplekitchen.project.dao.repository;
//
//import com.simplekitchen.project.dao.entity.user.UserImpl;
//import com.simplekitchen.project.dao.entity.user.api.User;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//public interface UserRepository extends CrudRepository<UserImpl, Long> {
//
//
//    Optional<User> findByUuid(Long id);
//    Iterable<UserImpl> findByNameAndAndSurnameAndPatronymic(String name, String surname, String Patronymic);
//    Iterable<UserImpl> findAll();
//    User findByUserName(String name, String surname, String patronymic);
//
//}
