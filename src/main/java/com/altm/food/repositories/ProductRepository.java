package com.altm.food.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altm.food.entities.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    
    List<Product> findByName(String name);
    
    List<Product> findByCategory(String name);
    
    @Query("SELECT DISTINCT a.category FROM Product a")
    List<String> findDistinctCategory();
    
    List<Product> findByRatingAndPrice(int rate, double prc);
    
    List<Product> findByRating(int rat);
    
    List<Product> findByPrice(double prc);
    
    Product findById(long id);
    
}