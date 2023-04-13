package com.simplekitchen.project.business.mapper;

import com.simplekitchen.project.business.mapper.common.CommonMapper;
import com.simplekitchen.project.dao.service.api.RecipeService;
import com.simplekitchen.project.dao.service.api.UserService;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.common.api.LongList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class CommonMapperMethodsTest {

    private final UserService daoUserService = Mockito.mock(UserService.class);
    private final RecipeService daoRecipeService = Mockito.mock(RecipeService.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void mapLongListDtoToDaoSuccess() {
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(2L);
        longList.add(3L);
        LongList dtoLongList = LongListImpl.builder().longList(longList).build();
        com.simplekitchen.project.dao.entity.common.api.LongList daoLongList = null;

        daoLongList = CommonMapper.INSTANCE.map(dtoLongList);

        Assert.assertEquals(longList, daoLongList.getLongList());
    }

    @Test
    public void mapLongListDaoToDtoSuccess() {
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(2L);
        longList.add(3L);
        com.simplekitchen.project.dao.entity.common.api.LongList daoLongList
                = com.simplekitchen.project.dao.entity.common.LongListImpl.builder()
                .longList(longList)
                .build();
        LongList dtoLongList = null;

        dtoLongList = CommonMapper.INSTANCE.map(daoLongList);

        Assert.assertEquals(longList, dtoLongList.getLongList());
    }
}