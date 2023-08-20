package ru.zhdanov.postApp.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.zhdanov.postApp.dto.PostMovementDto;
import ru.zhdanov.postApp.dto.PostOfficeDto;
import ru.zhdanov.postApp.entity.PostMovement;
import ru.zhdanov.postApp.entity.PostOffice;
import ru.zhdanov.postApp.mappers.PostMapper;
import ru.zhdanov.postApp.service.PostMovementService;
import ru.zhdanov.postApp.service.PostOfficeService;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostOfficeControllerTest {

    @Mock
    PostMapper postMapper;

    @Mock
    PostOfficeService postOfficeService;

    @Mock
    PostMovementService postMovementService;

    @InjectMocks
    PostOfficeController postOfficeController;


    @Test
    void postRegistrationTest(){
        PostOffice postOffice = new PostOffice(1L,"777777","СДЭК","Moscow");
        PostOfficeDto postOfficeDto = new PostOfficeDto("777777","СДЭК","Moscow");

        when(postMapper.convertToPostOffice(postOfficeDto)).thenReturn(postOffice);
        PostOffice convertedPostOffice = postMapper.convertToPostOffice(postOfficeDto);
        when(postOfficeService.save(convertedPostOffice)).thenReturn(postOffice);
        PostOffice savedPostOffice = postOfficeService.save(convertedPostOffice);
        when(postMapper.convertToPostOfficeDto(savedPostOffice)).thenReturn(postOfficeDto);

        ResponseEntity<PostOfficeDto> response = postOfficeController.postRegistration(postMapper.convertToPostOfficeDto(savedPostOffice));


        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Assertions.assertEquals(postOfficeDto,response.getBody());

    }

    @Test
    void getAllPostOfficesTest(){
        List<PostOffice> postOfficeList = List.of(new PostOffice(), new PostOffice(),new PostOffice());

        when(postOfficeService.findAll()).thenReturn(postOfficeList);
        List<PostOffice> findList = postOfficeService.findAll();
        when(postMapper.convertToPostOfficeDto(new PostOffice())).thenReturn(new PostOfficeDto());
        List<PostOfficeDto> convertedList = findList.stream().map(el->postMapper.convertToPostOfficeDto(el)).toList();
        ResponseEntity<List<PostOfficeDto>> response = postOfficeController.getAllPostOffices();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(response.getBody().size(),3);
        Assertions.assertEquals(convertedList,response.getBody());

    }

    @Test
    void postArriveAtPostOfficeTest(){
        PostMovementDto postMovementDto = new PostMovementDto();
        PostMovement postMovement = new PostMovement();
        long postItemId = 1L;
        long postOfficeId=1L;

        when(postMapper.convertToPostMovement(postMovementDto)).thenReturn(postMovement);
        PostMovement convertedPostMovement = postMapper.convertToPostMovement(postMovementDto);
        when(postMovementService.saveMovement(convertedPostMovement,postItemId,postOfficeId)).thenReturn(postMovement);
        PostMovement savedPostMovement = postMovementService.saveMovement(convertedPostMovement,postItemId,postOfficeId);
        when(postMapper.convertToPostMovementDto(savedPostMovement)).thenReturn(postMovementDto);
        PostMovementDto convertedPostMovementDto = postMapper.convertToPostMovementDto(savedPostMovement);

        ResponseEntity<PostMovementDto> response = postOfficeController.arriveAtPostOffice(convertedPostMovementDto,postItemId,postOfficeId);


        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Assertions.assertEquals(convertedPostMovementDto,response.getBody());
    }

    @Test
    void postDepartFromPostOfficeTest(){
        PostMovementDto postMovementDto = new PostMovementDto();
        PostMovement postMovement = new PostMovement();
        long postItemId = 1L;
        long postOfficeId=1L;

        when(postMapper.convertToPostMovement(postMovementDto)).thenReturn(postMovement);
        PostMovement convertedPostMovement = postMapper.convertToPostMovement(postMovementDto);
        when(postMovementService.saveMovement(convertedPostMovement,postItemId,postOfficeId)).thenReturn(postMovement);
        PostMovement savedPostMovement = postMovementService.saveMovement(convertedPostMovement,postItemId,postOfficeId);
        when(postMapper.convertToPostMovementDto(savedPostMovement)).thenReturn(postMovementDto);
        PostMovementDto convertedPostMovementDto = postMapper.convertToPostMovementDto(savedPostMovement);

        ResponseEntity<PostMovementDto> response = postOfficeController.departFromPostOffice(convertedPostMovementDto,postItemId,postOfficeId);


        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Assertions.assertEquals(convertedPostMovementDto,response.getBody());
    }




}
