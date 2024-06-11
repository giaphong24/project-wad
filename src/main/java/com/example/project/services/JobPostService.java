package com.example.project.services;

import com.example.project.dto.post.JobPostRequest;
import com.example.project.dto.post.JobPostResponse;
import com.example.project.dto.template.ResponseTemplate;
import org.springframework.stereotype.Service;

@Service
public interface JobPostService {
    ResponseTemplate<JobPostResponse> createJobPost(JobPostRequest data);

}
