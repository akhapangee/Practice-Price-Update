/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhilesh.servlet;

import com.akhilesh.dao.ProductDAO;
import com.akhilesh.dao.impl.ProductDAOImpl;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akhilesh
 */
@WebServlet(displayName = "default", urlPatterns = {"/", "/home"})
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAOImpl();
        String value = request.getParameter("category_choice");
        System.out.println(value);
        System.out.println("hello");
        try {
            request.setAttribute("products", productDAO.getAllProducts());
            request.setAttribute("categories", productDAO.getAllCategories());
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryValue = request.getParameter("category_choice");
        if (!categoryValue.equals("0")) {
            String updateOption = request.getParameter("updateOption");
            String updateValue = request.getParameter("updateValue");
            String checkBoxValue = request.getParameter("checkBoxValue");

            ProductDAO productDAO = new ProductDAOImpl();
            try {
                productDAO.updateProductPrice(Integer.parseInt(categoryValue), updateOption, Double.parseDouble(updateValue), checkBoxValue);
                doGet(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

}
