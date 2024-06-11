package com.example.project.services.impl;

import com.example.project.dto.post.OfficeLocationDto;
import com.example.project.dto.template.ResponseTemplate;
import com.example.project.mapper.OfficeLocationMapper;
import com.example.project.model.OfficeLocation;
import com.example.project.repository.OfficeLocationRepository;
import com.example.project.services.OfficeLocationService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfficeLocationServiceImpl implements OfficeLocationService {
    private final OfficeLocationRepository officeLocationRepository;
    private final OfficeLocationMapper officeLocationMapper;

    @Override
    public ResponseTemplate<OfficeLocationDto> createOfficeLocation(
        OfficeLocationDto officeLocationDto) {
        OfficeLocation newOfficeLocation = officeLocationMapper.toOfficeLocationEntity(officeLocationDto);

        LocalDateTime now = LocalDateTime.now();
        newOfficeLocation.setCreatedAt(now);
        newOfficeLocation.setUpdatedAt(now);

        OfficeLocation savedOfficeLocation = officeLocationRepository.save(newOfficeLocation);

        return ResponseTemplate.<OfficeLocationDto>builder()
            .code(HttpStatus.CREATED.value())
            .message("Successfully created new office location!")
            .data(officeLocationMapper.toOfficeLocationDto(savedOfficeLocation))
            .build();
    }
}
