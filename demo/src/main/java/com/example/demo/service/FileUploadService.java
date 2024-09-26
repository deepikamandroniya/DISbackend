package com.example.demo.service;

import com.example.demo.model.PreviousPaper;
import com.example.demo.repository.PreviousPaperRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final PreviousPaperRepository paperRepository;

    public FileUploadService(PreviousPaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public void saveFileAndData(MultipartFile file, String semester, String type, String course,int year) throws IOException {
        // Save file to directory
        String filePath = saveFile(file);

        // Save paper details to the database
        PreviousPaper paper = new PreviousPaper();
        paper.setSemester(semester);
        paper.setType(type);
        paper.setCourse(course);
        paper.setYear(year);
        paper.setFilePath(filePath);

        paperRepository.save(paper);
    }

    private String saveFile(MultipartFile file) throws IOException {
        Path path = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(path);  // Ensure directories exist
        Path targetLocation = path.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), targetLocation);
        return targetLocation.toString();
    }
}