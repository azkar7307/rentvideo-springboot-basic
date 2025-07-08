package com.crio.rentvideo_service.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.rentvideo_service.exception.ResourceNotFoundException;
import com.crio.rentvideo_service.model.Video;
import com.crio.rentvideo_service.repository.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getAllVideos() {
        return videoRepository.findByAvailableTrue();
    }

    public Video createVideo(Video video) {
        video.setAvailable(true);  // Ensure new videos are available
        return videoRepository.save(video);
    }

    public Video updateVideo(Long id, Video videoDetails) {
        Video video = videoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Video not found with id: " + id));
        
        video.setTitle(videoDetails.getTitle());
        video.setDirector(videoDetails.getDirector());
        video.setGenre(videoDetails.getGenre());
        return videoRepository.save(video);
    }

    public void deleteVideo(Long id) {
        if (!videoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Video not found with id: " + id);
        }
        videoRepository.deleteById(id);
    }
}