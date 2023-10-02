package com.greger.inviduelluppg.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
//    @Column(name = "address")
//    private int address;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private int phone;
    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @ManyToOne
    private Address address = new Address();

    public Member() {
    }

    public Member(int id, String firstName, String lastName, int address, String email, int phone, String dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
//        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

//    public int getAddress() {
//        return address;
//    }
//
//    public void setAddress(int address) {
//        this.address = address;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address=" + address +
                '}';
    }
}