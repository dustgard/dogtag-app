package edu.carroll.dogtag.service;
import edu.carroll.dogtag.jpa.model.Login;
import edu.carroll.dogtag.jpa.model.UserProfile;
import edu.carroll.dogtag.jpa.repo.LoginRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test to pass happy, crappy and crazy inputs to the UserProfileService, and
 * it should handle all situation that are tested and return the expected results
 * a user must be registered to be in the database so that a login can be attempted
 * If no user is present in the database it will always come back with null for returning
 * a Login object. This will not allow a UserProfile to be saved into the database if no
 * user exist.
 */
@SpringBootTest
@Transactional
class UserProfileServiceImplTest {

    private static final String user = "usertest";
    private static final String password = "passwordtest";
    private static final String email = "emailTest@gmail.com";
    private static final Logger log = LoggerFactory.getLogger(RegisterServiceImplTest.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private RegisterService registerService;

    @Test
    void fetchUserProfileHappy() {
        UserProfile noUser = userProfileService.fetchUserProfile("user1");
        assertNull(noUser);

    }

    @Test
    void fetchUserProfileHappy1() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile noUser = userProfileService.fetchUserProfile(user);
        assertNull(noUser);

    }

    @Test
    void fetchUserProfileHappy2() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        UserProfile userFound = userProfileService.fetchUserProfile(user);
        assertNotNull(userFound);

    }

    @Test
    void fetchUserProfileHappyFname1() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertEquals(userProfileService.fetchUserProfile(user), userProfile);
        assertEquals("Dustin", userProfileService.fetchUserProfile(user).getFname());

    }

    @Test
    void fetchUserProfileCrappyFname1() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertNotEquals(userProfileService.fetchUserProfile(user).getFname(), userProfile.getLname());
        assertEquals("Dustin", userProfileService.fetchUserProfile(user).getFname());

    }

    @Test
    void fetchUserProfileCrappyFname2() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertEquals(userProfileService.fetchUserProfile(user).getFname(), userProfile.getFname());
        assertNotEquals("Gardner", userProfileService.fetchUserProfile(user).getFname());

    }

    @Test
    void fetchUserProfileHappyLname1() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertEquals(userProfileService.fetchUserProfile(user).getLname(), userProfile.getLname());
        assertEquals("Gardner", userProfileService.fetchUserProfile(user).getLname());

    }

    @Test
    void fetchUserProfileCrappyLname1() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertNotEquals(userProfileService.fetchUserProfile(user).getLname(), userProfile.getFname());
        assertEquals("Gardner", userProfileService.fetchUserProfile(user).getLname());

    }

    @Test
    void fetchUserProfileCrappyLname2() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertEquals(userProfileService.fetchUserProfile(user).getLname(), userProfile.getLname());
        assertNotEquals("Dustin", userProfileService.fetchUserProfile(user).getLname());

    }

    @Test
    void fetchUserProfileCrappyLname3() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertNotEquals(userProfileService.fetchUserProfile(user).getLname(), userProfile.getPhone());
        assertEquals("Gardner", userProfileService.fetchUserProfile(user).getLname());

    }

    @Test
    void fetchUserProfileCrappyLname4() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertNotEquals(userProfileService.fetchUserProfile(user).getLname(), userProfile.getPhone());
        assertNotEquals("Dustin", userProfileService.fetchUserProfile(user).getLname());

    }

    @Test
    void fetchUserProfileHappyPhone1() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertEquals(userProfileService.fetchUserProfile(user).getPhone(), userProfile.getPhone());
        assertEquals("406-980-0947", userProfileService.fetchUserProfile(user).getPhone());

    }

    @Test
    void fetchUserProfileCrappyPhone1() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertNotEquals(userProfileService.fetchUserProfile(user).getPhone(), userProfile.getFname());
        assertEquals("406-980-0947", userProfileService.fetchUserProfile(user).getPhone());

    }

    @Test
    void fetchUserProfileCrappyPhone2() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertEquals(userProfileService.fetchUserProfile(user).getPhone(), userProfile.getPhone());
        assertNotEquals("Dustin", userProfileService.fetchUserProfile(user).getPhone());

    }

    @Test
    void fetchUserProfileCrappyPhone3() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertNotEquals(userProfileService.fetchUserProfile(user).getPhone(), userProfile.getFname());
        assertEquals("406-980-0947", userProfileService.fetchUserProfile(user).getPhone());

    }

    @Test
    void fetchUserProfileCrappyPhone4() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        assertNotEquals(userProfileService.fetchUserProfile(user).getPhone(), userProfile.getLogin());
        assertNotEquals("Dustin", userProfileService.fetchUserProfile(user).getPhone());

    }

    @Test
    void setProfileHappy() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        assertTrue(userProfileService.setUserProfile(userProfile));
    }

    @Test
    void setProfileBlankFormPassedCrappy() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfile.setLname("Gardner");
        userProfile.setPhone("406-980-0947");
        userProfile.setLogin(loginService.findLogin(user));
        userProfileService.setUserProfile(userProfile);
        userProfileService.fetchUserProfile(user);
        UserProfile userProfile2 = new UserProfile();
        assertFalse(userProfileService.setUserProfile(userProfile2));
    }

    @Test
    void setProfileOnlyFnameFormPassedCrappy2() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Login createUser = new Login();
        createUser.setPassword(password);
        createUser.setUser(user);
        createUser.setEmail(email);
        registerService.register(createUser);
        UserProfile userProfile = new UserProfile();
        userProfile.setFname("Dustin");
        userProfileService.setUserProfile(userProfile);
        assertFalse(userProfileService.setUserProfile(userProfile));
    }
}