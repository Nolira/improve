package ru.improve.testtask.servlets;

import ru.improve.testtask.dao.implementions.ProductDAOImpl;
import ru.improve.testtask.dao.interfaces.ProductDAO;
import ru.improve.testtask.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nolira on 06.07.2015.
 */
@WebServlet("/ProductFilter/*")
public class ProductServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Все параметры я кидаю через GET, потому что весь запрос все равно влезет в URL,
        // даже если будут использованы имена в 255 символов, а видеть конкретные параметры
        // в URL приятнее, плюс можно будет делиться ссылкой

        // Вывод переданных параметров
        System.out.println("Come in");
        System.out.println(request.getParameter("catName"));
        System.out.println(request.getParameter("prodName"));
        System.out.println(request.getParameter("lowerPrice"));
        System.out.println(request.getParameter("upperPrice"));

        // Создаем DAO
        ProductDAO productDAO = new ProductDAOImpl();

        // Передаем параметры в DAO, формируем список результатов
        List<Product> results = productDAO.getProductsByBasicParameters(
                request.getParameter("catName"),
                request.getParameter("prodName"),
                request.getParameter("lowerPrice"),
                request.getParameter("upperPrice"));

        request.setAttribute("results", results);
        request.getRequestDispatcher("index.jsp").forward(request, response);

        System.out.println("Get out out");

    }
}
