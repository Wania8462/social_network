package com.example.social_network.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorObject {
    private Date timeStamp;
    private int status;
    private String error;
    private String path;
}
