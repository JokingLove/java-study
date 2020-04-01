package com.joking.springjpa.repository;

import com.joking.springjpa.document.MedicalImage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MedicalImageRepositoryTest{

    @Resource
    private MedicalImageRepository medicalImageRepository;

    @Test
    public void testSave() {
        MedicalImage image = new MedicalImage();
        image.setCode("aaa");
        image.setCreateTime(new Date());
        image.setDeptId("111");
        image.setFileName("haha");
        medicalImageRepository.save(image);
    }
}
