package io.melakuera.springvue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZamenaOfferWebSocketRequest {
    private long teacherId;
    private String audience;
    private String to;
    private String comment;
}
