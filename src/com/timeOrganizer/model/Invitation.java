package com.timeOrganizer.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Invitation {

    public Integer InvitationId;


    public StringProperty Sender;


    public StringProperty AdventureName;

    public Integer getInvitationId() {
        return InvitationId;
    }

    public void setInvitationId(Integer invitationId) {
        InvitationId = invitationId;
    }

    public String getSender() {
        return Sender.get();
    }

    public StringProperty senderProperty() {
        return Sender;
    }

    public void setSender(String sender) {
        this.Sender.set(sender);
    }

    public String getAdventureName() {
        return AdventureName.get();
    }

    public StringProperty adventureNameProperty() {
        return AdventureName;
    }

    public void setAdventureName(String adventureName) {
        this.AdventureName.set(adventureName);
    }

    public Invitation(Integer invitationId, String sender, String adventureName) {
        Sender = new SimpleStringProperty(sender);
        InvitationId = invitationId;
        AdventureName = new SimpleStringProperty(adventureName);
    }
}
