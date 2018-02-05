/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhilesh.dao;

import com.akhilesh.entity.Category;
import com.akhilesh.entity.Product;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Akhilesh
 */
public interface ProductDAO {
    List<Product> getAllProducts() throws ClassNotFoundException,SQLException;
    List<Category> getAllCategories() throws ClassNotFoundException,SQLException;
    List<Product> getByCategoryID(int categoryId) throws ClassNotFoundException,SQLException;
    void updateProductPrice(int categoryValue, String updateOption,double updateValue, String checkBoxValue) throws  ClassNotFoundException, SQLException;
}
