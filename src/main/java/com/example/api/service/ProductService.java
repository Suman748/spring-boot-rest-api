package com.example.api.service;

import com.example.api.dto.ProductRequest;
import com.example.api.dto.ProductResponse;
import com.example.api.exception.ResourceNotFoundException;
import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<ProductResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public ProductResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public ProductResponse create(ProductRequest req) {
        Product p = new Product();
        apply(p, req);
        return toResponse(repo.save(p));
    }

    public ProductResponse update(Long id, ProductRequest req) {
        Product p = getOrThrow(id);
        apply(p, req);
        return toResponse(repo.save(p));
    }

    public void delete(Long id) {
        repo.delete(getOrThrow(id));
    }

    private Product getOrThrow(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    private void apply(Product p, ProductRequest r) {
        p.setName(r.name());
        p.setDescription(r.description());
        p.setPrice(r.price());
        p.setQuantity(r.quantity());
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getQuantity());
    }
}
