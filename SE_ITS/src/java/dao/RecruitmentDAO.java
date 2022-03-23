/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.RecruitmentDTO;
import java.sql.Connection;
import java.sql.Date;
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
public class RecruitmentDAO {

    public List<RecruitmentDTO> getListRecruitment(int page) {
        List<RecruitmentDTO> listRecruitment = new ArrayList<>();
        List<RecruitmentDTO> result = new ArrayList<>();
        int id = 0;
        String startDate = null;
        String endDate = null;
        float salary = 0;
        String description = null;
        int companyId = 0;
        String name = null;
        boolean status = false;
        String owner = null;
        String createDate = null;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "SELECT * FROM SWP391.Recruitment;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("idRecruitment");
                startDate = rs.getString("startDate");
                endDate = rs.getString("endDate");
                salary = rs.getFloat("salary");
                description = rs.getString("description");
                companyId = rs.getInt("Company_id");
                name = rs.getString("name");
                if (rs.getInt("status") == 0) {
                    status = false;
                } else {
                    status = true;
                }
                owner = rs.getString("owner");
                createDate = rs.getString("createDate");
                RecruitmentDTO dto = new RecruitmentDTO(id, startDate, endDate, salary, description, companyId, name, status, owner, createDate);
                listRecruitment.add(dto);
            }
            result = pagedResponse(listRecruitment, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<RecruitmentDTO> search(int page, String search) {
        List<RecruitmentDTO> listRecruitment = new ArrayList<>();
        List<RecruitmentDTO> result = new ArrayList<>();
        int id = 0;
        String startDate = null;
        String endDate = null;
        float salary = 0;
        String description = null;
        int companyId = 0;
        String name = null;
        boolean status = false;
        String owner = null;
        String createDate = null;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "SELECT * FROM SWP391.Recruitment WHERE name LIKE ?;";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, "%" + search + "%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idRecruitment");
                startDate = rs.getString("startDate");
                endDate = rs.getString("endDate");
                salary = rs.getFloat("salary");
                description = rs.getString("description");
                companyId = rs.getInt("Company_id");
                if (rs.getInt("status") == 0) {
                    status = false;
                } else {
                    status = true;
                }
                owner = rs.getString("owner");
                createDate = rs.getString("createDate");
                RecruitmentDTO dto = new RecruitmentDTO(id, startDate, endDate, salary, description, companyId, name, status, owner, createDate);
                listRecruitment.add(dto);
            }
            result = pagedResponse(listRecruitment, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean createtRecruitment(RecruitmentDTO recruitment) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "INSERT INTO `SWP391`.`Recruitment` (`startDate`, `endDate`, `salary`, `description`, `Company_id`, `name`, `status`, `owner`, `createDate` ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed1 = format.parse(recruitment.getEndDate());
            java.sql.Date sqlDate1 = new java.sql.Date(parsed1.getTime());
            java.util.Date parsed2 = format.parse(recruitment.getStartDate());
            java.sql.Date sqlDate2 = new java.sql.Date(parsed2.getTime());
            java.util.Date parsed3 = format.parse(recruitment.getCreateDate());
            java.sql.Date sqlDate3 = new java.sql.Date(parsed2.getTime());
            int tmp = 0;
            if (recruitment.isStatus()) {
                tmp = 1;
            }
            pr.setDate(1, sqlDate2);
            pr.setDate(2, sqlDate1);
            pr.setFloat(3, recruitment.getSalary());
            pr.setString(4, recruitment.getDescription());
            pr.setInt(5, recruitment.getCompanyId());
            pr.setString(6, recruitment.getName());
            pr.setInt(7, tmp);
            pr.setString(8, recruitment.getOwner());
            pr.setDate(9, sqlDate3);

            check = pr.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateRecruitment(RecruitmentDTO recruitment) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "UPDATE `SWP391`.`Recruitment` SET `startDate` = ?, `endDate` = ?, `salary` = ?, `description` = ?, `Company_id` = ?, `name` = ?, `status` = ?, `owner` = ?, `createDate` = ? WHERE (`idRecruitment` = ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed1 = format.parse(recruitment.getEndDate());
            java.sql.Date sqlDate1 = new java.sql.Date(parsed1.getTime());
            java.util.Date parsed2 = format.parse(recruitment.getStartDate());
            java.sql.Date sqlDate2 = new java.sql.Date(parsed1.getTime());
            java.util.Date parsed3 = format.parse(recruitment.getCreateDate());
            java.sql.Date sqlDate3 = new java.sql.Date(parsed2.getTime());
            int tmp = 0;
            if (recruitment.isStatus()) {
                tmp = 1;
            }
            pr.setDate(1, sqlDate2);
            pr.setDate(2, sqlDate1);
            pr.setFloat(3, recruitment.getSalary());
            pr.setString(4, recruitment.getDescription());
            pr.setInt(5, recruitment.getCompanyId());
            pr.setString(6, recruitment.getName());
            pr.setInt(7, tmp);
            pr.setString(8, recruitment.getOwner());
            pr.setDate(9, sqlDate3);
            pr.setInt(10, recruitment.getId());
            check = pr.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean deleteRecruitment(int id) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "DELETE FROM `SWP391`.`Recruitment` WHERE (`idRecruitment` = ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, id);
            check = pr.executeUpdate() > 0;
            return check;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public List<RecruitmentDTO> pagedResponse(List<RecruitmentDTO> allItems, int page) {
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
