package edu.carroll.dogtag.service;

import edu.carroll.dogtag.jpa.model.Login;
import edu.carroll.dogtag.jpa.repo.RegisterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

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
        // Always do the lookup in a case-insensitive manner (lower-casing the data).
        List<Login> users = registerRepo.findByUserIgnoreCase(user);
        log.info("Checking if {} user exists", user);
        return !users.isEmpty();
    }

    @Override
    public boolean emailExists(String email) {
        List<Login> emails = registerRepo.findByEmailIgnoreCase(email);
        log.info("Checking if {} email exists", email);
        return !emails.isEmpty();
    }

    @Override
    public boolean register(Login register) {
        if (register.getUser() != null && !register.getUser().isBlank()
                //fix is blank
                && register.getPassword() != null && register.getPassword() != ""
                && register.getEmail() != null && register.getEmail() != "") {

            registerRepo.save(register);
            log.info("Register Info for {} sent to login table", register.getUser());
            return true;
        }
        else {
            log.info("Can can not be null or blank {}", register.getUser());
            return false;
        }
    }

    @Override
    public void deleteByUser(String userEntityDelete) {
        List<Login> userRowDelete = registerRepo.findByUserIgnoreCase(userEntityDelete);
        registerRepo.deleteAll(userRowDelete);
        log.info("User {} was deleted", userEntityDelete);
    }


}




