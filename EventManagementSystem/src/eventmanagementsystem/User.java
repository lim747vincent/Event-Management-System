package eventmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class User implements Profile {

    private String userId;
    private String password;
    private String email;
    private String name;

    User(String... args) {
        this.userId = args[0];
        this.password = args[1];
        this.name = args[2];
        this.email = args[3];
    }

    User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void showLoginSuccessMsg() {
        System.out.println("\nLogin sucessfully!");
    }

    @Override
    public void showRegisterSuccessMsg() {
        System.out.println("\nRegistered sucessfully!");
    }

    public static String[] login(String userId, String password, File myObj) throws FileNotFoundException {

        if (!InputValidation.login(userId, password)) {
            System.out.println("\n\nInvalid input...Try again");
        } else {

            Scanner sc = new Scanner(myObj);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();

                String[] arrOfData = data.split(";");

                if (arrOfData[0].equals(userId) && arrOfData[1].equals(password)) {

                    sc.close();
                    return arrOfData;
                }

            }

            sc.close();
            return null;

        }
        return null;
    }

    public static String[] registration(String userId, String password, String passwordAgain, String userName, String userEmail, File myObj) throws FileNotFoundException, IOException {

        if (!(password.equals(passwordAgain))) {
            System.out.println("\n\nInvalid input...Try again");
        } else {
            if (!InputValidation.registration(userId, password, userName, userEmail)) {
                System.out.println("\n\nInvalid input...Try again");
            } else {

                Scanner sc = new Scanner(myObj);

                int counter = 0;

                while (sc.hasNextLine()) {
                    String data = sc.nextLine();

                    String[] arrOfData = data.split(";");

                    if (arrOfData[0].equals(userId)) {
                        System.out.println("\n\nInvalid input...Try again");
                        sc.close();
                        return null;
                    }
                    counter++;
                }

                sc.close();

                Scanner scanner = new Scanner(myObj);

                String[] tempDataHolder = new String[counter];

                for (int i = 0; i < counter; i++) {
                    tempDataHolder[i] = scanner.nextLine();
                }

                scanner.close();

                boolean result = Files.deleteIfExists(myObj.toPath());

                if (result) {

                    File newFile = myObj;

                    if (newFile.createNewFile()) {
                        try (FileWriter myWriter = new FileWriter(newFile)) {

                            for (String tempData : tempDataHolder) {
                                String[] dataArray = tempData.split(";");

                                for (String data : dataArray) {
                                    myWriter.write(data + ";");
                                }
                                myWriter.write("\n");
                            }

                            myWriter.write(userId + ";");
                            myWriter.write(password + ";");
                            myWriter.write(userName + ";");
                            myWriter.write(userEmail + ";");

                        } catch (IOException e) {
                            throw new IOException("Problem in myWriter filewriter!");
                        }
                    }

                    return new String[]{userId, password, userName, userEmail};
                }

            }
        }

        return null;
    }

    public static void forgotPassword(String userEmail, Scanner scanner, String userCode) throws FileNotFoundException, IOException {

        if (!InputValidation.forgotPassword(userEmail)) {
            System.out.println("\n\nInvalid input...Try again");
        } else {

            int counter = 0;

            File myObj;

            if (userCode.equals("1")) {
                myObj = Database.getATTENDEE();
            } else {
                myObj = Database.getEVENTORGANIZER();
            }

            Scanner sc = new Scanner(myObj);

            while (sc.hasNextLine()) {
                counter++;

                String data = sc.nextLine();

                String[] arrOfData = data.split(";");

                if (arrOfData[3].equals(userEmail)) {
                    sc.close();

                    Files.deleteIfExists(Database.getRECOVERYCODE().toPath());

                    try (FileWriter fw = new FileWriter(Database.getRECOVERYCODE())) {

                        int max = 9999;
                        int min = 1000;

                        int randomNumber = (int) ((Math.random() * (max - min)) + min);

                        String randomNumberString = randomNumber + "";

                        fw.write(randomNumberString);

                        // close the file
                        fw.close();

                        System.out.println("Recovery code sent! Please check mailbox...");

                        System.out.print("Please enter recovery code to reset password: ");

                        String recoveryCodeInput = scanner.next();

                        if (randomNumberString.equals(recoveryCodeInput)) {
                            System.out.println("\nNotice:\n"
                                    + "a. Only accept alphabet and/or digit for password input\n"
                                    + "b. Input is case sensitive\n"
                                    + "-------------------------------------");

                            System.out.print("Enter new password: ");
                            String newPasswordInput = scanner.next();
                            System.out.print("Enter new password again: ");
                            String newPasswordAgainInput = scanner.next();

                            if (!InputValidation.forgotPassword(newPasswordInput, newPasswordAgainInput) || !(newPasswordInput.equals(newPasswordAgainInput))) {
                                System.out.println("\n\nInvalid input...Try again");
                                return;
                            }

                            Scanner scan = new Scanner(myObj);

                            int count = 0;

                            while (scan.hasNextLine()) {
                                scan.nextLine();

                                count++;
                            }

                            scan.close();

                            String[] tempDataHolder = new String[count];

                            Scanner scn = new Scanner(myObj);

                            for (int i = 0; i < count; i++) {
                                tempDataHolder[i] = scn.nextLine();
                            }

                            scn.close();

                            Files.deleteIfExists(myObj.toPath());
                            File newFile = myObj;

                            try (FileWriter myWriter = new FileWriter(newFile)) {

                                for (String tempData : tempDataHolder) {

                                    String[] dataArray = tempData.split(";");

                                    if (dataArray[3].equals(userEmail)) {
                                        myWriter.write(dataArray[0] + ";");
                                        myWriter.write(newPasswordInput + ";");
                                        myWriter.write(dataArray[2] + ";");
                                        myWriter.write(dataArray[3] + ";");

                                    } else {
                                        for (String datainput : dataArray) {
                                            myWriter.write(datainput + ";");
                                        }
                                    }

                                    myWriter.write("\n");
                                }

                                System.out.println("\nPassword updated!");
                                return;

                            } catch (IOException e) {
                                throw new IOException("Problem in myWriter filewriter!");
                            }

                        } else {
                            System.out.println("\n\nWrong recovery code! Please try again...");
                            return;
                        }

                    } catch (IOException e) {
                        throw new IOException("Problem in myWriter filewriter!");
                    }

                }

            }

            System.out.println("\n\nInvalid input...Try again");

        }

    }

    @Override
    public void updateProfile(String userName, String userEmail, File myObj) throws FileNotFoundException, IOException {

        if (!InputValidation.updateProfile(userName, userEmail)) {
            System.out.println("\n\nInvalid input...Try again");
        } else {

            Scanner sc = new Scanner(myObj);

            int counter = 0;

            while (sc.hasNextLine()) {

                sc.nextLine();

                counter++;
            }

            sc.close();

            Scanner scanner = new Scanner(myObj);

            String[] tempDataHolder = new String[counter];

            for (int i = 0; i < counter; i++) {
                tempDataHolder[i] = scanner.nextLine();
            }

            scanner.close();

            boolean result = Files.deleteIfExists(myObj.toPath());

            if (result) {

                File newFile = myObj;

                if (newFile.createNewFile()) {
                    try (FileWriter myWriter = new FileWriter(newFile)) {

                        for (String tempData : tempDataHolder) {
                            String[] dataArray = tempData.split(";");

                            if (dataArray[0].equals(this.userId) && dataArray[1].equals(this.password)) {
                                myWriter.write(this.userId + ";");
                                myWriter.write(this.password + ";");
                                myWriter.write(userName + ";");
                                myWriter.write(userEmail + ";");
                                myWriter.write("\n");
                            } else {
                                for (String data : dataArray) {
                                    myWriter.write(data + ";");
                                }
                                myWriter.write("\n");
                            }

                        }

                    } catch (IOException e) {
                        throw new IOException("Problem in myWriter filewriter!");
                    }
                }

                setName(userName);
                setEmail(userEmail);

                System.out.println("\nUpdate profile sucessfully!");

            }

        }

    }

    public static boolean logoutAcc() {
        System.out.println("\nLogout sucessfully...");

        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", password=" + password + ", email=" + email + ", name=" + name + '}';
    }

}
