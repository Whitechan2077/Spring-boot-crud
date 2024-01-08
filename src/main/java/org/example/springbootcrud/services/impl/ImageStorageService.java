package org.example.springbootcrud.services.impl;

import org.apache.commons.io.FilenameUtils;
import org.example.springbootcrud.services.IStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;
@Service
public class ImageStorageService implements IStorageService {
    private final Path storageFolder = Paths.get("uploads");

    public ImageStorageService() {
        try {
            Files.createDirectories(storageFolder);
        }catch (IOException e){
            throw new RuntimeException("Failed to create");
        }
    }
    private boolean isImageFile(MultipartFile file){
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList("png","jpg","jpeg","bmp").contains(fileExtension.trim().toLowerCase());
    }
    @Override
    public String storeFile(MultipartFile file) {
        try {
            if (file.isEmpty()) throw new RuntimeException("Failed to store empty file");
            if (!isImageFile(file)) throw new RuntimeException("You can only upload image file");
            float fileMb = file.getSize()/1_000_000;

            if (fileMb>5.0f)
                throw new RuntimeException("File must be less than 5mb");

            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-","");
            generatedFileName = generatedFileName +"."+fileExtension;

            Path destinationFilePath = this.storageFolder.resolve(Paths.get(generatedFileName)).normalize().toAbsolutePath();

            if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath()))
                throw new RuntimeException("Can not store file outside current directory");

            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream,destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        }catch (IOException e){
            throw new RuntimeException("Can not store file ",e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public byte[] readFile(String fileName) {
        try {
            Path file = storageFolder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return StreamUtils.copyToByteArray(resource.getInputStream());
            }
            else throw new RuntimeException("Can not read file "+fileName);
        }catch (IOException e){
             throw new RuntimeException("Can not read file "+e);
        }
    }

    @Override
    public void deleteAllFile() {

    }
}
