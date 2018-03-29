package com.epam.project.model.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Message {
    private String recipient;
    private String subject;
    private String body;
    private String attachment;
}
