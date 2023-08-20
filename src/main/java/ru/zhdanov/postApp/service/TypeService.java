package ru.zhdanov.postApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zhdanov.postApp.entity.Type;
import ru.zhdanov.postApp.repository.TypeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TypeService {


    private final TypeRepository typeRepository;


    public Type findTypeByName(String name){
        return typeRepository.findByName(name);
    }
}
