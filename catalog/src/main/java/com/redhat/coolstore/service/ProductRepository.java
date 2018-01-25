package com.redhat.coolstore.service;

import java.util.List;

import com.redhat.coolstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(
            rs.getString("itemId"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("price"));


    public List<Product> readAll() {
        return jdbcTemplate.query("SELECT * FROM catalog", rowMapper);
    }

    public Product findById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM catalog WHERE itemId = '" + id + "'", rowMapper);
    }

}