/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.EventDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class EventsDAO {

    public List<EventDTO> getListEvent() {
        List<EventDTO> listEvent = new ArrayList<>();
        int id = 0;//
        String name;//
        String startDate;
        String endDate;
        boolean status;//
        String description;
        String owner;
        String type;
        String createDate;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "SELECT * FROM SWP391.Events;";
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
                startDate = rs.getString("startDate");
                endDate = rs.getString("endDate");
                description = rs.getString("description");
                owner = rs.getString("owner");
                type = rs.getString("type");
                createDate = rs.getString("createDate");
                EventDTO dto = new EventDTO(id, name, startDate, endDate, status, description, owner, type, createDate);
                listEvent.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listEvent;
    }

    public boolean createtEvent(EventDTO events) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            if (events.getEndDate() == null || events.getStartDate() == null) {
                return check;
            }
            String sql = "INSERT INTO SWP391.Events (`name`, `startDate`, `endDate`, `status`, `description`, `owner`, `type`, `createDate`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            int tmp = 0;
            if (events.isStatus()) {
                tmp = 1;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed1 = format.parse(events.getEndDate());
            java.sql.Date sqlDate1 = new java.sql.Date(parsed1.getTime());
            Date parsed2 = format.parse(events.getStartDate());
            java.sql.Date sqlDate2 = new java.sql.Date(parsed2.getTime());
            Date parsed3 = format.parse(events.getCreateDate());
            java.sql.Date sqlDate3 = new java.sql.Date(parsed3.getTime());
            pr.setString(1, events.getName());
            pr.setDate(2, sqlDate2);
            pr.setDate(3, sqlDate1);
            pr.setInt(4, tmp);
            pr.setString(5, events.getDescription());
            pr.setString(6, events.getOwner());
            pr.setString(7, events.getType());
            pr.setDate(8, sqlDate3);
            check = pr.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateEvent(EventDTO events) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            if (events.getEndDate() == null || events.getStartDate() == null) {
                return check;
            }
            String sql = "UPDATE SWP391.Events SET `name` = ?, `startDate` = ?, `endDate` = ?, `status` = ?, `description` = ?, `owner` = ?, `type` = ?, `createDate` = ? WHERE (`id` = ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            int tmp = 0;
            if (events.isStatus()) {
                tmp = 1;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed1 = format.parse(events.getEndDate());
            java.sql.Date sqlDate1 = new java.sql.Date(parsed1.getTime());
            Date parsed2 = format.parse(events.getStartDate());
            java.sql.Date sqlDate2 = new java.sql.Date(parsed2.getTime());
            Date parsed3 = format.parse(events.getCreateDate());
            java.sql.Date sqlDate3 = new java.sql.Date(parsed3.getTime());
            pr.setString(1, events.getName());
            pr.setDate(2, sqlDate2);
            pr.setDate(3, sqlDate1);
            pr.setInt(4, tmp);
            pr.setString(5, events.getDescription());
            pr.setString(6, events.getOwner());
            pr.setString(7, events.getType());
            pr.setDate(8, sqlDate3);
            pr.setInt(9, events.getId());
            check = pr.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean deleteEvent(int id) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "DELETE FROM SWP391.Events WHERE (`id` = ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, id);
            check = pr.executeUpdate() > 0;
            return check;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public List<EventDTO> getList3NewEvent() {
        List<EventDTO> listEvent = new ArrayList<>();
        int id = 0;//
        String name;//
        String startDate;
        String endDate;
        boolean status;//
        String description;
        String owner;
        String type;
        String createDate;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "SELECT * FROM SWP391.Events ORDER BY createDate DESC LIMIT 3;";
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
                startDate = rs.getString("startDate");
                endDate = rs.getString("endDate");
                description = rs.getString("description");
                owner = rs.getString("owner");
                type = rs.getString("type");
                createDate = rs.getString("createDate");
                EventDTO dto = new EventDTO(id, name, startDate, endDate, status, description, owner, type, createDate);
                listEvent.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listEvent;
    }

}
