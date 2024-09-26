package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;



// import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.PYQ;
import com.example.demo.repository.PYQRepository;

@RestController
@RequestMapping("/api/pyq")
public class pyqController {

    @Autowired
    private PYQRepository pyqRepository;

    // @GetMapping("/getFilePath")
    // public ResponseEntity<String> getFilePath(@RequestParam String semester, 
    //                                           @RequestParam String type, 
    //                                           @RequestParam String course) {
    //     Optional<PYQ> pyq = pyqRepository.findBySemesterAndTypeAndCourse(semester, type, course);
    //     return pyq.map(value -> ResponseEntity.ok(value.getFilePath()))
    //               .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found"));
    // }

    @GetMapping("/getFilePath")
public ResponseEntity<Map<String, String>> getFilePath(@RequestParam String semester, 
                                                       @RequestParam String type, 
                                                       @RequestParam String course) {
    Optional<PYQ> pyq = pyqRepository.findBySemesterAndTypeAndCourse(semester, type, course);
    if (pyq.isPresent()) {
        Map<String, String> response = new HashMap<>();
        response.put("filePath", pyq.get().getFilePath());
        return ResponseEntity.ok(response);
    } else {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "File not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}


    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/preview")
    public ResponseEntity<Resource> previewFile(@RequestParam String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            Resource resource = new FileSystemResource(file);

            // Determine the content type for the file
            String contentType = Files.probeContentType(file.toPath());
            if (contentType == null) {
                contentType = "application/octet-stream"; // Default if content type is unknown
            }

            // Respond with inline content disposition to allow preview
            return ResponseEntity.ok()
                    .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
