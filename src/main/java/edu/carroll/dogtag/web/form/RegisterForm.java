package edu.carroll.dogtag.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


/**
 * The RegisterForm is used to take the information from the controller that the html page
 * posted and organized it in way that is usable for java. This form also has set min and
 * max for the three variables that can be used.  Once the correct information is added to the form
 * it can then be used to set attributes in the Register model which is then sent to the RegisterService
 * to be verified before saving it in the database.
 */
public class RegisterForm {
    @NotBlank
    @Size(min = 6, message = "Username must be at least 6 characters long")
    @Size(max = 16, message = "Username must be less than 16 character long")
    private String user;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Size(max = 16, message = "Password must be less than 16 character long")
    private String password;

    @NotBlank
    @Size(min = 6, message = "Email must be at least 6 characters long")
    @Size(max = 50, message = "Email must be less than 50 character long")
    private String email;


    public RegisterForm() {
    }

    public RegisterForm(String user, String password, String email) {
        this.user = user;
        this.password = password;
        this.email = email;

    }

    /**
     * @return the user that is stored in the database.
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user sets the user in the database.
     */
    public void setUser(String user) {
        this.user = user;

    }

    /**
     * @return password to compare with what the user entered in RegisterForm
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password sets the password in the database
     */
    public void setPassword(String password) {
        this.password = password;

    }

    /**
     * @return email to compare with what the user entered in RegisterForm
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email sets the password in the database
     */
    public void setEmail(String email) {
        this.email = email;

    }

}

