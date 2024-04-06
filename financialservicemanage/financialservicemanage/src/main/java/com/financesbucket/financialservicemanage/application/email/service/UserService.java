package com.financesbucket.financialservicemanage.application.email.service;

import com.financesbucket.financialservicemanage.application.email.emailexception.UserCreationException;
import com.financesbucket.financialservicemanage.domain.customer.customermodel.Customer;
import com.financesbucket.financialservicemanage.infrastructure.adapters.email.entity.User;
import com.financesbucket.financialservicemanage.infrastructure.adapters.email.repositoryemail.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MailSender mailSender;

    @Autowired
    public UserService(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public void createUser(String username, String email, Customer register) throws UserCreationException {
        try {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            userRepository.save(user);
            sendWelcomeEmail(email, register);
        } catch (MailException e) {
            throw new UserCreationException("Error al enviar el correo de bienvenida", e);
        }
    }

    private void sendWelcomeEmail(String email, Customer register) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("¡Bienvenido a nuestra aplicación!");
        message.setText("¡Gracias por registrarte en nuestra aplicación! " + register);

        mailSender.send(message);
    }
}
