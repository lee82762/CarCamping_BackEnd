package com.Hanium.CarCamping.domain.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePasswordDto {
    private String oldPassword;
    private String newPassword;

}
