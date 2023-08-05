package com.group.libraryapp.dto.book.request;

public class BookCreateRequest {
    private String name;

    public BookCreateRequest(String name){
        this.name = name;
    }

    public BookCreateRequest() { }

    public String getName() {
        return name;
    }
}
