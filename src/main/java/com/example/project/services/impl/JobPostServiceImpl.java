package com.example.project.services.impl;

import com.example.project.dto.post.JobPostRequest;
import com.example.project.dto.post.JobPostResponse;
import com.example.project.dto.template.ResponseTemplate;
import com.example.project.mapper.JobPostMapper;
import com.example.project.model.Jobpost;
import com.example.project.model.Tag;
import com.example.project.repository.JobpostRepository;
import com.example.project.repository.TagRepository;
import com.example.project.services.JobPostService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class JobPostServiceImpl implements JobPostService {
    private final JobpostRepository jobpostRepository;
    private final TagRepository tagRepository;
    private final JobPostMapper jobPostMapper;

    @Autowired
    public JobPostServiceImpl(
        JobpostRepository jobpostRepository,
        TagRepository tagRepository,
        JobPostMapper jobPostMapper
    ) {
        this.jobpostRepository = jobpostRepository;
        this.tagRepository = tagRepository;
        this.jobPostMapper = jobPostMapper;
    }

    @Override
    public ResponseTemplate<JobPostResponse> createJobPost(JobPostRequest data) {
        List<Tag> tags = tagRepository.findAllById(data.getTagId());
        Jobpost jobpost = jobPostMapper.toJobPostEntity(data);

        LocalDateTime now = LocalDateTime.now();
        jobpost.setCreatedAt(now);
        jobpost.setUpdatedAt(now);
        jobpost.setTags(tags);

        Jobpost savedJobPost = jobpostRepository.save(jobpost);

        JobPostResponse jobPostToResponse = jobPostMapper.toJobPostToResponse(savedJobPost);

        return ResponseTemplate.<JobPostResponse>builder()
            .code(HttpStatus.CREATED.value())
            .message("Request Job Post Successfully")
            .data(jobPostToResponse)
            .build();
    }
}
