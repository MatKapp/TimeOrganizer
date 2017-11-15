package com.timeOrganizer.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Person {

    private final StringProperty firstName;

    private final StringProperty lastName;

    private final StringProperty street;

    private final IntegerProperty postalCode;

    private final StringProperty city;

    private final StringProperty emailAddress;

    private final ObjectProperty<LocalDate> birthday;

    public Person() {
        this(null, null, null);
    }

    public Person(String firstName, String lastName, String emailAddress) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.emailAddress = new SimpleStringProperty(emailAddress);

        // Some initial dummy data, just for convenient testing.
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public Person(StringProperty firstName, StringProperty lastName, StringProperty street, IntegerProperty postalCode, StringProperty city, StringProperty emailAddress, ObjectProperty<LocalDate> birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
    }


}
