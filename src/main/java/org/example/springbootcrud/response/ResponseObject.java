package org.example.springbootcrud.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseObject {
    private String status;
    private String messages;
    private Object data;
}
