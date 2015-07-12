package ru.improve.testtask.dao.implementions;

import ru.improve.testtask.dao.interfaces.ProductDAO;
import ru.improve.testtask.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nolira on 10.07.2015.
 */
public class ProductDAOImpl implements ProductDAO {

    private static EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory("SomePU");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<Product> getProductsByBasicParameters(String catName, String prodName, String lowerPriceString, String upperPriceString) {

        String queryString = "";
        List parameters = new ArrayList();
        int parametersSize = 0;

        if (catName != null && !catName.isEmpty()) {
            queryString += "AND p.category=(SELECT c FROM Category c WHERE LOWER(c.name) LIKE LOWER(?" + parametersSize + ")) ";
            parameters.add(catName + "%");
            parametersSize++;
        }

        if (prodName != null && !prodName.isEmpty()) {
            queryString += "AND LOWER(p.name) LIKE LOWER(?" + parametersSize + ") ";
            parameters.add(prodName + "%");
            parametersSize++;
        }

        if (lowerPriceString != null && !lowerPriceString.isEmpty()) {
            try {
                BigDecimal lowerPriceNumber = new BigDecimal(lowerPriceString);
                queryString += "AND p.price >= ?" + parametersSize + " ";
                parameters.add(lowerPriceNumber);
                parametersSize++;
            } catch (Exception ex) {
                System.out.println("Что-то с lowerPrice");
                // Если параметр задан неверно, то проверки не будет
            }
        }

        if (upperPriceString != null && !upperPriceString.isEmpty()) {
            try {
                BigDecimal upperPriceNumber = new BigDecimal(upperPriceString);
                queryString += "AND p.price <= ?" + parametersSize + " ";
                parameters.add(upperPriceNumber);
                parametersSize++;
            } catch (Exception ex) {
                System.out.println("Что-то с upperPrice");
                // Если параметр задан неверно, то проверки не будет
            }
        }

        if (parametersSize > 0) {
            // Формируем строку запроса
            queryString = queryString.substring(3); // отрезаем первый AND
            queryString = "SELECT p FROM Product p WHERE" + queryString;

            // Создаем запрос и выполняем его
            EntityManager entityManager = factory.createEntityManager();
            try {
                TypedQuery<Product> query = entityManager.createQuery(queryString, Product.class);

                // Проставляем параметры в запрос
                for (int i = 0; i < parametersSize; i++) {
                    query.setParameter(i, parameters.get(i));
                }

                // Формируем список результатов
                return query.getResultList();

            } catch (Exception ex) {
                System.out.println("Все плохо");
            } finally {
                entityManager.close();
            }
        }
        return null;
    }
}
