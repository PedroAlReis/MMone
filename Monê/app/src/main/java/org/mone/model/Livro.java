package org.mone.model;

import java.time.LocalDate;

public class Livro {
    private int id;
    private String title;
    private String author;
    private LocalDate creationDate;
    private String editor;
    private int stock;

public Livro(int id, String title, String author, LocalDate creationDate, String editor, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.creationDate = creationDate;
        this.editor = editor;
        this.stock = stock;
    }

public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}