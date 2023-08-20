package ru.zhdanov.postApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zhdanov.postApp.entity.PostOffice;
import ru.zhdanov.postApp.repository.PostOfficeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostOfficeService {

    private final PostOfficeRepository postOfficeRepository;


    @Transactional
    public PostOffice save(PostOffice postOffice) {
        return postOfficeRepository.save(postOffice);
    }

    public List<PostOffice> findAll() {
     return   postOfficeRepository.findAll();
    }

    public PostOffice findOfficeById(long postItemId) {
        return postOfficeRepository.findById(postItemId).orElse(null);
    }


}
