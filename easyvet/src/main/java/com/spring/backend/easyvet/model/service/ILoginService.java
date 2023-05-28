package com.spring.backend.easyvet.model.service;

import com.spring.backend.easyvet.dto.ResetPasswordDTO;

public interface ILoginService {
    public String verifyEmail(String email);
    public void resetPassword(ResetPasswordDTO resetPasswordDTO);
}
