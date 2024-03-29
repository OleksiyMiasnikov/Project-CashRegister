package com.myProject.dao.mysql;

import com.myProject.dao.UserDao;
import com.myProject.entitie.Role;
import com.myProject.entitie.User;
import com.myProject.service.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.myProject.util.Constants.*;

public class UserDaoImpl implements UserDao {

    public User findByName(Connection con, String name) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = con.prepareStatement(SELECT_USER);
            pstmt.setString(1, name);
            pstmt.executeQuery();
            resultSet = pstmt.getResultSet();
            if (resultSet.next()) {
                return buildUser(resultSet);
            }
            return null;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    @Override
    public List<User> findAll(Connection con, int from, int size) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(SELECT_ALL_USERS_WITH_LIMIT);
            pstmt.setInt(1, from);
            pstmt.setInt(2, size);
            pstmt.executeQuery();
            resultSet = pstmt.getResultSet();
            while (resultSet.next()) {
                userList.add(buildUser(resultSet));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        Collections.sort(userList);
        return userList;
    }

    @Override
    public User read(Connection con, Long id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = con.prepareStatement(READ_USER_BY_ID);
            pstmt.setLong(1, id);
            pstmt.executeQuery();
            resultSet = pstmt.getResultSet();
            if (resultSet.next()) {
                return buildUser(resultSet);
            }
            return null;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    @Override
    public User create(Connection con, User newUser) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newUser.getLogin());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setLong(4, newUser.getRole().getId());
            pstmt.executeUpdate();
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                newUser.setId(resultSet.getLong(1));
                return newUser;
            }
            throw new DaoException("Duplicate entry");
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public boolean delete(Connection con, Long id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_USER)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        }
    }

    @Override
    public void update(Connection con, User user) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_USER)) {
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setLong(4, user.getRole().getId());
            pstmt.setLong(5, user.getId());
            pstmt.executeUpdate();
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong(1))
                .login(resultSet.getString(2))
                .password(resultSet.getString(3))
                .email(resultSet.getString(4))
                .role(Role.builder()
                        .id(resultSet.getInt(5))
                        .name(resultSet.getString(6))
                        .build())
                .build();
    }

    @Override
    public int findRowsTotal(Connection con) throws SQLException {
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            stmt = con.createStatement();
            stmt.executeQuery(COUNT_ROWS_IN_USER);
            resultSet = stmt.getResultSet();
            resultSet.next();
            return resultSet.getInt("rows_total");
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
