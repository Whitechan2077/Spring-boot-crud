package org.example.springbootcrud.controller;

import org.example.springbootcrud.dtos.ProductDTO;
import org.example.springbootcrud.response.ResponseObject;
import org.example.springbootcrud.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    @Autowired
    ProductService service;
    @GetMapping("/getAllProduct")
    public ResponseEntity<ResponseObject> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("Complete","Get all product complete",service.getAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Long id){
        try {
            ProductDTO productDTO = service.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok","Get product complete",productDTO)
            );
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Fail","Can't not found data with id: "+id,"")
            );
        }
    }
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody ProductDTO dto){
        try {
            ProductDTO insertDto = service.insert(dto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Complete","Insert Complete",insertDto)
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Fail","Insert fail "+e.getMessage(),"")
            );
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductDTO dto){
        service.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Complete","Update complete",dto)
        );
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Complete","Delete complete","")
        );
    }
}
