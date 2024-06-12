package com.example.project.services.impl;

import com.example.project.dto.post.OfficeLocationDto;
import com.example.project.dto.template.ResponseTemplate;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.mapper.OfficeLocationMapper;
import com.example.project.model.OfficeLocation;
import com.example.project.repository.OfficeLocationRepository;
import com.example.project.services.OfficeLocationService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    public ResponseTemplate<OfficeLocationDto> updateOfficeLocation(Long id, OfficeLocationDto officeLocationDto) {

        OfficeLocation officeLocation = officeLocationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Office Location not found with id: %s" .formatted(id)));

        LocalDateTime now = LocalDateTime.now();
        officeLocation.setUpdatedAt(now);

        if(officeLocationDto.getCity() != null){
            officeLocation.setCity(officeLocationDto.getCity().trim());
        }
        if(officeLocationDto.getOfficeName() != null){
            officeLocation.setOfficeName(officeLocationDto.getOfficeName().trim());
        }
        if(officeLocationDto.getStreet() != null){
            officeLocation.setStreet(officeLocationDto.getStreet().trim());
        }
        if(officeLocationDto.getDistrict() != null){
            officeLocation.setDistrict(officeLocationDto.getDistrict().trim());
        }
        if(officeLocationDto.getCountry() !=null ){
            officeLocation.setCountry(officeLocationDto.getCountry().trim());
        }

        OfficeLocation updatedOfficeLocation = officeLocationRepository.save(officeLocation);

        return ResponseTemplate.<OfficeLocationDto>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully edited new office location!")
                .data(officeLocationMapper.toOfficeLocationDto((updatedOfficeLocation)))
                .build();
    }
    @Override
    public ResponseTemplate<OfficeLocationDto> deleteOfficeLocation(Long id){
        OfficeLocation officeLocation = officeLocationRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Office Location can't be found with id: "+ id));

        officeLocationRepository.delete(officeLocation);
        return ResponseTemplate.<OfficeLocationDto>builder()
                .code(HttpStatus.OK.value())
                .message("Delete Office Location Successfully")
                .build();
    }

}
