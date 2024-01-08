package org.example.springbootcrud.services.impl;

import org.example.springbootcrud.dtos.ProductDTO;
import org.example.springbootcrud.services.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    @Override
    public List<ProductDTO> getAll() {
        return null;
    }

    @Override
    public ProductDTO getById() {
        return null;
    }

    @Override
    public void insert(ProductDTO dto) {

    }

    @Override
    public void update(ProductDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll(List<Long> ids) {

    }
}
