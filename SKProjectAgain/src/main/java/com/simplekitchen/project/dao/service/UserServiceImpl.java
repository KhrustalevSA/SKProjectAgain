package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.repository.UserRepository;
import com.simplekitchen.project.dao.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SessionFactory sessionFactory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public Optional<UserImpl> save(UserImpl user) {

        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return Optional.of(userRepository.save(user));
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public List<UserImpl> saveAll(List<UserImpl> users) {
        return Lists.newArrayList(userRepository.saveAll(users));
    }

    @Override
    public Optional<UserImpl> get(Long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (session.get(UserImpl.class,id) != null
                && userRepository.findById(id).isPresent()) {
            if (session.get(UserImpl.class,id) == userRepository.findById(id).get()) {
                transaction.commit();
                session.close();
                return userRepository.findById(id);
            } else {
                log.debug("content is not equals");
                return Optional.empty();
            }
        }

        transaction.commit();
        session.close();
        return Optional.empty();
    }

    @Override
    public List<UserImpl> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public List<UserImpl> getAllById(List<Long> ids) {
        return Lists.newArrayList(userRepository.findAllById(ids));
    }

    @Override
    public Boolean deleteById(Long id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }

    @Override
    public Boolean delete(UserImpl user) {
        userRepository.delete(user);
        return !userRepository.findById(user.getId()).isPresent();
    }

    @Override
    public Boolean deleteAll(List<UserImpl> users) {
        userRepository.deleteAll(users);
        return Lists.newArrayList(userRepository.findAllById(users.stream().map(UserImpl::getId).collect(Collectors.toList()))).isEmpty();
    }

    @Override
    public Boolean deleteAll() {
        userRepository.deleteAll();
        return Lists.newArrayList(userRepository.findAll()).isEmpty();
    }

}
