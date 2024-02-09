package com.example.QuickPasta.dto.request;

import com.example.QuickPasta.Enum.Gender;

import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    String name;

    Gender gender;

    String email;

    String phoneNo;

    String address;

    String dob;
}
