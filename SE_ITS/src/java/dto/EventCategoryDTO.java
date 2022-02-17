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
public class EventCategoryDTO {
    int id;
    String nsme;
    int eventsId;

    public EventCategoryDTO() {
    }

    public EventCategoryDTO(int id, String nsme, int eventsId) {
        this.id = id;
        this.nsme = nsme;
        this.eventsId = eventsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNsme() {
        return nsme;
    }

    public void setNsme(String nsme) {
        this.nsme = nsme;
    }

    public int getEventsId() {
        return eventsId;
    }

    public void setEventsId(int eventsId) {
        this.eventsId = eventsId;
    }
    
}
