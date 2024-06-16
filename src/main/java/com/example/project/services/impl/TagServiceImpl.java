package com.example.project.services.impl;

import com.example.project.dto.TagDto;
import com.example.project.dto.template.ResponseTemplate;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.mapper.TagMapper;
import com.example.project.model.Tag;
import com.example.project.repository.TagRepository;
import com.example.project.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public ResponseTemplate<TagDto> createTag(TagDto tagDto) {
        Tag newTag = tagMapper.toTagEntity(tagDto);
        Tag savedTag = tagRepository.save(newTag);

        return ResponseTemplate.<TagDto>builder()
            .code(HttpStatus.CREATED.value())
            .message("Successfully created new tag!")
            .data(tagMapper.toTagDto(savedTag))
            .build();
    }

    @Override
    public ResponseTemplate<TagDto> deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tag cannot be found with id: " + id));

        tagRepository.delete(tag);
        return ResponseTemplate.<TagDto>builder()
            .code(HttpStatus.OK.value())
            .message("Tag deleted successfully!")
            .build();
    }
}
