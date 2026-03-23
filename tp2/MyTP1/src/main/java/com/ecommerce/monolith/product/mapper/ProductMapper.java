package com.ecommerce.monolith.product.mapper;

import com.ecommerce.monolith.product.dto.ProductDTO;
import com.ecommerce.monolith.product.dto.ProductRequest;
import com.ecommerce.monolith.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // Convertit une entité Product en DTO
    ProductDTO toDTO(Product product);
    // Convertit une liste d'entités Product en liste de DTO
    List<ProductDTO> toDTOList(List<Product> products);
    // Convertit une requête CreateProductRequest en entité Product
    Product toEntity(ProductRequest request);
    // Met à jour une entité Product existante à partir d'une requête
    @Mapping(target = "id", ignore = true)
    void updateEntity(ProductRequest request, @MappingTarget Product
            product);
}
