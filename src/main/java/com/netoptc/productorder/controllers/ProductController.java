package com.netoptc.productorder.controllers;

import com.netoptc.productorder.dtos.ProductDto;
import com.netoptc.productorder.dtos.ProductMinDto;
import com.netoptc.productorder.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;

import java.net.URI;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<Page<ProductMinDto>> findAll(
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @RequestParam(defaultValue = "") String name
    ) {
        Page<ProductMinDto> result =  productService.findAll(pageable, name);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<ProductDto>  insert(@Valid @RequestBody ProductDto dto
    ) {
        ProductDto result =  productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(
            @PathVariable Long id
    ) {
        ProductDto result =  productService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductDto dto
    ) {
        ProductDto result =  productService.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
