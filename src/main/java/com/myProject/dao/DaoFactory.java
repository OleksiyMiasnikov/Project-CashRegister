package com.myProject.dao;

import com.myProject.dao.mysql.DaoFactoryImpl;

public abstract class DaoFactory {
    private final static DaoFactory instance = new DaoFactoryImpl();
    public static DaoFactory getInstance() {
        return instance;
    }

    public abstract UserDao getUserDao();
    public abstract RoleDao getRoleDao();
    public abstract GoodsDao getGoodsDao();
}
