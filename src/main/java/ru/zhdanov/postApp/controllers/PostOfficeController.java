package ru.zhdanov.postApp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhdanov.postApp.dto.PostMovementDto;
import ru.zhdanov.postApp.dto.PostOfficeDto;
import ru.zhdanov.postApp.entity.PostMovement;
import ru.zhdanov.postApp.entity.PostOffice;
import ru.zhdanov.postApp.mappers.PostMapper;
import ru.zhdanov.postApp.service.PostMovementService;
import ru.zhdanov.postApp.service.PostOfficeService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Post Office api")
@RestController
@RequestMapping("/post-office")
@RequiredArgsConstructor
public class PostOfficeController {

    private final PostMapper postMapper;

    private final PostOfficeService postOfficeService;

    private final PostMovementService postMovementService;


    @PostMapping("/create")
    public ResponseEntity<PostOfficeDto> postRegistration(@RequestBody PostOfficeDto postOfficeDto) {

        PostOffice postOffice = postMapper.convertToPostOffice(postOfficeDto);
        PostOffice createdPostOffice = postOfficeService.save(postOffice);
        return new ResponseEntity<>(postMapper.convertToPostOfficeDto(createdPostOffice), HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<PostOfficeDto>> getAllPostOffices() {

        return new ResponseEntity<>(postOfficeService.findAll().stream()
                .map(postMapper::convertToPostOfficeDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }


    @PostMapping("/arrive/{postItemId}/{postOfficeId}")
    public ResponseEntity<PostMovementDto> arriveAtPostOffice(@RequestBody PostMovementDto postMovementDto,
                                                              @PathVariable long postItemId,
                                                              @PathVariable long postOfficeId) {

        PostMovement postMovement = postMapper.convertToPostMovement(postMovementDto);
        PostMovement createdArriveAtOffice = postMovementService.saveMovement(postMovement, postItemId, postOfficeId);


        return new ResponseEntity<>(postMapper.convertToPostMovementDto(createdArriveAtOffice), HttpStatus.CREATED);

    }

    @PostMapping("/depart/{postItemId}/{postOfficeId}")
    public ResponseEntity<PostMovementDto> departFromPostOffice(@RequestBody PostMovementDto postMovementDto,
                                                                @PathVariable long postItemId,
                                                                @PathVariable long postOfficeId) {

        PostMovement postMovement = postMapper.convertToPostMovement(postMovementDto);
        PostMovement createdArriveAtOffice = postMovementService.saveMovement(postMovement, postItemId, postOfficeId);

        return new ResponseEntity<>(postMapper.convertToPostMovementDto(createdArriveAtOffice), HttpStatus.CREATED);

    }

    @PostMapping("/received/{postItemId}/{postOfficeId}")
    public ResponseEntity<PostMovementDto> receivedByRecipient(@RequestBody PostMovementDto postMovementDto,
                                                               @PathVariable long postItemId,
                                                               @PathVariable long postOfficeId) {

        PostMovement postMovement = postMapper.convertToPostMovement(postMovementDto);
        PostMovement createdArriveAtOffice = postMovementService.received(postMovement, postItemId, postOfficeId);

        return new ResponseEntity<>(postMapper.convertToPostMovementDto(createdArriveAtOffice), HttpStatus.CREATED);

    }


}
