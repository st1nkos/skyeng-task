package ru.zhdanov.postApp.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.zhdanov.postApp.dto.PostItemDto;
import ru.zhdanov.postApp.entity.PostItem;
import ru.zhdanov.postApp.entity.PostMovement;
import ru.zhdanov.postApp.entity.PostOffice;
import ru.zhdanov.postApp.entity.Type;
import ru.zhdanov.postApp.enums.Status;
import ru.zhdanov.postApp.mappers.PostMapper;
import ru.zhdanov.postApp.service.PostMovementService;
import ru.zhdanov.postApp.service.PostService;
import ru.zhdanov.postApp.service.TypeService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostItemControllerTest {

    @Mock
    PostMovementService postMovementService;

    @Mock
    PostMapper postMapper;

    @Mock
    PostService postService;

    @Mock
    TypeService typeService;
    @InjectMocks
    PostItemController postItemController;


    @Test
    void getPostItemHistoryTest() {

        PostItem postItem = new PostItem(1L, new Type(), 456776, "Snezhinsk", "Anton", Status.TRANSIT);
        var postMovementList = List.of(new PostMovement(1L, "Покинуло сортировку",
                        LocalDateTime.now(), Status.TRANSIT,
                        postItem,
                        new PostOffice(1L, "777777", "СДЭК", "Moscow")),
                new PostMovement(2L, "Прибыло на сортировку", LocalDateTime.now(), Status.TRANSIT,
                        postItem,
                        new PostOffice(2L, "434777", "СДЭК", "CHELYABINSK")));

        when(postMovementService.getPostItemHistory(postItem.getId())).thenReturn(postMovementList);
        var responseEntity = this.postItemController.getPostItemHistory(postItem.getId());

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        Assertions.assertEquals(postMovementList, responseEntity.getBody());
    }

    @Test
    void postRegistrationTest() {
        PostItem postItem = new PostItem(1L, new Type(1,"Посылка"), 456776, "Snezhinsk", "Anton", Status.TRANSIT);
        PostItemDto postItemDto = new PostItemDto("посылка",456776,"Snezhinsk", "Anton");
        Type type = new Type(1,"посылка");

        when(typeService.findTypeByName("посылка")).thenReturn(type);
        Type findType = typeService.findTypeByName(postItemDto.getType());
        when(postMapper.convertToPostItem(postItemDto)).thenReturn(postItem);
        PostItem convertedPostItem = postMapper.convertToPostItem(postItemDto);
        when(postService.save(convertedPostItem,findType)).thenReturn(postItem);
        PostItem savedItem = postService.save(convertedPostItem,findType);
        when(postMapper.convertToPostItemDto(savedItem)).thenReturn(postItemDto);
        ResponseEntity<PostItemDto> response = postItemController.postRegistration(postMapper.convertToPostItemDto(savedItem));

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Assertions.assertEquals(postItemDto,response.getBody());

    }
}
