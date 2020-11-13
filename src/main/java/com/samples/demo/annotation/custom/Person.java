package com.samples.demo.annotation.custom;

import java.util.Map;

@JsonSerializable
public class Person {
    @JsonElement(key = "first_name")
    private String firstName;
    @JsonElement(key = "last_name")
    private String lastName;
    @JsonElement(key = "person_age")
    private String age;
    @JsonArray(key = "pets")
    private Map<String, String> pets;
    @JsonElement
    private String address;

    public Person(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person(String firstName, String lastName, String age, Map pets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.pets = pets;
    }

    @Init
    private void initNames() {
        this.firstName = this.firstName.substring(0, 1)
                .toUpperCase() + this.firstName.substring(1);
        this.lastName = this.lastName.substring(0, 1)
                .toUpperCase() + this.lastName.substring(1);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, String> getPets() {
        return pets;
    }

    public void setPets(Map<String, String> pets) {
        this.pets = pets;
    }
}
