/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Admin
 */
public class NewsImageDTO {
    int id;
    String link;
    int newId;

    public NewsImageDTO() {
    }

    public NewsImageDTO(int id, String link, int newId) {
        this.id = id;
        this.link = link;
        this.newId = newId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }
    
    
}
