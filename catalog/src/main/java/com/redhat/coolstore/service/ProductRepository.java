package com.redhat.coolstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.redhat.coolstore.model.Product;

@Repository
public class ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(
            rs.getString("itemId"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("price"));

    public List<Product> readAll() {
        return this.jdbcTemplate.query("SELECT * FROM catalog", this.rowMapper);
    }

    public Product findById(String id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM catalog WHERE itemId = '" + id + "'", this.rowMapper);
    }

}
