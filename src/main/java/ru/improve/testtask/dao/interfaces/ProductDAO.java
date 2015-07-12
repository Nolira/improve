package ru.improve.testtask.dao.interfaces;

import ru.improve.testtask.entities.Product;

import java.util.List;

/**
 * Created by Nolira on 10.07.2015.
 */
public interface ProductDAO {
    List<Product> getProductsByBasicParameters(String catName, String prodName, String lowerPrice, String upperPrice);
}
