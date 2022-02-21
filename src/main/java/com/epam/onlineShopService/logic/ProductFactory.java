package com.epam.onlineShopService.logic;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.CategoryDAOImpl;
import com.epam.onlineShopService.dao.implementation.ProducerDAOImpl;
import com.epam.onlineShopService.dao.implementation.ProductDAOImpl;
import com.epam.onlineShopService.dao.implementation.SizeDAOImpl;
import com.epam.onlineShopService.dao.interfaces.CategoryDAO;
import com.epam.onlineShopService.dao.interfaces.ProducerDAO;
import com.epam.onlineShopService.dao.interfaces.ProductDAO;
import com.epam.onlineShopService.dao.interfaces.SizeDAO;
import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.entity.Producer;
import com.epam.onlineShopService.entity.Product;
import com.epam.onlineShopService.entity.Size;

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
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private ProducerDAO producerDAO = (ProducerDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCER_DAO);
    private ProductDAO productDAO = (ProductDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCT_DAO);
    private SizeDAO sizeDAO = (SizeDAOImpl) factoryDAO.getDAO(EnumDAO.SIZE_DAO);
    private CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(EnumDAO.CATEGORY_DAO);

    private ProductFactory() {
    }

    public List<Product> fillProductsForCreating(HttpServletRequest request, Integer languageId) throws SQLException, IOException, ServletException {
        List<Product> products = fillProductsForUpdating(request, languageId);
        Long generatedID = productDAO.takeLastID() + 1;
        for (Product product : products) {
            product.setId(generatedID);
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
            Producer producer = producerDAO.getByID(Long.parseLong(productProducer), languageId);
            product.setProducer(producer);
            Category category = categoryDAO.getByID(Long.parseLong(productCategory), languageId);
            product.setCategory(category);
            Size size = sizeDAO.getByID(Long.parseLong(productSize), languageId);
            product.setSize(size);

            for (Part part : parts) {
                if (part.getName().equalsIgnoreCase("image")) {
                    try (InputStream fileInputStream = part.getInputStream()) {
                        product.setUploadingLogo(fileInputStream);
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

