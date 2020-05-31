package com.young;

import com.young.dao.BlogDao;
import com.young.entities.Type;
import com.young.service.BlogService;
import com.young.service.TagService;
import com.young.service.TypeService;
import com.young.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class YoungblogApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    UserService userService;
    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;

    @Autowired
    TagService tagService;

    @Autowired
    BlogDao blogDao;
    @Test
    void contextLoads() throws SQLException {

        List<Type> types = typeService.getAllType();
        System.out.println(types);

    }


}
