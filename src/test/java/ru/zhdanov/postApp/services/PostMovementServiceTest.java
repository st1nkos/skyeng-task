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
import ru.zhdanov.postApp.entity.PostItem;
import ru.zhdanov.postApp.entity.PostMovement;
import ru.zhdanov.postApp.entity.PostOffice;
import ru.zhdanov.postApp.enums.Status;
import ru.zhdanov.postApp.repository.PostMovementRepository;
import ru.zhdanov.postApp.service.PostMovementService;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PostMovementServiceTest {

    @Autowired
    public PostMovementService postMovementService;

    @MockBean
    public PostMovementRepository postMovementRepository;

    @BeforeEach
    public void setUp(){
        Mockito.when(postMovementRepository.save(Mockito.any(PostMovement.class)))
                .thenReturn(new PostMovement(1L,"Покинуло сортировку",
                        LocalDateTime.now(), Status.TRANSIT,
                        new PostItem(),new PostOffice()));
    }


    @Test
    void saveMovementTest(){
        PostMovement postMovement = new PostMovement();
        PostMovement savedPostMovement = postMovementService.saveMovement(postMovement,1L,1L);

        Assertions.assertNotNull(savedPostMovement);
        Assertions.assertNotNull(savedPostMovement.getId());

    }


    @Test
    void receivedTest(){
        PostMovement postMovement = new PostMovement();
        PostMovement savedMovement = postMovementService.saveMovement(postMovement,1L,1L);

        PostMovement received = postMovementService.received(savedMovement,1L,1L);

        Assertions.assertNotNull(received);
        Assertions.assertNotNull(received.getId());
        Assertions.assertEquals(received.getMovementStatus(),Status.RECEIVED);
    }

}
