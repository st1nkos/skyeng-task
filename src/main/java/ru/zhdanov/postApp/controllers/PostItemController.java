package ru.zhdanov.postApp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhdanov.postApp.dto.PostItemDto;
import ru.zhdanov.postApp.entity.PostItem;
import ru.zhdanov.postApp.entity.PostMovement;
import ru.zhdanov.postApp.entity.Type;
import ru.zhdanov.postApp.mappers.PostMapper;
import ru.zhdanov.postApp.service.PostMovementService;
import ru.zhdanov.postApp.service.PostService;
import ru.zhdanov.postApp.service.TypeService;

import java.util.List;


@Tag(name = "Post Item api")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostItemController {

    private final PostMapper postMapper;

    private final PostMovementService postMovementService;

    private final TypeService typeService;
    private final PostService postService;


    @PostMapping("/registration")
    public ResponseEntity<PostItemDto> postRegistration(@RequestBody PostItemDto postItemDto) {
        Type type = typeService.findTypeByName(postItemDto.getType());
        PostItem postItem = postMapper.convertToPostItem(postItemDto);
        PostItem createdPostItem = postService.save(postItem,type);

        return new ResponseEntity<>(postMapper.convertToPostItemDto(createdPostItem), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<PostMovement>> getPostItemHistory(@PathVariable long id) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(postMovementService.getPostItemHistory(id));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> getMailItemStatus(@PathVariable long id) {
        return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(postService.getPostItemStatus(id));
    }


}
