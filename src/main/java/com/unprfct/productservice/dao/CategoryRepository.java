package com.unprfct.productservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unprfct.productservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

}
