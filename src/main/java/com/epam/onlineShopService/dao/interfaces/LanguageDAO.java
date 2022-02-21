package com.epam.onlineShopService.dao.interfaces;

import com.epam.onlineShopService.entity.Language;

import java.sql.SQLException;
import java.util.List;

public interface LanguageDAO extends CommonDAO<Language> {

    Integer getLanguageId(String language) throws SQLException;

    List<Language> getAll() throws SQLException;
}