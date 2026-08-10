package com.netoptc.productorder.repositories;

import com.netoptc.productorder.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p FROM Product p JOIN FETCH p.categories")
    List<Product> findAllWithCategories();

    @Query("""
        SELECT p FROM Product p
        WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', :name, '%'))
    """)
    Page<Product> findAllPaginated(Pageable pageable, @Param("name") String name);

    @Query("""
        SELECT p FROM Product p LEFT JOIN FETCH p.categories
        WHERE p.id IN :ids
    """)
    List<Product> findAllWithCategoriesByIds(List<Long> ids);

}
