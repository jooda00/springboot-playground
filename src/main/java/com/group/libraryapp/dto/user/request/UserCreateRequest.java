package com.group.libraryapp.dto.user.request;

import javax.validation.constraints.NotNull;

public class UserCreateRequest {

    @NotNull
    private String name;
    private Integer age; // null 표현 가능(선택이므로)

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
