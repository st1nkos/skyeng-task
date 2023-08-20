package ru.zhdanov.postApp.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zhdanov.postApp.dto.PostItemDto;
import ru.zhdanov.postApp.dto.PostMovementDto;
import ru.zhdanov.postApp.dto.PostOfficeDto;
import ru.zhdanov.postApp.entity.PostItem;
import ru.zhdanov.postApp.entity.PostMovement;
import ru.zhdanov.postApp.entity.PostOffice;

@Component
public class PostMapper {


    private final ModelMapper modelMapper;
    @Autowired
    public PostMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PostItemDto convertToPostItemDto(PostItem postItem){
        return modelMapper.map(postItem,PostItemDto.class);
    }

    public PostItem convertToPostItem(PostItemDto postItemDto){
        return modelMapper.map(postItemDto,PostItem.class);
    }

    public PostOfficeDto convertToPostOfficeDto(PostOffice postOffice){
        return modelMapper.map(postOffice,PostOfficeDto.class);
    }

    public PostOffice convertToPostOffice(PostOfficeDto postOfficeDto){
        return modelMapper.map(postOfficeDto,PostOffice.class);
    }

    public PostMovementDto convertToPostMovementDto(PostMovement postMovement){
        return modelMapper.map(postMovement,PostMovementDto.class);
    }
    public PostMovement convertToPostMovement(PostMovementDto postMovementDto){
        return modelMapper.map(postMovementDto,PostMovement.class);
    }
}
