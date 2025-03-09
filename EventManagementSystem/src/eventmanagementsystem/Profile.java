package eventmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Profile {

    void updateProfile(String userName, String userEmail, File myObj) throws FileNotFoundException, IOException;
    void showLoginSuccessMsg();
    void showRegisterSuccessMsg();

}
