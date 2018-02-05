/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhilesh.dao.impl;

import com.akhilesh.dao.ProductDAO;
import com.akhilesh.entity.Category;
import com.akhilesh.entity.Product;
import com.akhilesh.utils.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akhilesh
 */
public class ProductDAOImpl implements ProductDAO {

    private DbConnection db = new DbConnection();

    @Override
    public List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "select t2.id,t2.name, t2.description,t1.name as category_name, t2.price,t2.quantity,t2.status from categories t1\n"
                + "join products t2 on t1.id = t2.category_id;";
        db.connect();
        db.initStatement(sql);
        ResultSet rs = db.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String categoryName = rs.getString("category_name");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            boolean status = rs.getBoolean("status");

            product.setProductId(id);
            product.setProductName(name);
            product.setDescription(description);
            product.setCategoryName(categoryName);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setStatus(status);

            products.add(product);

        }
        db.close();

        return products;
    }

    @Override
    public List<Category> getAllCategories() throws ClassNotFoundException, SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories;";
        db.connect();
        db.initStatement(sql);
        ResultSet rs = db.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            Category category = new Category();
            category.setId(id);
            category.setCategoryName(name);
            categories.add(category);
        }
        db.close();
        return categories;
    }

    @Override
    public List<Product> getByCategoryID(int categoryId) throws ClassNotFoundException, SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "t2.name, t2.description,t1.name as category_name, t2.price,t2.quantity,t2.status from categories t1\n"
                + "join products t2 on t1.id = t2.category_id where t1.id =" + categoryId + ";";
        db.connect();
        db.initStatement(sql);
        ResultSet rs = db.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String categoryName = rs.getString("category_name");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            boolean status = rs.getBoolean("status");

            product.setProductId(id);
            product.setProductName(name);
            product.setDescription(description);
            product.setCategoryName(categoryName);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setStatus(status);

            products.add(product);
        }
        db.close();
        return products;
    }

    @Override
    public void updateProductPrice(int categoryValue, String updateOption, double updateValue, String checkBoxValue) throws ClassNotFoundException, SQLException {
        String sql = "";
        if (updateOption.equalsIgnoreCase("increment")) {
            if (checkBoxValue.equals("amount")) {
                sql = "UPDATE products SET price = (price+?) WHERE category_id = ?;";
            } else if (checkBoxValue.equals("percent")) {
                sql = "UPDATE products set price = (price+price*?/100) where category_id=?;";
            }
        } else if (updateOption.equalsIgnoreCase("decrement")) {
            if (checkBoxValue.equals("amount")) {
                sql = "UPDATE products SET price = (price-?) WHERE category_id = ?;";
            } else if (checkBoxValue.equals("percent")) {
                sql = "UPDATE products set price = (price-price*?/100) where category_id=?;";
            }
        }
        if (!sql.isEmpty()) {
            db.connect();
            PreparedStatement stmt = db.initStatement(sql);
            stmt.setDouble(1, updateValue);
            stmt.setInt(2, categoryValue);
            int result = db.executeUpdate();
            System.out.println("result: " + result);
            db.close();
        }

    }

}
