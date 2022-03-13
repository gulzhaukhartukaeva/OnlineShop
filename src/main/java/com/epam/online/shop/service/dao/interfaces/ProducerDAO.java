package com.epam.online.shop.service.dao.interfaces;

import com.epam.online.shop.service.entity.Producer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProducerDAO extends CommonDAO<Producer> {
    Long takeLastId() throws SQLException;
    List<Producer> takeAllLocalizedProducerByID(Long producerId) throws SQLException, IOException;
}