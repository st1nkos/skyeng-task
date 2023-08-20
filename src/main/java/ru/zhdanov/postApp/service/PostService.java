package ru.zhdanov.postApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zhdanov.postApp.entity.PostItem;
import ru.zhdanov.postApp.entity.Type;
import ru.zhdanov.postApp.enums.Status;
import ru.zhdanov.postApp.repository.PostItemRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostItemRepository postItemRepository;

    @Transactional
    public PostItem save(PostItem postItem, Type type) {

        postItem.setType(type);

        if (postItem.getStatus()==null){
            postItem.setStatus(Status.READY_TO_SEND);

        }

        return postItemRepository.save(postItem);
    }

    public PostItem findItemById(long postItemId) {
        return postItemRepository.findById(postItemId).orElse(null);
    }


    public String getPostItemStatus(Long id) {
        PostItem postItem = postItemRepository.findById(id).orElse(null);


        if (postItem != null) {
            return postItem.getStatus().toString();
        }else {
            return "Отправление с таким номером не найдено";
        }
    }

    @Transactional
    public void update(PostItem postItem, Long id) {
       postItem.setId(id);
       postItem.setStatus(postItem.getStatus());
       postItemRepository.save(postItem);


    }
}
