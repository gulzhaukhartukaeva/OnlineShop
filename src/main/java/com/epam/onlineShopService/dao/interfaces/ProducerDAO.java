package com.epam.onlineShopService.dao.interfaces;

import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.entity.Producer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProducerDAO extends CommonDAO<Producer> {
    Long takeLastID() throws SQLException;
    List<Producer> takeAllLocalizedProducerByID(Long producerId) throws SQLException, IOException;
}