/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.EventCategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class EventCategoryDAO {
    public List<EventCategoryDTO> getListEventCategory() {
        List<EventCategoryDTO> listEventCategory = new ArrayList<>();
        int id = 0;
        String name = null;
        int eventId = 0;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "SELECT * FROM SWP391.Event_Category;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("idEvent_Category");
                name = rs.getString("name");
                eventId = rs.getInt("Events_id");
                EventCategoryDTO dto = new EventCategoryDTO(id, name, eventId);
                listEventCategory.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listEventCategory;
    }

    public boolean createtEventCategory(EventCategoryDTO eventCategory) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "INSERT INTO SWP391.Event_Category (`name`, `Events_id`) VALUES (?, ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, eventCategory.getNsme());
            pr.setInt(2, eventCategory.getEventsId());
            check = pr.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateEventCategory(EventCategoryDTO eventCategory) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "UPDATE SWP391.Event_Category SET `name` = ?, `Events_id` = ? WHERE (`idEvent_Category` = ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, eventCategory.getNsme());
            pr.setInt(2, eventCategory.getEventsId());
            pr.setInt(3, eventCategory.getId());
            check = pr.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean deleteEventCategory(int id) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "DELETE FROM SWP391.Event_Category WHERE (`idEvent_Category` = ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, id);
            check = pr.executeUpdate() > 0;
            return check;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
