package eventmanagementsystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventManagementSystem {

    public static void main(String[] args) {
        boolean isMenuOptionInvalid = false, logoutAcc = false;
        String option;
        String[] userInfo;

        Scanner scanner = new Scanner(System.in);

        try {
            AvailableEventList.updateLatestRecord();
            RegisteredEventList.updateLatestRecord();
        } catch (IOException ex) {
            Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("There is IOException");
        } catch (ParseException ex) {
            Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("There is ParseException");
        }

        System.out.println("\nWelcome to ABC birthday event management system! \n");

        do {
            User[] users = new User[2];
            String useridInput, passwordInput, passwordAgainInput, userName, userEmail, userCode;
            isMenuOptionInvalid = false;
            userInfo = null;
            logoutAcc = false;

            System.out.println();
            System.out.println();
            System.out.println("=====================================");
            System.out.println("|                                   |");
            System.out.println("|             Home Menu             |");
            System.out.println("|                                   |");
            System.out.println("=====================================\n");

            System.out.print("Please select options below: \n"
                    + "1. Register as new attendee\n"
                    + "2. Login as existing attendee\n"
                    + "3. Register as new event organizer\n"
                    + "4. Login as existing event organizer\n"
                    + "5. Forgot password\n"
                    + "6. Exit program\n\n"
                    + "Notice:\n"
                    + "a. Enter [1-6] to proceed \n"
                    + "-------------------------------------\n"
                    + "Enter your option: ");

            option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println();
                    System.out.println();
                    System.out.println("=====================================");
                    System.out.println("|                                   |");
                    System.out.println("|               Sign Up             |");
                    System.out.println("|              (Attendee)           |");
                    System.out.println("|                                   |");
                    System.out.println("=====================================\n");
                    System.out.println("Notice:\n"
                            + "a. Enter [-9999] to cancel process \n"
                            + "b. Only accept alphabet and/or digit for user id and password input\n"
                            + "c. Only accept whitespace and alphabet for user name input\n"
                            + "d. Only accept standard format for email input\n"
                            + "e. Input is case sensitive\n"
                            + "-------------------------------------");

                    System.out.print("Enter your user id: ");
                    useridInput = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    passwordInput = scanner.nextLine();
                    System.out.print("Enter your password again: ");
                    passwordAgainInput = scanner.nextLine();
                    System.out.print("Enter your full name: ");
                    userName = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    userEmail = scanner.nextLine();

                    if (useridInput.contains("-9999") || passwordInput.contains("-9999") || passwordAgainInput.contains("-9999") || userName.contains("-9999") || userEmail.contains("-9999")) {
                        System.out.println("\nExiting current process...\n");
                        break;
                    }

                    try {
                        userInfo = User.registration(useridInput, passwordInput, passwordAgainInput, userName, userEmail, Database.getATTENDEE());
                    } catch (FileNotFoundException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nRegister unsucessfully! Please try again...");

                    } catch (IOException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nFile I/O failure! Please try again...");
                    }

                    if (userInfo != null) {
                        users[0] = new Attendee(userInfo);

                        for (User user : users) {
                            if (user instanceof Attendee) {
                                user.showRegisterSuccessMsg();
                            }
                        }
                    }

                    break;
                case "2":
                    System.out.println();
                    System.out.println();
                    System.out.println("=====================================");
                    System.out.println("|                                   |");
                    System.out.println("|                Login              |");
                    System.out.println("|              (Attendee)           |");
                    System.out.println("|                                   |");
                    System.out.println("=====================================\n");
                    System.out.println("Notice:\n"
                            + "a. Enter [-9999] to cancel process \n"
                            + "b. Only accept alphabet and/or digit for user id and password input\n"
                            + "c. Input is case sensitive\n"
                            + "-------------------------------------");

                    System.out.print("Enter your user id: ");
                    useridInput = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    passwordInput = scanner.nextLine();

                    if (useridInput.contains("-9999") || passwordInput.contains("-9999")) {
                        System.out.println("\nExiting current process...\n");
                        break;
                    }

                    try {
                        userInfo = User.login(useridInput, passwordInput, Database.getATTENDEE());
                    } catch (FileNotFoundException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nLogin unsucessfully! Please try again...");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nLogin unsucessfully! Please try again...");

                    }

                    if (userInfo != null) {
                        users[0] = new Attendee(userInfo);

                        for (User user : users) {
                            if (user instanceof Attendee) {
                                user.showLoginSuccessMsg();
                            }
                        }
                    } else {
                        System.out.println("\nError occur...Try again later");
                    }

                    break;

                case "3":
                    System.out.println();
                    System.out.println();
                    System.out.println("=====================================");
                    System.out.println("|                                   |");
                    System.out.println("|              Sign Up              |");
                    System.out.println("|          (Event Organizer)        |");
                    System.out.println("|                                   |");
                    System.out.println("=====================================\n");

                    System.out.println("Notice:\n"
                            + "a. Enter [-9999] to cancel process \n"
                            + "b. Only accept alphabet and/or digit for user id and password input\n"
                            + "c. Only accept whitespace and alphabet for user name input\n"
                            + "d. Only accept standard format for email input\n"
                            + "e. Input is case sensitive\n"
                            + "-------------------------------------");

                    System.out.print("Enter your user id: ");
                    useridInput = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    passwordInput = scanner.nextLine();
                    System.out.print("Enter your password again: ");
                    passwordAgainInput = scanner.nextLine();
                    System.out.print("Enter your full name: ");
                    userName = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    userEmail = scanner.nextLine();

                    if (useridInput.contains("-9999") || passwordInput.contains("-9999") || passwordAgainInput.contains("-9999") || userName.contains("-9999") || userEmail.contains("-9999")) {
                        System.out.println("\nExiting current process...\n");
                        break;
                    }

                    try {
                        userInfo = User.registration(useridInput, passwordInput, passwordAgainInput, userName, userEmail, Database.getEVENTORGANIZER());
                    } catch (FileNotFoundException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nRegister unsucessfully! Please try again...");

                    } catch (IllegalArgumentException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nRegister unsucessfully! Please try again...");
                    } catch (IOException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nFile I/O failure! Please try again...");
                    }

                    if (userInfo != null) {
                        users[1] = new EventOrganizer(userInfo);

                        for (User user : users) {
                            if (user instanceof EventOrganizer) {
                                user.showRegisterSuccessMsg();
                            }
                        }
                    }

                    break;
                case "4":
                    System.out.println();
                    System.out.println();
                    System.out.println("=====================================");
                    System.out.println("|                                   |");
                    System.out.println("|                Login              |");
                    System.out.println("|          (Event Organizer)        |");
                    System.out.println("|                                   |");
                    System.out.println("=====================================\n");

                    System.out.println("Notice:\n"
                            + "a. Enter [-9999] to cancel process \n"
                            + "b. Only accept alphabet and/or digit for user id and password input\n"
                            + "c. Input is case sensitive\n"
                            + "-------------------------------------");

                    System.out.print("Enter your user id: ");
                    useridInput = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    passwordInput = scanner.nextLine();

                    if (useridInput.contains("-9999") || passwordInput.contains("-9999")) {
                        System.out.println("\nExiting current process...\n");
                        break;
                    }

                    try {
                        userInfo = User.login(useridInput, passwordInput, Database.getEVENTORGANIZER());
                    } catch (FileNotFoundException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nLogin unsucessfully! Please try again...");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nLogin unsucessfully! Please try again...");
                    }

                    if (userInfo != null) {
                        users[1] = new EventOrganizer(userInfo);

                        for (User user : users) {
                            if (user instanceof EventOrganizer) {
                                user.showLoginSuccessMsg();
                            }
                        }
                    } else {
                        System.out.println("\nError occur...Try again later");
                    }

                    break;

                case "5":
                    System.out.println();
                    System.out.println();
                    System.out.println("=====================================");
                    System.out.println("|                                   |");
                    System.out.println("|          Forgot Password          |");
                    System.out.println("|                                   |");
                    System.out.println("=====================================\n");
                    System.out.println("\nNotice:\n"
                            + "a. Enter [-9999] to cancel process \n"
                            + "b. Only accept standard format for email input\n"
                            + "c. Input is case sensitive\n"
                            + "-------------------------------------");

                    System.out.print("Enter [1] for attendee, [2] for event organizer: ");
                    userCode = scanner.nextLine();
                    System.out.print("Enter your email to receive recovery code and reset password: ");
                    userEmail = scanner.nextLine();

                    if (userEmail.contains("-9999") || userCode.contains("-9999")) {
                        System.out.println("\nExiting current process...\n");
                        break;
                    }

                    try {
                        User.forgotPassword(userEmail, scanner, userCode);
                    } catch (FileNotFoundException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nLogin unsucessfully! Please try again...");
                    } catch (IOException ex) {
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nFile I/O failure! Please try again...");
                    }

                    isMenuOptionInvalid = true;

                    break;
                case "6":
                    System.out.println("Exiting program now...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select option above only. Try again...");
                    isMenuOptionInvalid = true;
            }

            if (users[0] instanceof Attendee) {
                boolean isAttendeeMenuOptInvalid = false;
                Attendee attendee = (Attendee) users[0];

                do {

                    String message, eventId, numParticipants, bankNo, cvc, expiryDate;
                    isAttendeeMenuOptInvalid = false;

                    try {
                        attendee.getAvailableEventList().addRecord();
                        attendee.getRegisteredEventList().addRecord(attendee.getUserId());

                    } catch (FileNotFoundException ex) {
                        System.out.println("There is FileNotFoundException");
                        System.out.println("The message of the exception is: " + ex.getMessage());
                        System.out.println("\nPlease try again...");
                    }

                    System.out.println();
                    System.out.println();
                    System.out.println("=====================================");
                    System.out.println("|                                   |");
                    System.out.println("|           Attendee Menu           |");
                    System.out.println("|                                   |");
                    System.out.println("=====================================\n");

                    System.out.print(
                            "Hello " + attendee.getName() + "! Please select options below: \n"
                            + "1. View all available birthday events (sort by date)\n"
                            + "2. View all available birthday events (sort by price)\n"
                            + "3. Search event by id\n"
                            + "4. Register an birthday event\n"
                            + "5. View personal information (ID, name, email, registered events) \n"
                            + "6. Update personal information (name, email)\n"
                            + "7. View message inbox\n"
                            + "8. Send message to customer service\n"
                            + "9. Logout\n\n"
                            + "Notice:\n"
                            + "a. Enter [1-9] to proceed \n"
                            + "-------------------------------------\n"
                            + "Enter your option: ");

                    option = scanner.nextLine();

                    switch (option) {
                        case "1":
                            System.out.println();
                            System.out.println();
                            System.out.println("==================================================");
                            System.out.println("|                                                |");
                            System.out.println("|       View All Available Birthday Events       |");
                            System.out.println("|                 (Sort By Date)                 |");
                            System.out.println("|                                                |");
                            System.out.println("==================================================\n");
                            attendee.getAvailableEventList().viewAllRecordByDate();
                            System.out.println("\n\nReturning to attendee menu...\n\n");
                            isAttendeeMenuOptInvalid = true;
                            break;
                        case "2":
                            System.out.println();
                            System.out.println();
                            System.out.println("==================================================");
                            System.out.println("|                                                |");
                            System.out.println("|       View All Available Birthday Events       |");
                            System.out.println("|                 (Sort By Price)                |");
                            System.out.println("|                                                |");
                            System.out.println("==================================================\n");
                            attendee.getAvailableEventList().viewAllRecordByPrice();
                            System.out.println("\n\nReturning to attendee menu...\n\n");
                            isAttendeeMenuOptInvalid = true;
                            break;
                        case "3":
                            System.out.println();
                            System.out.println();
                            System.out.println("=========================================");
                            System.out.println("|                                       |");
                            System.out.println("|        Search Event By Event ID       |");
                            System.out.println("|                                       |");
                            System.out.println("=========================================\n");

                            System.out.println("Notice:\n"
                                    + "a. Enter [-9999] to cancel process \n"
                                    + "b. Enter valid event ID \n"
                                    + "-------------------------------------");

                            System.out.print("Enter your event ID: ");
                            eventId = scanner.nextLine();

                            if (eventId.contains("-9999")) {
                                System.out.println("\nExiting current process...\n");
                                isAttendeeMenuOptInvalid = true;
                                break;
                            }

                            attendee.getAvailableEventList().searchEventById(eventId);

                            System.out.println("\n\nReturning to attendee menu...\n\n");

                            isAttendeeMenuOptInvalid = true;
                            break;
                        case "4":
                            System.out.println();
                            System.out.println();
                            System.out.println("===============================");
                            System.out.println("|                             |");
                            System.out.println("|        Register Event       |");
                            System.out.println("|                             |");
                            System.out.println("===============================\n");

                            System.out.println("Notice:\n"
                                    + "a. Enter [-9999] to cancel process \n"
                                    + "b. Only accept addon number of participant that not exceed max capacity of the event\n"
                                    + "c. Only accept available event ID \n"
                                    + "d. Number of participants must more than 0 \n"
                                    + "e. Total price will be charged to attendee based on number of participants * event ticket unit price\n"
                                    + "-------------------------------------");

                            System.out.print("Enter event Id: ");
                            eventId = scanner.nextLine();
                            System.out.print("Enter number of participants: ");
                            numParticipants = scanner.nextLine();
                            System.out.print("Enter your bank card no. (8 digits): ");
                            bankNo = scanner.nextLine();
                            System.out.print("Enter card CVC (3 digits): ");
                            cvc = scanner.nextLine();
                            System.out.print("Enter card expiry date (MM/YY): ");
                            expiryDate = scanner.nextLine();

                            if (eventId.contains("-9999") || numParticipants.contains("-9999") || bankNo.contains("-9999") || cvc.contains("-9999") || expiryDate.contains("-9999")) {
                                System.out.println("\nExiting current process...\n");
                                isAttendeeMenuOptInvalid = true;
                                break;
                            }

                            if (!InputValidation.payment(bankNo, cvc, expiryDate, eventId, numParticipants)) {
                                System.out.println("\nInvalid inputs...Please try again");
                                System.out.println("\n\nReturning to attendee menu...\n\n");
                                isAttendeeMenuOptInvalid = true;
                                break;
                            }

                            try {
                                attendee.registerEvent(eventId, numParticipants, attendee.getAvailableEventList().getAvailableEvents());
                            } catch (IOException ex) {
                                System.out.println("There is IOException");
                                Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            System.out.println("\n\nReturning to attendee menu...\n\n");

                            isAttendeeMenuOptInvalid = true;
                            break;

                        case "5":
                            System.out.println();
                            System.out.println();
                            System.out.println("=========================================");
                            System.out.println("|                                       |");
                            System.out.println("|       View Personal Information       |");
                            System.out.println("|                                       |");
                            System.out.println("=========================================\n");

                            System.out.println("ID: " + attendee.getUserId());
                            System.out.println("Name: " + attendee.getName());
                            System.out.println("Email: " + attendee.getEmail());
                            System.out.println("\nRegistered Events:");
                            attendee.getRegisteredEventList().viewAllRecordByDate();

                            System.out.println("\n\nReturning to attendee menu...\n\n");

                            isAttendeeMenuOptInvalid = true;
                            break;
                        case "6":
                            System.out.println();
                            System.out.println();
                            System.out.println("==========================================");
                            System.out.println("|                                        |");
                            System.out.println("|       Update Personal Information      |");
                            System.out.println("|                                        |");
                            System.out.println("==========================================\n");

                            System.out.println("Notice:\n"
                                    + "a. Enter [-9999] to cancel process \n"
                                    + "b. Only accept whitespace and alphabet for name input\n"
                                    + "c. Only accept standard format for email input\n"
                                    + "d. Input is case sensitive\n"
                                    + "-------------------------------------");

                            System.out.print("Enter new full name: ");
                            userName = scanner.nextLine();
                            System.out.print("Enter new email: ");
                            userEmail = scanner.nextLine();

                            if (userName.contains("-9999") || userEmail.contains("-9999")) {
                                System.out.println("\nExiting current process...\n");
                                isAttendeeMenuOptInvalid = true;
                                break;
                            }

                            try {

                                attendee.updateProfile(userName, userEmail, Database.getATTENDEE());

                            } catch (FileNotFoundException ex) {
                                System.out.println("The message of the exception is: " + ex.getMessage());
                                System.out.println("\nRegister unsucessfully! Please try again...");

                            } catch (IllegalArgumentException ex) {
                                System.out.println("The message of the exception is: " + ex.getMessage());
                                System.out.println("\nRegister unsucessfully! Please try again...");
                            } catch (IOException ex) {
                                System.out.println("The message of the exception is: " + ex.getMessage());
                                System.out.println("\nFile I/O failure! Please try again...");
                            }

                            System.out.println("\n\nReturning to attendee menu...\n\n");

                            isAttendeeMenuOptInvalid = true;

                            break;
                        case "7":
                            System.out.println();
                            System.out.println();
                            System.out.println("=============================================");
                            System.out.println("|                                           |");
                            System.out.println("|              View Message Inbox           |");
                            System.out.println("|                                           |");
                            System.out.println("=============================================\n");

                            try {
                                attendee.viewMessage(attendee.getUserId());
                            } catch (FileNotFoundException ex) {
                                System.out.println("There is FileNotFoundException");
                                Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            System.out.println("\n\nReturning to attendee menu...\n\n");
                            isAttendeeMenuOptInvalid = true;

                            break;

                        case "8":
                            System.out.println();
                            System.out.println();
                            System.out.println("============================================");
                            System.out.println("|                                          |");
                            System.out.println("|                Send Message              |");
                            System.out.println("|            to Customer Service           |");
                            System.out.println("|                                          |");
                            System.out.println("============================================\n");

                            System.out.println("\nNotice:\n"
                                    + "a. Enter [-9999] to cancel process \n"
                                    + "b. Only accept alphabet and/or digits and/or [.,!? ] as message input\n"
                                    + "-------------------------------------");
                            System.out.print("Enter message: ");
                            message = scanner.nextLine();
                            System.out.println();

                            if (message.contains("-9999")) {
                                System.out.println("\nExiting current process...\n");
                                isAttendeeMenuOptInvalid = true;
                                break;
                            }

                            if (InputValidation.message(message)) {
                                System.out.println("Message sent sucessfully!");
                            } else {
                                System.out.println("Error occur...Try again later");
                            }

                            System.out.println("\n\nReturning to attendee menu...\n\n");
                            isAttendeeMenuOptInvalid = true;
                            break;
                        case "9":
                            logoutAcc = User.logoutAcc();
                            users[0] = null;
                            break;
                        default:
                            System.out.println("Please select option above only. Try again...");
                            isAttendeeMenuOptInvalid = true;
                    }

                } while (isAttendeeMenuOptInvalid == true);
            }

            if (users[1] instanceof EventOrganizer) {
                boolean isEventOrganizerMenuOptInvalid = false;
                boolean isEventOrganizerSubMenuOptInvalid = false;
                EventOrganizer eventOrganizer = (EventOrganizer) users[1];

                do {
                    String attendeeEventId, eventStatusInput, userIdInput, eventIdInput, message, numParticipant, supplierName;
                    isEventOrganizerMenuOptInvalid = false;

                    try {
                        eventOrganizer.getCreatedEventList().addRecord(eventOrganizer.getUserId());
                        eventOrganizer.getEve_AttendeeList().addRecord(eventOrganizer.getCreatedEventList());
                    } catch (FileNotFoundException ex) {
                        System.out.println("There is FileNotFoundException");
                        Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println();
                    System.out.println();
                    System.out.println("=====================================");
                    System.out.println("|                                   |");
                    System.out.println("|        Event Organizer Menu       |");
                    System.out.println("|                                   |");
                    System.out.println("=====================================\n");

                    System.out.print(
                            "Hello " + eventOrganizer.getName() + "! Please select options below: \n"
                            + "1. Create event\n"
                            + "2. Event monitoring\n"
                            + "3. Sourcing management\n"
                            + "4. Attendee management \n"
                            + "5. Reporting & analytic \n"
                            + "6. Update personal information (name, email)\n"
                            + "7. View personal information (ID, name, email)\n"
                            + "8. Logout\n\n"
                            + "Notice:\n"
                            + "a. Enter [1-8] to proceed \n"
                            + "b. You need to have minimum 1 supplier record for each category in order to successfully create event \n"
                            + "-------------------------------------\n"
                            + "Enter your option: ");

                    option = scanner.nextLine();

                    switch (option) {
                        case "1":
                            boolean isAllSupplierExist = true;
                            try {
                                eventOrganizer.getSupplierList().addRecord(eventOrganizer.getUserId());
                            } catch (FileNotFoundException ex) {
                                System.out.println("There is FileNotFoundException");
                                Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (isAllSupplierExist == false) {
                                System.out.println("Fail to create event because missing supplier records. Try again after adding supplier records...");
                            } else {
                                String eventName, eventDate, eventPrice, eventMaxCapacity, supplierIdFoodDrink, supplierIdVenue, supplierIdStaff;
                                System.out.println();
                                System.out.println();
                                System.out.println("==============================");
                                System.out.println("|                            |");
                                System.out.println("|         Create Event       |");
                                System.out.println("|                            |");
                                System.out.println("==============================\n");

                                System.out.println("\nNotice:\n"
                                        + "a. Enter [-9999] to cancel process \n"
                                        + "b. Enter valid input for supplier ID on each category\n"
                                        + "c. Enter alphabet and/or digit for eventName input\n"
                                        + "d. Enter valid input for eventDate (dd-mm-yyyy) input\n"
                                        + "e. Enter digit only for price & max capacity input\n"
                                        + "f. Price input accept decimal or whole number\n"
                                        + "-------------------------------------");
                                System.out.print("Enter event name: ");
                                eventName = scanner.nextLine();
                                System.out.print("Enter event date: ");
                                eventDate = scanner.nextLine();
                                System.out.print("Enter price per person: RM ");
                                eventPrice = scanner.nextLine();
                                System.out.print("Enter max capacity of people can joined: ");
                                eventMaxCapacity = scanner.nextLine();
                                System.out.print("Enter supplier id for responsible in food drink: ");
                                supplierIdFoodDrink = scanner.nextLine();
                                System.out.print("Enter supplier id for responsible in venue: ");
                                supplierIdVenue = scanner.nextLine();
                                System.out.print("Enter supplier id for responsible in staffing: ");
                                supplierIdStaff = scanner.nextLine();

                                if (eventName.contains("-9999") || eventDate.contains("-9999") || eventPrice.contains("-9999") || eventMaxCapacity.contains("-9999") || supplierIdFoodDrink.contains("-9999") || supplierIdVenue.contains("-9999") || supplierIdStaff.contains("-9999")) {
                                    System.out.println("\nExiting current process...\n");
                                    isEventOrganizerMenuOptInvalid = true;
                                    break;
                                }

                                if (InputValidation.createEvent(eventName, eventDate, eventPrice, eventMaxCapacity)) {
                                    try {
                                        eventOrganizer.createEvent(eventName, eventDate, eventPrice, eventMaxCapacity, supplierIdFoodDrink, supplierIdVenue, supplierIdStaff);
                                    } catch (IOException ex) {
                                        System.out.println("There is IOException");
                                        Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ParseException ex) {
                                        System.out.println("There is ParseException");
                                        Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    System.out.println("\nInvalid input...Try again!");
                                }

                            }

                            isEventOrganizerMenuOptInvalid = true;
                            break;

                        case "2":

                            do {
                                isEventOrganizerSubMenuOptInvalid = false;

                                try {
                                    eventOrganizer.getCreatedEventList().addRecord(eventOrganizer.getUserId());
                                } catch (FileNotFoundException ex) {
                                    System.out.println("There is FileNotFoundException");
                                    Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                System.out.println();
                                System.out.println();
                                System.out.println("=====================================");
                                System.out.println("|                                   |");
                                System.out.println("|        Event Monitoring Menu      |");
                                System.out.println("|                                   |");
                                System.out.println("=====================================\n");

                                System.out.print(
                                        "Please select options below: \n"
                                        + "1. View all events created (sort by date)\n"
                                        + "2. Cancel event\n"
                                        + "\nNotice:\n"
                                        + "a. Enter [1-2] to proceed \n"
                                        + "b. Enter [B] to back \n"
                                        + "c. Only event with \"Normal\" status can be cancelled\n"
                                        + "-------------------------------------\n"
                                        + "Enter your option: ");

                                option = scanner.nextLine();

                                switch (option) {
                                    case "1":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|               View All Events             |");
                                        System.out.println("|                (Sort by Date)             |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");
                                        eventOrganizer.getCreatedEventList().viewAllRecordByDate();
                                        System.out.println("\n\nReturning to event monitoring menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "2":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("==============================");
                                        System.out.println("|                            |");
                                        System.out.println("|         Cancel Event       |");
                                        System.out.println("|                            |");
                                        System.out.println("==============================\n");

                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "b. Enter valid input for event ID\n"
                                                + "c. Only event with \"Normal\" status can be cancel\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter event ID: ");
                                        eventIdInput = scanner.nextLine();
                                        System.out.println();

                                        if (eventIdInput.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        try {
                                            eventOrganizer.getCreatedEventList().cancelRecord(eventIdInput, eventOrganizer.getUserId(), eventOrganizer.getEve_AttendeeList());
                                        } catch (IOException ex) {
                                            System.out.println("There is IOException");
                                            Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        System.out.println("\n\nReturning to event monitoring menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;

                                        break;

                                    case "b", "B":
                                        isEventOrganizerMenuOptInvalid = true;
                                        break;
                                    default:
                                        System.out.println("Please select option above only. Try again...");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                }
                            } while (isEventOrganizerSubMenuOptInvalid == true);
                            break;
                        case "3":
                            do {
                                try {
                                    eventOrganizer.getSupplierList().addRecord(eventOrganizer.getUserId());
                                } catch (FileNotFoundException ex) {
                                    System.out.println("There is FileNotFoundException");
                                    Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                String categoryInput, companyName, email, pricePerPax, foodDrinkDescription, location, pricePerDay, maxCapacity, supplierId;
                                isEventOrganizerSubMenuOptInvalid = false;

                                System.out.println();
                                System.out.println();
                                System.out.println("=====================================");
                                System.out.println("|                                   |");
                                System.out.println("|      Sourcing Management Menu     |");
                                System.out.println("|                                   |");
                                System.out.println("=====================================\n");

                                System.out.print(
                                        "Please select options below: \n"
                                        + "1. View all suppliers\n"
                                        + "2. View all suppliers by type\n"
                                        + "3. Add supplier records\n"
                                        + "4. Delete supplier records\n"
                                        + "5. Edit supplier records\n"
                                        + "Notice:\n"
                                        + "a. Enter [1-5] to proceed \n"
                                        + "b. Enter [B] to back \n"
                                        + "-------------------------------------\n"
                                        + "Enter your option: ");

                                option = scanner.nextLine();

                                switch (option) {
                                    case "1":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|              View All Suppliers           |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");
                                        eventOrganizer.getSupplierList().viewAllRecord();
                                        System.out.println("\n\nReturning to sourcing management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "2":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|              View All Suppliers           |");
                                        System.out.println("|                 (By Category)             |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");
                                        System.out.println("Select category below:");
                                        System.out.println("1. Food and drink");
                                        System.out.println("2. Venue");
                                        System.out.println("3. Staff");
                                        System.out.println("\nNotice:\n"
                                                + "a. Enter wrong input will return to previous page \n"
                                                + "b. Enter [1-3] to proceed \n"
                                                + "c. Wrong input will have empty result display\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter your option: ");
                                        categoryInput = scanner.nextLine();
                                        System.out.println();

                                        if (categoryInput.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }
                                        eventOrganizer.getSupplierList().viewAllRecordByCategory(categoryInput);
                                        System.out.println("\n\nReturning to sourcing management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "3":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|             Add Supplier Record           |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");
                                        System.out.println("Select category below:");
                                        System.out.println("1. Food and drink");
                                        System.out.println("2. Venue");
                                        System.out.println("3. Staff");
                                        System.out.println("\nNotice:\n"
                                                + "a. Enter wrong input will return to previous page \n"
                                                + "b. Enter [1-3] to proceed \n"
                                                + "-------------------------------------");
                                        System.out.print("Enter your option: ");
                                        categoryInput = scanner.nextLine();
                                        System.out.println();

                                        if (categoryInput.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        if (categoryInput.equals("1")) {
                                            System.out.println("\nNotice:\n"
                                                    + "a. Enter [-9999] to cancel process \n"
                                                    + "b. Enter valid input for email\n"
                                                    + "c. Enter digits and/or alphabets for name and description\n"
                                                    + "d. Enter digit for price\n"
                                                    + "-------------------------------------");
                                            System.out.print("Enter company name: ");
                                            companyName = scanner.nextLine();
                                            System.out.print("Enter email: ");
                                            email = scanner.nextLine();
                                            System.out.print("Enter price per pax: RM ");
                                            pricePerPax = scanner.nextLine();
                                            System.out.print("Enter food drink description: ");
                                            foodDrinkDescription = scanner.nextLine();
                                            System.out.println();

                                            if (companyName.contains("-9999") || email.contains("-9999") || pricePerPax.contains("-9999") || foodDrinkDescription.contains("-9999")) {
                                                System.out.println("\nExiting current process...\n");
                                                isEventOrganizerSubMenuOptInvalid = true;
                                                break;
                                            }

                                            if (InputValidation.addSupplier(companyName, email, pricePerPax, foodDrinkDescription)) {
                                                try {
                                                    eventOrganizer.getSupplierList().addSupplier(eventOrganizer.getUserId(), foodDrinkDescription, pricePerPax, companyName, email);
                                                } catch (IOException ex) {
                                                    System.out.println("There is IOException");
                                                    Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            } else {
                                                System.out.println("\nWrong input...Try again later");
                                            }

                                        } else if (categoryInput.equals("2")) {
                                            System.out.println("\nNotice:\n"
                                                    + "a. Enter [-9999] to cancel process \n"
                                                    + "b. Enter valid input for email\n"
                                                    + "c. Enter digits and/or alphabets for name and description\n"
                                                    + "d. Enter digit for price and max capacity\n"
                                                    + "e. Enter digits and/or alphabets and/or [,. ] for location\n"
                                                    + "-------------------------------------");
                                            System.out.print("Enter company name: ");
                                            companyName = scanner.nextLine();
                                            System.out.print("Enter email: ");
                                            email = scanner.nextLine();
                                            System.out.print("Enter location: ");
                                            location = scanner.nextLine();
                                            System.out.print("Enter price per day: ");
                                            pricePerDay = scanner.nextLine();
                                            System.out.print("Enter max capacity: ");
                                            maxCapacity = scanner.nextLine();
                                            System.out.println();

                                            if (companyName.contains("-9999") || email.contains("-9999") || location.contains("-9999") || pricePerDay.contains("-9999") || maxCapacity.contains("-9999")) {
                                                System.out.println("\nExiting current process...\n");
                                                isEventOrganizerSubMenuOptInvalid = true;
                                                break;
                                            }

                                            if (InputValidation.addSupplier(companyName, email, location, pricePerDay, maxCapacity)) {
                                                try {
                                                    eventOrganizer.getSupplierList().addSupplier(eventOrganizer.getUserId(), companyName, email, location, pricePerDay, maxCapacity);
                                                } catch (IOException ex) {
                                                    System.out.println("There is IOException");
                                                    Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            } else {
                                                System.out.println("\nWrong input...Try again later");
                                            }

                                        } else if (categoryInput.equals("3")) {
                                            System.out.println("\nNotice:\n"
                                                    + "a. Enter [-9999] to cancel process \n"
                                                    + "b. Enter valid input for email\n"
                                                    + "c. Enter digits and/or alphabets for name and description\n"
                                                    + "d. Enter digit for price\n"
                                                    + "-------------------------------------");
                                            System.out.print("Enter company name: ");
                                            companyName = scanner.nextLine();
                                            System.out.print("Enter email: ");
                                            email = scanner.nextLine();
                                            System.out.print("Enter price per day: ");
                                            pricePerDay = scanner.nextLine();
                                            System.out.println();

                                            if (companyName.contains("-9999") || email.contains("-9999") || pricePerDay.contains("-9999")) {
                                                System.out.println("\nExiting current process...\n");
                                                isEventOrganizerSubMenuOptInvalid = true;
                                                break;
                                            }

                                            if (InputValidation.addSupplier(companyName, email, pricePerDay)) {
                                                try {
                                                    eventOrganizer.getSupplierList().addSupplier(eventOrganizer.getUserId(), companyName, email, pricePerDay);
                                                } catch (IOException ex) {
                                                    System.out.println("There is IOException");
                                                    Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            } else {
                                                System.out.println("\nWrong input...Try again later");
                                            }

                                        }

                                        System.out.println("\n\nReturning to sourcing management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "4":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|         Remove Supplier From Record       |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");

                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "b. Enter valid input for supplier ID\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter supplier ID: ");
                                        supplierId = scanner.nextLine();
                                        System.out.println();

                                        if (supplierId.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        try {
                                            eventOrganizer.getSupplierList().removeRecord(supplierId, eventOrganizer.getUserId());
                                        } catch (IOException ex) {
                                            System.out.println("There is IOException");
                                            Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        System.out.println("\n\nReturning to sourcing management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;

                                    case "5":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|            Edit Supplier Record           |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");
                                        System.out.println("Select category below:");
                                        System.out.println("1. Food and drink");
                                        System.out.println("2. Venue");
                                        System.out.println("3. Staff");
                                        System.out.println("\nNotice:\n"
                                                + "a. Enter wrong input will return to previous page \n"
                                                + "b. Enter [1-3] to proceed \n"
                                                + "-------------------------------------");
                                        System.out.print("Enter your option: ");
                                        categoryInput = scanner.nextLine();
                                        System.out.println();

                                        if (categoryInput.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        if (categoryInput.equals("1")) {
                                            System.out.println("\nNotice:\n"
                                                    + "a. Enter [-9999] to cancel process \n"
                                                    + "b. Enter valid input for email and supplier id\n"
                                                    + "c. Enter digits and/or alphabets for name and description\n"
                                                    + "d. Enter digit for price\n"
                                                    + "-------------------------------------");
                                            System.out.print("Enter supplier id: ");
                                            supplierId = scanner.nextLine();
                                            System.out.print("Enter supplier name: ");
                                            supplierName = scanner.nextLine();
                                            System.out.print("Enter supplier email: ");
                                            email = scanner.nextLine();
                                            System.out.print("Enter food drink description: ");
                                            foodDrinkDescription = scanner.nextLine();
                                            System.out.print("Enter price per pax: RM ");
                                            pricePerPax = scanner.nextLine();
                                            System.out.println();

                                            if (supplierId.contains("-9999") || supplierName.contains("-9999") || email.contains("-9999") || foodDrinkDescription.contains("-9999") || pricePerPax.contains("-9999")) {
                                                System.out.println("\nExiting current process...\n");
                                                isEventOrganizerSubMenuOptInvalid = true;
                                                break;
                                            }

                                            if (InputValidation.editSupplier(supplierName, email, foodDrinkDescription, pricePerPax)) {
                                                try {
                                                    eventOrganizer.getSupplierList().editRecord(eventOrganizer.getUserId(), supplierId, supplierName, email, foodDrinkDescription, pricePerPax);
                                                } catch (IOException ex) {
                                                    System.out.println("There is IOException");
                                                    Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            } else {
                                                System.out.println("\nWrong input...Try again later");
                                            }
                                        } else if (categoryInput.equals("2")) {
                                            System.out.println("\nNotice:\n"
                                                    + "a. Enter [-9999] to cancel process \n"
                                                    + "b. Enter valid input for email and supplier id\n"
                                                    + "c. Enter digits and/or alphabets for name and description\n"
                                                    + "d. Enter digit for price and capacity\n"
                                                    + "e. Enter digits and/or alphabets and/or [,. ] for location\n"
                                                    + "-------------------------------------");
                                            System.out.print("Enter supplier id: ");
                                            supplierId = scanner.nextLine();
                                            System.out.print("Enter supplier name: ");
                                            supplierName = scanner.nextLine();
                                            System.out.print("Enter supplier email: ");
                                            email = scanner.nextLine();
                                            System.out.print("Enter location: ");
                                            location = scanner.nextLine();
                                            System.out.print("Enter price per day: RM ");
                                            pricePerDay = scanner.nextLine();
                                            System.out.print("Enter max capacity: ");
                                            maxCapacity = scanner.nextLine();
                                            System.out.println();

                                            if (supplierId.contains("-9999") || supplierName.contains("-9999") || email.contains("-9999") || location.contains("-9999") || pricePerDay.contains("-9999") || maxCapacity.contains("-9999")) {
                                                System.out.println("\nExiting current process...\n");
                                                isEventOrganizerSubMenuOptInvalid = true;
                                                break;
                                            }

                                            if (InputValidation.editSupplier(supplierName, email, location, pricePerDay, maxCapacity)) {
                                                try {
                                                    eventOrganizer.getSupplierList().editRecord(eventOrganizer.getUserId(), supplierId, supplierName, email, location, pricePerDay, maxCapacity);
                                                } catch (IOException ex) {
                                                    System.out.println("There is IOException");
                                                    Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            } else {
                                                System.out.println("\nWrong input...Try again later");
                                            }
                                        } else if (categoryInput.equals("3")) {
                                            System.out.println("\nNotice:\n"
                                                    + "a. Enter [-9999] to cancel process \n"
                                                    + "b. Enter valid input for email and supplier id\n"
                                                    + "c. Enter digits and/or alphabets for name\n"
                                                    + "d. Enter digit for price\n"
                                                    + "-------------------------------------");
                                            System.out.print("Enter supplier id: ");
                                            supplierId = scanner.nextLine();
                                            System.out.print("Enter supplier name: ");
                                            supplierName = scanner.nextLine();
                                            System.out.print("Enter supplier email: ");
                                            email = scanner.nextLine();
                                            System.out.print("Enter price per day: RM ");
                                            pricePerDay = scanner.nextLine();
                                            System.out.println();

                                            if (supplierId.contains("-9999") || supplierName.contains("-9999") || email.contains("-9999") || pricePerDay.contains("-9999")) {
                                                System.out.println("\nExiting current process...\n");
                                                isEventOrganizerSubMenuOptInvalid = true;
                                                break;
                                            }

                                            if (InputValidation.editSupplier(supplierName, email, pricePerDay)) {
                                                try {
                                                    eventOrganizer.getSupplierList().editRecord(eventOrganizer.getUserId(), supplierId, supplierName, email, pricePerDay);
                                                } catch (IOException ex) {
                                                    System.out.println("There is IOException");
                                                    Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            } else {
                                                System.out.println("\nWrong input...Try again later");
                                            }
                                        }

                                        System.out.println("\n\nReturning to sourcing management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "b", "B":
                                        isEventOrganizerMenuOptInvalid = true;
                                        break;
                                    default:
                                        System.out.println("Please select option above only. Try again...");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                }

                            } while (isEventOrganizerSubMenuOptInvalid == true);
                            break;

                        case "4":
                            do {
                                try {
                                    eventOrganizer.getCreatedEventList().addRecord(eventOrganizer.getUserId());
                                    eventOrganizer.getEve_AttendeeList().addRecord(eventOrganizer.getCreatedEventList());
                                } catch (FileNotFoundException ex) {
                                    System.out.println("There is FileNotFoundException");
                                    Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                isEventOrganizerSubMenuOptInvalid = false;

                                System.out.println();
                                System.out.println();
                                System.out.println("=====================================");
                                System.out.println("|                                   |");
                                System.out.println("|      Attendee Management Menu     |");
                                System.out.println("|                                   |");
                                System.out.println("=====================================\n");

                                System.out.print(
                                        "Please select options below: \n"
                                        + "1. View all attendee by date\n"
                                        + "2. View all attendee by event id\n"
                                        + "3. View all attendee by event status\n"
                                        + "4. View attendee by user id\n"
                                        + "5. Add attendee into event\n"
                                        + "6. Remove attendee from event\n"
                                        + "7. Send message to attendee\n"
                                        + "8. View message history\n"
                                        + "9. Check-in attendee\n\n"
                                        + "Notice:\n"
                                        + "a. Enter [1-9] to proceed \n"
                                        + "b. Enter [B] to back \n"
                                        + "-------------------------------------\n"
                                        + "Enter your option: ");

                                option = scanner.nextLine();

                                switch (option) {
                                    case "1":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|              View All Attendee            |");
                                        System.out.println("|             (Sort By Event Date)          |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");
                                        eventOrganizer.getEve_AttendeeList().viewAllRecordByDate();
                                        System.out.println("\n\nReturning to attendee management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "2":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|              View All Attendee            |");
                                        System.out.println("|                (By Event ID)              |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");

                                        System.out.println("Select your events below:");
                                        for (AvailableEvent event : eventOrganizer.getCreatedEventList().getCreatedEvents()) {
                                            System.out.println("Event ID: " + event.getEventId());
                                        }

                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "b. Only accept digits for event id input\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter event ID: ");
                                        attendeeEventId = scanner.nextLine();
                                        System.out.println();

                                        if (attendeeEventId.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        eventOrganizer.getEve_AttendeeList().viewAllRecordByEventId(attendeeEventId);
                                        System.out.println("\n\nReturning to attendee management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "3":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|              View All Attendee            |");
                                        System.out.println("|              (By Event Status)            |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");

                                        System.out.println("Select event status below:");
                                        System.out.println("1. Normal");
                                        System.out.println("2. Cancelled");
                                        System.out.println("3. Completed");
                                        System.out.println("4. Removed");

                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "b. Only accept digits for event id input\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter event status [1-4]: ");
                                        eventStatusInput = scanner.nextLine();
                                        System.out.println();

                                        if (eventStatusInput.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        eventOrganizer.getEve_AttendeeList().viewAllRecordByEventStatus(eventStatusInput);
                                        System.out.println("\n\nReturning to attendee management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "4":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|           View Attendee History           |");
                                        System.out.println("|                (By User Id)               |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");

                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "-------------------------------------");
                                        System.out.print("Enter user ID: ");
                                        userIdInput = scanner.nextLine();
                                        System.out.println();

                                        if (userIdInput.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        eventOrganizer.getEve_AttendeeList().viewAllRecordByUserId(userIdInput);

                                        System.out.println("\n\nReturning to attendee management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "5":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|          Add Attendee Into Event          |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");

                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "b. Enter valid input for user ID & event ID\n"
                                                + "c. Add attendee is free of charge\n"
                                                + "d. Only accept addon number of participant that not exceed max capacity of the event\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter user ID: ");
                                        userIdInput = scanner.nextLine();
                                        System.out.print("Enter event ID: ");
                                        eventIdInput = scanner.nextLine();
                                        System.out.print("Enter number of participants: ");
                                        numParticipant = scanner.nextLine();
                                        System.out.println();

                                        if (userIdInput.contains("-9999") || eventIdInput.contains("-9999") || numParticipant.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        if (!(InputValidation.addAttendee(userIdInput, eventIdInput, numParticipant))) {
                                            System.out.println("\nInvalid input...Try again\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        try {
                                            eventOrganizer.getEve_AttendeeList().addAttendee(userIdInput, eventIdInput, numParticipant, eventOrganizer.getCreatedEventList());
                                        } catch (IOException ex) {
                                            System.out.println("There is IOException");
                                            Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        System.out.println("\n\nReturning to attendee management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;

                                    case "6":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|         Remove Attendee From Event        |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");

                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "b. Enter valid input for user ID & event ID\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter user ID: ");
                                        userIdInput = scanner.nextLine();
                                        System.out.print("Enter event ID: ");
                                        eventIdInput = scanner.nextLine();
                                        System.out.println();

                                        if (userIdInput.contains("-9999") || eventIdInput.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        try {
                                            eventOrganizer.getEve_AttendeeList().removeAttendee(userIdInput, eventIdInput, eventOrganizer.getCreatedEventList());
                                        } catch (FileNotFoundException ex) {
                                            System.out.println("There is FileNotFoundException");
                                            Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IOException ex) {
                                            System.out.println("There is IOException");
                                            Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        System.out.println("\n\nReturning to attendee management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;

                                    case "7":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|           Send Message to Attendee        |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");

                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "b. Only accept valid user ID input\n"
                                                + "c. Only accept valid event ID input\n"
                                                + "d. Only accept alphabet and/or digits and/or [.,!? ] as message input\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter user ID: ");
                                        userIdInput = scanner.nextLine();
                                        System.out.print("Enter event ID: ");
                                        eventIdInput = scanner.nextLine();
                                        System.out.print("Enter message: ");
                                        message = scanner.nextLine();
                                        System.out.println();

                                        if (userIdInput.contains("-9999") || eventIdInput.contains("-9999") || message.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        try {
                                            eventOrganizer.getEve_AttendeeList().sendMessage(userIdInput, eventIdInput, message);
                                        } catch (IOException ex) {
                                            System.out.println("There is IOException");
                                            Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        System.out.println("\n\nReturning to attendee management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;

                                    case "8":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|             View Message History          |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");

                                        try {
                                            eventOrganizer.getEve_AttendeeList().viewMessage(eventOrganizer.getCreatedEventList());
                                        } catch (FileNotFoundException ex) {
                                            System.out.println("There is FileNotFoundException");
                                            Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        System.out.println("\n\nReturning to attendee management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "9":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|              Check-in Attendee            |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");
                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "b. Only accept valid user ID input\n"
                                                + "c. Only accept valid event ID input\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter user ID: ");
                                        userIdInput = scanner.nextLine();
                                        System.out.print("Enter event ID: ");
                                        eventIdInput = scanner.nextLine();
                                        System.out.println();

                                        if (userIdInput.contains("-9999") || eventIdInput.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        try {
                                            eventOrganizer.getEve_AttendeeList().checkIn(userIdInput, eventIdInput);
                                        } catch (IOException ex) {
                                            System.out.println("There is IOException");
                                            Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        System.out.println("\n\nReturning to attendee management menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;

                                    case "b", "B":
                                        isEventOrganizerMenuOptInvalid = true;
                                        break;
                                    default:
                                        System.out.println("Please select option above only. Try again...");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                }

                            } while (isEventOrganizerSubMenuOptInvalid == true);
                            break;
                        case "5":

                            do {
                                isEventOrganizerSubMenuOptInvalid = false;

                                System.out.println();
                                System.out.println();
                                System.out.println("=====================================");
                                System.out.println("|                                   |");
                                System.out.println("|     Reporting & Analysis Menu     |");
                                System.out.println("|                                   |");
                                System.out.println("=====================================\n");

                                System.out.print(
                                        "Please select options below: \n"
                                        + "1. Number of people check-in in selected event\n"
                                        + "2. Cost, revenue and profit report of selected event\n\n"
                                        + "Notice:\n"
                                        + "a. Enter [1-2] to proceed \n"
                                        + "b. Enter [B] to back \n"
                                        + "-------------------------------------\n"
                                        + "Enter your option: ");

                                option = scanner.nextLine();

                                switch (option) {
                                    case "1":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|         Number of People Check-in         |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");
                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "b. Only accept valid event ID input\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter event ID: ");
                                        eventIdInput = scanner.nextLine();
                                        System.out.println();

                                        if (eventIdInput.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        eventOrganizer.showNumPeopleCheckedIn(eventIdInput);

                                        System.out.println("\n\nReturning to reporting and analysis menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "2":
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("=============================================");
                                        System.out.println("|                                           |");
                                        System.out.println("|        Cost, Revenue & Profit Report      |");
                                        System.out.println("|                                           |");
                                        System.out.println("=============================================\n");
                                        System.out.println("\nNotice:\n"
                                                + "a. Enter [-9999] to cancel process \n"
                                                + "b. Only accept valid event ID input\n"
                                                + "-------------------------------------");
                                        System.out.print("Enter event ID: ");
                                        eventIdInput = scanner.nextLine();
                                        System.out.println();

                                        if (eventIdInput.contains("-9999")) {
                                            System.out.println("\nExiting current process...\n");
                                            isEventOrganizerSubMenuOptInvalid = true;
                                            break;
                                        }

                                        eventOrganizer.showProfitReport(eventIdInput);

                                        System.out.println("\n\nReturning to reporting and analysis menu...\n\n");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                        break;
                                    case "b", "B":
                                        isEventOrganizerMenuOptInvalid = true;
                                        break;
                                    default:
                                        System.out.println("\nPlease select option above only. Try again...");
                                        isEventOrganizerSubMenuOptInvalid = true;
                                }

                            } while (isEventOrganizerSubMenuOptInvalid == true);

                            break;
                        case "6":
                            System.out.println();
                            System.out.println();
                            System.out.println("==========================================");
                            System.out.println("|                                        |");
                            System.out.println("|       Update Personal Information      |");
                            System.out.println("|                                        |");
                            System.out.println("==========================================\n");

                            System.out.println("Notice:\n"
                                    + "a. Enter [-9999] to cancel process \n"
                                    + "b. Only accept whitespace and alphabet for name input\n"
                                    + "c. Only accept standard format for email input\n"
                                    + "d. Input is case sensitive\n"
                                    + "-------------------------------------");

                            System.out.print("Enter new full name: ");
                            userName = scanner.nextLine();
                            System.out.print("Enter new email: ");
                            userEmail = scanner.nextLine();

                            if (userName.contains("-9999") || userEmail.contains("-9999")) {
                                System.out.println("\nExiting current process...\n");
                                isEventOrganizerMenuOptInvalid = true;
                                break;
                            }

                            try {
                                eventOrganizer.updateProfile(userName, userEmail, Database.getEVENTORGANIZER());
                            } catch (IOException ex) {
                                System.out.println("There is IOException");
                                Logger.getLogger(EventManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            isEventOrganizerMenuOptInvalid = true;
                            break;
                        case "7":
                            System.out.println();
                            System.out.println();
                            System.out.println("=========================================");
                            System.out.println("|                                       |");
                            System.out.println("|       View Personal Information       |");
                            System.out.println("|                                       |");
                            System.out.println("=========================================\n");

                            System.out.println("ID: " + eventOrganizer.getUserId());
                            System.out.println("Name: " + eventOrganizer.getName());
                            System.out.println("Email: " + eventOrganizer.getEmail());

                            System.out.println("\n\nReturning to event organizer menu...\n\n");

                            isEventOrganizerMenuOptInvalid = true;
                            break;
                        case "8":
                            logoutAcc = User.logoutAcc();
                            users[1] = null;
                            break;
                        default:
                            System.out.println("Please select option above only. Try again...");
                            isEventOrganizerMenuOptInvalid = true;
                    }

                } while (isEventOrganizerMenuOptInvalid == true);
            }

        } while (logoutAcc == true || isMenuOptionInvalid == true || userInfo
                == null);

    }

}
