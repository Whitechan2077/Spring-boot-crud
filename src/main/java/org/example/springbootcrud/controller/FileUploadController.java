package org.example.springbootcrud.controller;

import org.example.springbootcrud.response.ResponseObject;
import org.example.springbootcrud.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/file-upload")
public class FileUploadController {
    @Autowired
    IStorageService service;
    @PostMapping("/upload-image")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile multipartFile){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Store complete",service.storeFile(multipartFile))
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Fail","Store fail","")
            );
        }
    }
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName){
        try {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(service.readFile(fileName));
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
}
