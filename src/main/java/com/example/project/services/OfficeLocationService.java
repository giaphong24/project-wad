package com.example.project.services;

import com.example.project.dto.post.OfficeLocationDto;
import com.example.project.dto.template.ResponseTemplate;
import org.springframework.stereotype.Service;

@Service
public interface OfficeLocationService {
    ResponseTemplate<OfficeLocationDto> createOfficeLocation(OfficeLocationDto officeLocationDto);
    ResponseTemplate<OfficeLocationDto> updateOfficeLocation(Long id, OfficeLocationDto officeLocationDto);
    ResponseTemplate<OfficeLocationDto> deleteOfficeLocation(Long id);

}
