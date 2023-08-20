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
import ru.zhdanov.postApp.entity.Type;
import ru.zhdanov.postApp.enums.Status;
import ru.zhdanov.postApp.repository.PostItemRepository;
import ru.zhdanov.postApp.service.PostService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PostServiceTest {

 @Autowired
 public PostService postService;
 @MockBean
 public PostItemRepository postItemRepository;


 @BeforeEach
 public void setUp(){
  Mockito.when(postItemRepository.save(Mockito.any(PostItem.class)))
          .thenReturn(new PostItem(1L,new Type(),456776,"Snezhinsk","Anton", Status.RECEIVED));

  Type type = new Type(1,"посылка");
 }

 @Test
 void saveTest(){
  PostItem postItem = new PostItem();
  Type type = new Type();
  PostItem savedPostItem = postService.save(postItem,type);

  Assertions.assertNotNull(savedPostItem);
  Assertions.assertNotNull(savedPostItem.getId());
 }


 @Test
 void itemUpdateTest(){
  PostItem postItem = new PostItem();
  Type type = new Type();
  PostItem savedItem = postService.save(postItem,type);
  savedItem.setName("Nikita");
  savedItem.setZip(123456);

   PostItem updatedItem =  postService.save(savedItem,type);



  Assertions.assertNotNull(updatedItem);
  Assertions.assertNotNull(updatedItem.getId());
  Assertions.assertEquals(updatedItem.getName(),"Nikita");

 }

}
