/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.SubjectDTO;
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
public class SubjectDAO {

    public List<SubjectDTO> getListSubject() {
        List<SubjectDTO> listSubject = new ArrayList<>();
        int id = 0;
        String link = null;
        String name = null;
        int majorId = 0;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "SELECT * FROM SWP391.Subjects;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("idSubjects");
                name = rs.getString("name");
                link = rs.getString("link");
                majorId = rs.getInt("Major_idMajor");
                SubjectDTO dto = new SubjectDTO(id, link, name, majorId);
                listSubject.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSubject;
    }

    public boolean createtSubject(SubjectDTO subject) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "INSERT INTO `SWP391`.`Subjects` (`link`, `name`, `Major_idMajor`) VALUES (?, ?, ?);";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, subject.getLink());
            pr.setString(2, subject.getName());
            pr.setInt(3, subject.getMajorId());
            check = pr.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateSubject(SubjectDTO subject) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();

            String sql = "UPDATE `SWP391`.`Subjects` SET `link` = ?, `name` = ?, `Major_idMajor` = ? WHERE (`idSubjects` = ?);";
            PreparedStatement pr = con.prepareStatement(sql);

            pr.setString(1, subject.getLink());
            pr.setString(2, subject.getName());
            pr.setInt(3, subject.getMajorId());
            pr.setInt(4, subject.getId());
            check = pr.executeUpdate() > 0;
            return check;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean deleteSubject(int id) {
        boolean check = false;
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            String sql = "DELETE FROM `SWP391`.`Subjects` WHERE (`idSubjects` = ?);";
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
