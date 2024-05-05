package com.testehan.springsecurity.chpt3.ex2;

//@Entity           // commented out since i did not want this to create a table in the DB
public class User {
//    @Id
    private int id;
    private String username;
    private String password;
    private String authority;
    // Omitted getters and setters


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthority() {
        return authority;
    }
}
