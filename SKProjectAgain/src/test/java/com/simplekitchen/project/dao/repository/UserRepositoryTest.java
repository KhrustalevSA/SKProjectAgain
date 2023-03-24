package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserRepositoryTest {

    UserRepository repository = Mockito.mock(UserRepository.class);

    @Before
    public void setUp() throws Exception {
        Mockito.when(repository.findByNameAndSurname(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(Collections.singletonList(UserEntityImpl.builder().build())));
        Mockito.when(repository.findByNameAndSurnameAndPatronymic(
                            Mockito.anyString(),
                                    Mockito.anyString(),
                                    Mockito.any()
                            )
                    ).thenReturn(Optional.of(Collections.singletonList(UserEntityImpl.builder().build())));
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void findByNameAndSurnameSuccess() {
        List<UserEntity> list;

        list = repository.findByNameAndSurname("Name", "Surname").orElse(Collections.emptyList());

        Assert.assertEquals(1, list.size());
    }

    @Test
    public void findByNameAndSurnameAndPatronymic() {
    }
}