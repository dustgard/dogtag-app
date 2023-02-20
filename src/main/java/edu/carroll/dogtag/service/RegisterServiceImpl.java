package edu.carroll.dogtag.service;

import edu.carroll.dogtag.jpa.model.Login;
import edu.carroll.dogtag.jpa.repo.RegisterRepository;
import edu.carroll.dogtag.web.form.RegisterForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService{

    private static final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);
    private final RegisterRepository registerRepo;

    public RegisterServiceImpl(RegisterRepository registerRepo) {
        this.registerRepo = registerRepo;
    }

    /**
     * Given a registerForm, determine if the information provided is valid, and the user exists in the system.
     *
     * @param user - check for user in database
     * @return true if data user does not exist already
     */
    @Override
    public boolean userExists(String user) {
        log.info("userExists: '{}' exists already", user);
        // Always do the lookup in a case-insensitive manner (lower-casing the data).
        List<Login> users = registerRepo.findByUserIgnoreCase(user);
        return !users.isEmpty();
    }


    @Override
    public void register(Login register) {
        registerRepo.save(register);
    }
}



