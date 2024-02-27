package com.example.QuickPasta.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    String name;
    String address;
    String phoneNo;
    CartResponse cart;
}
