package com.example.projectqlch.Service.Impl;

import com.example.projectqlch.Convert.UserConvert;
import com.example.projectqlch.Entity.SignUpToken;
import com.example.projectqlch.Entity.User;
import com.example.projectqlch.Repository.TokenRepository;
import com.example.projectqlch.Repository.UserRepository;
import com.example.projectqlch.Service.UserService;
import com.example.projectqlch.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserConvert userConvert;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private EmailService emailService;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/UserImage/307691984_811271283242512_124413355601862320_n.jpg");

    @Override
    public User signUp(UserDTO userDTO) {
        User user = userConvert.toEntityUser(userDTO);
        File file = new File(String.valueOf(CURRENT_FOLDER));
        user.setImageUrl(CURRENT_FOLDER.toString());
        user.setActive(false);
        User savedUser = userRepository.save(user);
        generateToken(user);
        return savedUser;
    }

    @Override
    public String verifyToken(String token) {
        Optional<SignUpToken> optionalSignUpToken = tokenRepository.findSignUpTokenByToken(token);
        if (optionalSignUpToken.isEmpty()) {
            return "Activate failed";
        }
        SignUpToken signUpToken = optionalSignUpToken.get();
        User user = signUpToken.getUser();
        user.setActive(true);
        userRepository.save(user);
        return "Activate successfully";
    }

    @Override
    public String deleteUser(Long[] ids) {
        return null;
    }

    @Override
    public User updateUser(UserDTO userDTO) {

        return null;
    }

    public void generateToken(User user){
        UUID token = UUID.randomUUID();
        SignUpToken signUpToken = new SignUpToken();
        signUpToken.setUser(user);
        signUpToken.setToken(String.valueOf(token));
        tokenRepository.save(signUpToken);
        try {
            emailService.sendEmail(user.getEmail(), "Please active your account", "localhost:8081/user/verify/" + token);
        } catch (Exception e) {
            logger.error("Send email failed");
            throw e;
        }
        logger.info("Send email activate successfully");
    }
}
