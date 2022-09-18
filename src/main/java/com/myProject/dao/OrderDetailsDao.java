package com.myProject.dao;

import com.myProject.dao.entitie.OrderDetails;
import com.myProject.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailsDao extends Dao<Long, OrderDetails> {
    List<OrderDetails> detailsByOrderId(Connection con, long id) throws DaoException;
}