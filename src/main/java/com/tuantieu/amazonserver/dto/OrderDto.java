package com.tuantieu.amazonserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String address;
    private String note;

    public OrderDto(String address, String note) {
        this.address = address;
        this.note = note;
    }
}
