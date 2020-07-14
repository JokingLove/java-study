package com.joking.test.repository;


import com.joking.test.JavaTestApplication;
import com.joking.test.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = JavaTestApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        User user = new User();
        user.setName("张三33")
        .setPassword("haha")
        .setBirthday(new Date());
        User.Address address = new User.Address();
        address.setId("1")
                .setProvince("北京市")
                .setCity("海淀区");
        user.setAddress(address);
        userRepository.save(user);
    }

    @Test
    public void testFindByAddress() {
        userRepository.getUserByAddressProvinceLike("北京")
                .ifPresent(i -> i.forEach(System.out::println));

//        userRepository.getUserByAddressProvince("北京市")
//                .ifPresent(i -> i.forEach(System.out::println));
    }

    @Test
    public void testUpdate() {
//        userRepository.insert()
    }


    @Test
    public void testFind() {


//        userRepository.getUserByNameLike("赵")
//                .ifPresent(list -> list.forEach(System.out::println));

//        userRepository.getUserByName("三")
//                .ifPresent(System.out::println);

//        userRepository.findAll()
//                .forEach(System.out::println);

//        User user = new User().setName("张三");

//        userRepository.findOne(Example.of(user))
//                .ifPresent(System.out::println);

    }
}
