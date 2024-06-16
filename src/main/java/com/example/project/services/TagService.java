package com.example.project.services;

import com.example.project.dto.TagDto;
import com.example.project.dto.template.ResponseTemplate;
import org.springframework.stereotype.Service;

@Service
public interface TagService {
    ResponseTemplate<TagDto> createTag(TagDto tagDto);

}
