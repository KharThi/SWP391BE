/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.EventDTO;
import dto.NewsDTO;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class NewsDAO {

    public List<NewsDTO> getListNews(int page) {
        List<NewsDTO> listNews = new ArrayList<>();
        List<NewsDTO> result = new ArrayList<>();
        int id = 0;
        String name = null;
        boolean status = false;
        String createTime = null;
        String content = null;
        String author = null;
        int view = 0;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "SELECT * FROM SWP391.News;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                if (rs.getInt("status") == 0) {
                    status = false;
                } else {
                    status = true;
                }
                createTime = rs.getDate("create_time").toString();
                content = rs.getString("content");
                author = rs.getString("author");
                view = rs.getInt("view");
                NewsDTO dto = new NewsDTO(id, name, status, createTime, content, author, view);
                listNews.add(dto);
            }
            result = pagedResponse(listNews, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<NewsDTO> search(int page, String search) {
        List<NewsDTO> listNews = new ArrayList<>();
        List<NewsDTO> result = new ArrayList<>();
        int id = 0;
        String name = null;
        boolean status = false;
        String createTime = null;
        String content = null;
        String author = null;
        int view = 0;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "SELECT * FROM SWP391.News WHERE name LIKE ?;";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, "%" + search + "%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                if (rs.getInt("status") == 0) {
                    status = false;
                } else {
                    status = true;
                }
                createTime = rs.getDate("create_time").toString();
                content = rs.getString("content");
                author = rs.getString("author");
                view = rs.getInt("view");
                NewsDTO dto = new NewsDTO(id, name, status, createTime, content, author, view);
                listNews.add(dto);
            }
            result = pagedResponse(listNews, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean createtNews(NewsDTO news) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            if (news.getCreateTime() != null) {
                String sql = "INSERT INTO SWP391.News (`name`, `status`, `create_time`  , `content`, `author`, `view`) VALUES (?, ?,?, ?, ?,?);";
                PreparedStatement pr = con.prepareStatement(sql);
                int tmp = 0;
                if (news.isStatus()) {
                    tmp = 1;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = format.parse(news.getCreateTime());
                java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
                pr.setString(1, news.getName());
                pr.setInt(2, tmp);
                pr.setDate(3, sqlDate);
                pr.setString(4, news.getContent());
                pr.setString(5, news.getAuthor());
                pr.setInt(6, news.getView());
                check = pr.executeUpdate() > 0;
                return check;
            }
            String sql = "INSERT INTO SWP391.News (`name`, `status` , `content`, `author`, `view`) VALUES (?, ?,?, ?, ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            int tmp = 0;
            if (news.isStatus()) {
                tmp = 1;
            }
            pr.setString(1, news.getName());
            pr.setInt(2, tmp);
            pr.setString(3, news.getContent());
            pr.setString(4, news.getAuthor());
            pr.setInt(5, news.getView());

            check = pr.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateNews(NewsDTO news) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            if (news.getCreateTime() != null) {
                String sql = "UPDATE SWP391.News SET `name` = ?, `status` = ?,"
                        + " `create_time` = ?, `content` = ?, `author` = ?, `view` = ? WHERE (`id` LIKE ?);";
                PreparedStatement pr = con.prepareStatement(sql);
                int tmp = 0;
                if (news.isStatus()) {
                    tmp = 1;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = format.parse(news.getCreateTime());
                java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
                pr.setString(1, news.getName());
                pr.setInt(2, tmp);
                pr.setDate(3, sqlDate);
                pr.setString(4, news.getContent());
                pr.setString(5, news.getAuthor());
                pr.setInt(6, news.getView());
                pr.setInt(7, news.getId());
                check = pr.executeUpdate() > 0;
                return check;
            }
            String sql = "UPDATE SWP391.News SET `name` = ?, `status` = ?,"
                    + "`content` = ?, `author` = ?, `view` = ? WHERE (`id` LIKE ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            int tmp = 0;
            if (news.isStatus()) {
                tmp = 1;
            }
            pr.setString(1, news.getName());
            pr.setInt(2, tmp);
            pr.setString(3, news.getContent());
            pr.setString(4, news.getAuthor());
            pr.setInt(5, news.getView());
            pr.setInt(6, news.getId());
            check = pr.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean deleteNews(int id) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "DELETE FROM SWP391.News WHERE (`id` LIKE ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, id);
            check = pr.executeUpdate() > 0;
            return check;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public List<NewsDTO> pagedResponse(List<NewsDTO> allItems, int page) {
        int totalItems = allItems.size();
        int fromIndex = (page - 1) * 10;
        int toIndex = fromIndex + 10;
        if (fromIndex <= totalItems) {
            if (toIndex > totalItems) {
                toIndex = totalItems;
            }
            return allItems.subList(fromIndex, toIndex);
        } else {
            return Collections.emptyList();
        }
    }
}
