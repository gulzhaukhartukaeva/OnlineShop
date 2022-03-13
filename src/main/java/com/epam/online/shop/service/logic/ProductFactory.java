package com.epam.online.shop.service.logic;

import com.epam.online.shop.service.dao.EnumDAO;
import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.ProducerDAOImpl;
import com.epam.online.shop.service.dao.implementation.ProductDAOImpl;
import com.epam.online.shop.service.entity.Category;
import com.epam.online.shop.service.entity.Producer;
import com.epam.online.shop.service.entity.Product;
import com.epam.online.shop.service.entity.Size;
import com.epam.online.shop.service.dao.implementation.CategoryDAOImpl;
import com.epam.online.shop.service.dao.implementation.SizeDAOImpl;
import com.epam.online.shop.service.dao.interfaces.CategoryDAO;
import com.epam.online.shop.service.dao.interfaces.ProducerDAO;
import com.epam.online.shop.service.dao.interfaces.ProductDAO;
import com.epam.online.shop.service.dao.interfaces.SizeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductFactory {
    private static ProductFactory instance = new ProductFactory();
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProducerDAO producerDAO = (ProducerDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCER_DAO);
    private final ProductDAO productDAO = (ProductDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCT_DAO);
    private final SizeDAO sizeDAO = (SizeDAOImpl) factoryDAO.getDAO(EnumDAO.SIZE_DAO);
    private final CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(EnumDAO.CATEGORY_DAO);

    private ProductFactory() {
    }

    public List<Product> fillProductsForCreating(HttpServletRequest request, Integer languageId) throws SQLException, IOException, ServletException {
        List<Product> products = fillProductsForUpdating(request, languageId);
        Long generatedId = productDAO.takeLastId() + 1;
        for (Product product : products) {
            product.setId(generatedId);
        }
        return products;
    }

    public List<Product> fillProductsForUpdating(HttpServletRequest request, Integer languageId) throws SQLException, IOException, ServletException {
        List<Product> products = new ArrayList<>();
        String[] productNames = request.getParameterValues("name");
        String[] productDescriptions = request.getParameterValues("description");
        String productPrice = request.getParameter("price");
        String productQuantity = request.getParameter("quantity");
        String productDiscount = request.getParameter("discount");
        String productProducer = request.getParameter("producer");
        String productCategory = request.getParameter("category");
        String productSize = request.getParameter("size");

        String[] languageIds = request.getParameterValues("languageId");
        Collection<Part> parts = request.getParts();

        for (int i = 0; i < productNames.length; i++) {
            Product product = new Product();
            if (request.getParameterValues("productId") != null) {
                product.setId(Long.parseLong(request.getParameterValues("productId")[i]));
            }
            product.setLanguageId(Integer.parseInt(languageIds[i]));
            product.setName(productNames[i]);
            product.setDescription(productDescriptions[i]);
            product.setPrice(Double.parseDouble(productPrice));
            product.setQuantity(Integer.parseInt(productQuantity));
            product.setDiscount(Integer.parseInt(productDiscount));
            Producer producer = producerDAO.getById(Long.parseLong(productProducer), languageId);
            product.setProducer(producer);
            Category category = categoryDAO.getById(Long.parseLong(productCategory), languageId);
            product.setCategory(category);
            Size size = sizeDAO.getById(Long.parseLong(productSize), languageId);
            product.setSize(size);

            for (Part part : parts) {
                if (part.getName().equalsIgnoreCase("image")) {
                    try (InputStream fileInputStream = part.getInputStream()) {
                        product.setUploadingImage(fileInputStream);
                    }
                }
            }

            products.add(product);
        }
        return products;
    }

    public static ProductFactory getInstance() {
        if (instance == null) {
            instance = new ProductFactory();
        }
        return instance;
    }
}

