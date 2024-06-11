package com.example.project.mapper;

import com.example.project.dto.post.OfficeLocationDto;
import com.example.project.model.OfficeLocation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfficeLocationMapper {
    OfficeLocationDto toOfficeLocationDto(OfficeLocation officeLocation);
    OfficeLocation toOfficeLocationEntity(OfficeLocationDto officeLocationDto);
}
