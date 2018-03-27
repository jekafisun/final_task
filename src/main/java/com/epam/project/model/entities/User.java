package com.epam.project.model.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private String username;
    private String password;
}
