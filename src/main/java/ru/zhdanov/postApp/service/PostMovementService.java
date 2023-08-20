package ru.zhdanov.postApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zhdanov.postApp.entity.PostItem;
import ru.zhdanov.postApp.entity.PostMovement;
import ru.zhdanov.postApp.enums.Status;
import ru.zhdanov.postApp.repository.PostMovementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostMovementService {

    private final PostMovementRepository postMovementRepository;

    private final PostService postService;

    private final  PostOfficeService postOfficeService;
    public List<PostMovement> getPostItemHistory(long id) {
        return postMovementRepository.findByPostItemIdOrderByMovementDate(id);
    }

    @Transactional
    public PostMovement saveMovement(PostMovement postMovement, long postItemId, long postOfficeId) {

        postMovement.setPostItem(postService.findItemById(postItemId));
        postMovement.setPostOffice(postOfficeService.findOfficeById(postOfficeId));
        postMovement.setMovementStatus(Status.TRANSIT);
        postMovement.setMovementDate(LocalDateTime.now());
        PostItem postItem = postService.findItemById(postItemId);

        if (postItem.getStatus()!= Status.TRANSIT){
            postItem.setStatus(Status.TRANSIT);
            postService.update(postItem,postItem.getId());
        }



        return postMovementRepository.save(postMovement);
    }

    @Transactional
    public PostMovement received(PostMovement postMovement, long postItemId, long postOfficeId) {
        postMovement.setPostItem(postService.findItemById(postItemId));
        postMovement.setPostOffice(postOfficeService.findOfficeById(postOfficeId));
        postMovement.setMovementStatus(Status.RECEIVED);
        postMovement.setMovementDate(LocalDateTime.now());
        PostItem postItem = postService.findItemById(postItemId);

        if (postItem.getStatus()!= Status.RECEIVED){
            postItem.setStatus(Status.RECEIVED);
            postService.update(postItem,postItem.getId());
        }

        return postMovementRepository.save(postMovement);
    }
}
