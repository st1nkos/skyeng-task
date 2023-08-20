package ru.zhdanov.postApp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.zhdanov.postApp.entity.PostOffice;
import ru.zhdanov.postApp.repository.PostOfficeRepository;
import ru.zhdanov.postApp.service.PostOfficeService;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PostOfficeServiceTest {

    @Autowired
    public PostOfficeService postOfficeService;

    @MockBean
    public PostOfficeRepository postOfficeRepository;

    @BeforeEach
    public void setUp(){
        Mockito.when(postOfficeRepository.save(Mockito.any(PostOffice.class)))
                .thenReturn(new PostOffice(1L,"777777","СДЭК","Moscow"));
        Mockito.when(postOfficeRepository.findAll())
                .thenReturn(Arrays.asList(new PostOffice(),new PostOffice(),new PostOffice()));
    }


    @Test
    void saveTest(){
        PostOffice postOffice = new PostOffice();
        PostOffice savedPostOffice = postOfficeService.save(postOffice);

        Assertions.assertNotNull(savedPostOffice);
        Assertions.assertNotNull(savedPostOffice.getId());
    }

    @Test
    void findAllTest(){
        List<PostOffice> postOfficeList = postOfficeRepository.findAll();

        Assertions.assertNotNull(postOfficeList);
        Assertions.assertEquals(postOfficeList.size(), 3);
    }

}
