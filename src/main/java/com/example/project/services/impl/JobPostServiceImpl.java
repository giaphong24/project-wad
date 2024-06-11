package com.example.project.services.impl;

import com.example.project.dto.post.JobPostRequest;
import com.example.project.dto.post.JobPostResponse;
import com.example.project.dto.template.ResponseTemplate;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.mapper.JobPostMapper;
import com.example.project.model.Jobpost;
import com.example.project.model.Tag;
import com.example.project.repository.JobpostRepository;
import com.example.project.repository.TagRepository;
import com.example.project.services.JobPostService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    @Override
    public ResponseTemplate<JobPostResponse> updateJobPost(Long id, JobPostRequest jobPostRequest){
        Jobpost jobpost = jobpostRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Job Post not found with id: %s".formatted(id)));
        LocalDateTime now = LocalDateTime.now();
        jobpost.setCreatedAt(now);
        jobpost.setUpdatedAt(now);

        Jobpost savedJobPost = jobpostRepository.save(jobpost);

        if(jobPostRequest.getJobTitle() != null){
            jobpost.setJobTitle(jobPostRequest.getJobTitle().trim());
        }
        if(jobPostRequest.getJobDescription() != null){
            jobpost.setJobDescription(jobPostRequest.getJobDescription().trim());
        }
        if(jobPostRequest.getPosition() != null){
            jobpost.setPosition(jobPostRequest.getPosition().trim());
        }
        if(jobPostRequest.getRequirements() != null){
            jobpost.setRequirements(jobPostRequest.getRequirements().trim());
        }
        if(jobPostRequest.getTypeOfEmployment() != null){
            jobpost.setTypeOfEmployment(jobPostRequest.getTypeOfEmployment().trim());
        }
        if(jobPostRequest.getContactInformation() != null){
            jobpost.setContactInformation(jobPostRequest.getContactInformation().trim());
        }
        Optional<Integer> salaryRange = Optional.of(jobPostRequest.getSalaryRange());
        salaryRange.ifPresent(jobpost::setSalaryRange);

        if(jobPostRequest.getTagId() != null){
            List<Tag> tags = tagRepository.findAllById(jobPostRequest.getTagId());
            jobpost.setTags(tags);
        }
        if(jobPostRequest.getStartDate() != null){
            jobpost.setStartDate(jobPostRequest.getStartDate());
        }
        jobpost.setUpdatedAt(LocalDateTime.now());

        Jobpost updatedJobPost = jobpostRepository.save(jobpost);


        return ResponseTemplate.<JobPostResponse>builder()
            .code(HttpStatus.OK.value())
            .message("Edit Job Post successfully")
            .data(jobPostMapper.toJobPostToResponse(updatedJobPost))
            .build();
    }

    @Override
    public ResponseTemplate<JobPostResponse> deleteJobPost(Long id){
        Jobpost jobpost = jobpostRepository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("Job Post not found with id: " + id));

        jobpostRepository.delete(jobpost);
        return ResponseTemplate.<JobPostResponse>builder()
            .code(HttpStatus.OK.value())
            .message("Delete Job Post Successfully")
            .build();
    }
}
