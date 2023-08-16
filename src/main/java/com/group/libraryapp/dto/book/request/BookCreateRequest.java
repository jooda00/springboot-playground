package com.group.libraryapp.dto.book.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class BookCreateRequest {
    @NotNull
    private String name;

    @NotNull
    private String introduction;

    public BookCreateRequest() { }

}
