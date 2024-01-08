package org.example.springbootcrud.services.impl;

import org.example.springbootcrud.dtos.ProductDTO;
import org.example.springbootcrud.entities.Product;
import org.example.springbootcrud.repositories.IProductRepository;
import org.example.springbootcrud.services.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ProductDTO> getAll() {
        return repository.findAll().stream().map(product -> modelMapper.map(product,ProductDTO.class)).toList();
    }

    @Override
    public ProductDTO getById(Long id) {
        return modelMapper
                .map(repository
                        .findById(id)
                        .orElseThrow(()
                                -> new NoSuchElementException("Not Found")),ProductDTO.class);
    }

    @Override
    public ProductDTO insert(ProductDTO dto) {
      if (dto.getBrand().isEmpty()||dto.getProductName().isEmpty()){
          throw new RuntimeException("Data invalid");
      }
      else {
          return modelMapper.map(repository.save(modelMapper.map(dto, Product.class)), ProductDTO.class);
      }
    }

    @Override
    public void update(ProductDTO dto) {
        repository.save(modelMapper.map(dto, Product.class));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Long> ids) {
        repository.deleteAllById(ids);
    }
}
