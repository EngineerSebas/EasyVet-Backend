package com.spring.backend.easyvet.model.service.impl;



import com.spring.backend.easyvet.dto.ResetPasswordDTO;
import com.spring.backend.easyvet.model.entity.Propietor;
import com.spring.backend.easyvet.model.entity.User;
import com.spring.backend.easyvet.model.entity.Veterinary;
import com.spring.backend.easyvet.model.repository.IPropietorRepository;
import com.spring.backend.easyvet.model.repository.IVeterynaryRepository;
import com.spring.backend.easyvet.model.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IPropietorRepository propietorRepository;

    @Autowired
    private IVeterynaryRepository veterynaryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String verifyEmail(String email) {
        System.out.println(email);
        User user = propietorRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = veterynaryRepository.findByEmail(email).orElse(null);
        }

        if (user == null) {
            throw new RuntimeException("Correo electrónico no encontrado");
        }

        // Devolver los primeros 4 caracteres del DNI y los últimos 2
        return user.getDni().substring(0, 2) + "XXXX" + user.getDni().substring(user.getDni().length() - 2);
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        User user = propietorRepository.findByEmail(resetPasswordDTO.getEmail()).orElse(null);
        if (user == null) {
            user = veterynaryRepository.findByEmail(resetPasswordDTO.getEmail()).orElse(null);
        }

        if (user == null || !user.getDni().equals(resetPasswordDTO.getDni())) {
            throw new RuntimeException("Correo electrónico o DNI incorrecto");
        }

        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));

        // Guardar el usuario actualizado en la base de datos
        if (user instanceof Propietor) {
            propietorRepository.save((Propietor) user);
        } else if (user instanceof Veterinary) {
            veterynaryRepository.save((Veterinary) user);
        }
    }



}
