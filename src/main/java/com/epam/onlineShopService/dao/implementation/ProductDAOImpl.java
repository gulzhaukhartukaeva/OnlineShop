package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.interfaces.*;
import com.epam.onlineShopService.database.connection.ConnectionPool;
import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.entity.Product;
import com.epam.onlineShopService.entity.Size;
import com.epam.onlineShopService.util.FileManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private ProducerDAO producerDAO = (ProducerDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCER_DAO);
    private CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(EnumDAO.CATEGORY_DAO);
    private SizeDAO sizeDAO = (SizeDAOImpl) factoryDAO.getDAO(EnumDAO.SIZE_DAO);

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM PRODUCT WHERE LANGUAGE_ID = ?";
    private static final String ADD_PRODUCT = "INSERT INTO PRODUCT (NAME, DESCRIPTION, PRICE, QUANTITY, DISCOUNT, PRODUCERID, CATEGORYID, SIZEID, " +
            "LOGO, LANGUAGE_ID, ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_LAST_ID_FROM_PRODUCT = "SELECT MAX(ID) FROM PRODUCT";
    private static final String UPDATE_PRODUCT = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, PRICE =?, QUANTITY =?," +
            "DISCOUNT =?, PRODUCERID =?, CATEGORYID = ?  WHERE ID = ? AND LANGUAGE_ID = ?";
    private static final String SELECT_ALL_LOCALIZED_PRODUCTS_BY_ID = "SELECT * FROM PRODUCT WHERE ID = ?";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM PRODUCT WHERE ID = ? AND LANGUAGE_ID = ?";
    private static final String  SELECT_BY_CATEGORY_ID= "SELECT * FROM PRODUCT WHERE CATEGORYID = ? AND LANGUAGE_ID = ?";

    @Override
    public void create(Product product) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getQuantity());
            preparedStatement.setInt(5, product.getDiscount());
            preparedStatement.setLong(6, product.getProducer().getId());
            preparedStatement.setLong(7, product.getCategory().getId());
            preparedStatement.setLong(8, product.getSize().getId());
            preparedStatement.setBlob(9, product.getUploadingLogo());
            preparedStatement.setLong(10, product.getLanguageId());
            preparedStatement.setLong(11, product.getId());

            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Product> getAll(Integer languageId) throws SQLException, IOException {
        List<Product> products = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            preparedStatement.setInt(1, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                setParametersToProduct(product, resultSet, languageId);
                products.add(product);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return products;
    }

    private void setParametersToProduct(Product product, ResultSet resultSet, Integer languageId) throws SQLException, IOException{
        FileManager fileManager = new FileManager();

        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getDouble("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setDiscount(resultSet.getInt("discount"));
        product.setProducer(producerDAO.getByID(resultSet.getLong("producerId"), languageId));
        product.setCategory(categoryDAO.getByID(resultSet.getLong("categoryId"), languageId));
        
        Size size = new Size();
        product.setSize(size);
        product.getSize().setId(resultSet.getLong("sizeId"));

        product.setLogo(fileManager.takeFileFromDataBase(resultSet.getBlob("LOGO")));
        product.setLanguageId(resultSet.getInt("LANGUAGE_ID"));
    }

    @Override
    public Product getByID(Long id, Integer languageId) throws SQLException, IOException {
        Product category = new Product() ;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToProduct(category, resultSet, languageId);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return category;    }

    @Override
    public void update(Long id, Integer languageId, Product product) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getQuantity());
            preparedStatement.setInt(5, product.getDiscount());
            preparedStatement.setLong(6, product.getProducer().getId());
            preparedStatement.setLong(7, product.getCategory().getId());
            //preparedStatement.setLong(8, product.getSize().getId());
            preparedStatement.setLong(9, id);
            preparedStatement.setLong(10, languageId);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Product> takeAllLocalizedProductByID(Long productId, Integer languageId) throws SQLException, IOException {
        List<Product> products = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOCALIZED_PRODUCTS_BY_ID)) {
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToProductList(products, resultSet, languageId);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return products;
    }

    @Override
    public Long takeLastID() throws SQLException {
        long lastID = 0;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST_ID_FROM_PRODUCT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                lastID = resultSet.getLong("MAX(ID)");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return lastID;
    }

    private void setParametersToProductList(List<Product> products, ResultSet resultSet, Integer languageId) throws SQLException, IOException {
        Product product = new Product();
        setParametersToProduct(product, resultSet, languageId);
        products.add(product);
    }

    public List<Product> findByCategoryId(Integer languageId, Long categoryId) throws SQLException, IOException {
        List<Product> products = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_CATEGORY_ID)) {
            preparedStatement.setLong(1, categoryId);
            preparedStatement.setLong(2, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToProductList(products, resultSet, languageId);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return products;
    }
}