package org.mone.model;

import java.time.LocalDate;

public class Processo {
    private int id;
    private LocalDate dateProcess;
    private String type;
    private String bookName;


public Processo (int id, LocalDate dateProcess, String type, String bookName){
    this.id = id;
    this.dateProcess = dateProcess;
    this.type = type;
    this.bookName = bookName;
}

public int getId(){
  return id;
}

public void setId (int id){
  this.id = id;

}

public LocalDate getDateProcess(){
    return dateProcess;
}

public void setDateProcess(LocalDate dateProcess){
    this.dateProcess = dateProcess;
}

public String getType(){
    return type;
}

public void setType(String type){
    this.type = type;
}

public String getBookName(){
    return bookName;
}

public void setBookName(String bookName){
    this.bookName = bookName;
}

}
