package data;

import books.Book;

import java.util.ArrayList;

public abstract class User {

    private String nim;

    public User(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void displayBooks(Book[] bookList){

    }
    public void addStudent(){

    }
    public void choiceBook(Book[] bookList){

    }

    public abstract void choiceBook(ArrayList<Book> bookList);
}
