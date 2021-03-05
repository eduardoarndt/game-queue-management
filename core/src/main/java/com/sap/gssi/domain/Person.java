package com.sap.gssi.domain;

import java.util.Date;

public class Person {
    String name;
    Date birthDate;
    Boolean isAssociate;

    public Person(String name, Date birthDate, Boolean isAssociate) {
        this.name = name;
        this.birthDate = birthDate;
        this.isAssociate = isAssociate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getAssociate() {
        return isAssociate;
    }

    public void setAssociate(Boolean associate) {
        isAssociate = associate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", isAssociate=" + isAssociate +
                '}';
    }
}
