package org.mone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // armazene o hash, não a senha pura

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int role;

    protected Usuario () {}


public Usuario (int id, String name, String username, String password, String status, int role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public String getStatus() {
        return status;
    }


    public int getRole() {
        return role;
    }


    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setRole(int role) {
        this.role = role;
    }
}


