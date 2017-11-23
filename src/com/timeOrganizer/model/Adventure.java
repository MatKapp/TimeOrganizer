package com.timeOrganizer.model;

import java.util.Date;

public class Adventure {
    public int Id;
    public Date AdventureDateTime;
    public String Name;
    public String Address;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getAdventureDateTime() {
        return AdventureDateTime;
    }

    public void setAdventureDateTime(Date adventureDateTime) {
        AdventureDateTime = adventureDateTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
