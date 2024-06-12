package com.example.project.mapper;

import com.example.project.dto.TagDto;
import com.example.project.model.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto toTagDto(Tag tag);
    Tag toTagEntity(TagDto tagDto);
}
