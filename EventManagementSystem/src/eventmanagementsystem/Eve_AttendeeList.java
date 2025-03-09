package eventmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Eve_AttendeeList {

    private ArrayList<RegisteredEvent> eve_Attendees;

    public Eve_AttendeeList() {

        this.eve_Attendees = new ArrayList<>();

    }

    public ArrayList<RegisteredEvent> getEve_Attendees() {
        return eve_Attendees;
    }

    public void setEve_Attendees(ArrayList<RegisteredEvent> eve_Attendees) {
        this.eve_Attendees = eve_Attendees;
    }

    public void viewAllRecordByDate() {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");

        Collections.sort(eve_Attendees, new Comparator<RegisteredEvent>() {
            public int compare(RegisteredEvent data1, RegisteredEvent data2) {

                try {
                    return dfm.parse(data1.getDate()).compareTo(dfm.parse(data2.getDate()));
                } catch (ParseException ex) {
                    Logger.getLogger(Eve_AttendeeList.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 0;

            }
        });

        for (RegisteredEvent data : eve_Attendees) {
            double doublePrice = Double.parseDouble(data.getPrice());

            System.out.println("User ID: " + data.getUserId());
            System.out.println("Event ID: " + data.getEventId());
            System.out.println("Event Name: " + data.getName());
            System.out.println("Event Date: " + data.getDate());
            System.out.printf("Ticket Unit Price: RM %.2f \n", doublePrice);
            System.out.println("Location: " + data.getLocation());
            System.out.println("Number of Participants: " + data.getNumParticipants());
            System.out.println("Registered Date: " + data.getRegistrationDate());
            System.out.println("Check-in Status: " + data.getCheckInStatus());
            System.out.println("Event Status: " + data.getEventStatus() + "\n");
        }

        System.out.println("Notice:\n"
                + "a. Remember user id to remove attendee from event\n"
                + "b. Only able to edit attendee when event status is normal\n"
                + "c. Same attendee will show up again if he repeat purchase\n"
                + "-------------------------------------");
    }

    public void viewAllRecordByEventId(String attendeeEventId) {

        System.out.println("Result:\n");

        for (RegisteredEvent data : eve_Attendees) {

            if (data.getEventId().equals(attendeeEventId)) {
                double doublePrice = Double.parseDouble(data.getPrice());

                System.out.println("User ID: " + data.getUserId());
                System.out.println("Event ID: " + data.getEventId());
                System.out.println("Event Name: " + data.getName());
                System.out.println("Event Date: " + data.getDate());
                System.out.printf("Ticket Unit Price: RM %.2f \n", doublePrice);
                System.out.println("Location: " + data.getLocation());
                System.out.println("Number of Participants: " + data.getNumParticipants());
                System.out.println("Registered Date: " + data.getRegistrationDate());
                System.out.println("Check-in Status: " + data.getCheckInStatus());
                System.out.println("Event Status: " + data.getEventStatus() + "\n");
            }

        }

        System.out.println("Notice:\n"
                + "a. Remember user id to view history or remove attendee from event\n"
                + "b. Only able to edit attendee when event status is normal\n"
                + "c. Same attendee will show up again if he repeat purchase\n"
                + "d. There will have no result if enter wrong event id\n"
                + "-------------------------------------");
    }

    public void viewAllRecordByEventStatus(String eventStatusInput) {
        String eventStatus = null;

        if (eventStatusInput.equals("1")) {
            eventStatus = "Normal";
        } else if (eventStatusInput.equals("2")) {
            eventStatus = "Cancelled";
        } else if ((eventStatusInput.equals("3"))) {
            eventStatus = "Completted";
        } else if ((eventStatusInput.equals("4"))) {
            eventStatus = "Removed";
        }

        System.out.println("Result:\n");

        for (RegisteredEvent data : eve_Attendees) {

            if (data.getEventStatus().equals(eventStatus)) {
                double doublePrice = Double.parseDouble(data.getPrice());

                System.out.println("User ID: " + data.getUserId());
                System.out.println("Event ID: " + data.getEventId());
                System.out.println("Event Name: " + data.getName());
                System.out.println("Event Date: " + data.getDate());
                System.out.printf("Ticket Unit Price: RM %.2f \n", doublePrice);
                System.out.println("Location: " + data.getLocation());
                System.out.println("Number of Participants: " + data.getNumParticipants());
                System.out.println("Registered Date: " + data.getRegistrationDate());
                System.out.println("Check-in Status: " + data.getCheckInStatus());
                System.out.println("Event Status: " + data.getEventStatus() + "\n");
            }

        }

        System.out.println("Notice:\n"
                + "a. Remember user id to view history or remove attendee from event\n"
                + "b. Only able to edit attendee when event status is normal\n"
                + "c. Same attendee will show up again if he repeat purchase\n"
                + "d. Empty result will be display if there is not data that meet the filtering criteria\n"
                + "-------------------------------------");
    }

    public void viewAllRecordByUserId(String userIdInput) {

        System.out.println("Result:\n");

        for (RegisteredEvent data : eve_Attendees) {

            if (data.getUserId().equals(userIdInput)) {
                double doublePrice = Double.parseDouble(data.getPrice());

                System.out.println("User ID: " + data.getUserId());
                System.out.println("Event ID: " + data.getEventId());
                System.out.println("Event Name: " + data.getName());
                System.out.println("Event Date: " + data.getDate());
                System.out.printf("Ticket Unit Price: RM %.2f \n", doublePrice);
                System.out.println("Location: " + data.getLocation());
                System.out.println("Number of Participants: " + data.getNumParticipants());
                System.out.println("Registered Date: " + data.getRegistrationDate());
                System.out.println("Check-in Status: " + data.getCheckInStatus());
                System.out.println("Event Status: " + data.getEventStatus() + "\n");
            }

        }

        System.out.println("Notice:\n"
                + "a. Remember user id to remove attendee from event\n"
                + "b. Only able to edit attendee when event status is normal\n"
                + "-------------------------------------");
    }

    public void viewMessage(CreatedEventList createdEventList) throws FileNotFoundException {
        int counter = 0;

        System.out.println("Result:");
        Scanner sc = new Scanner(Database.getATTENDEEMAIL());

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            String[] arrOfData = data.split(";");

            for (int i = 0; i < createdEventList.getCreatedEvents().size(); i++) {
                if (arrOfData[1].equals(createdEventList.getCreatedEvents().get(i).getEventId())) {
                    System.out.println("To user ID: " + arrOfData[0]);
                    System.out.println("Event ID: " + arrOfData[1]);
                    System.out.println("Message: " + arrOfData[2]);
                    System.out.println();

                    counter++;
                }
            }

        }

        System.out.println("Total mails: " + counter);

        sc.close();

    }

    public void sendMessage(String userIdInput, String eventIdInput, String message) throws FileNotFoundException, IOException {

        if (InputValidation.message(message)) {
            for (RegisteredEvent eve_Attendee : eve_Attendees) {
                if (eve_Attendee.getUserId().equals(userIdInput) && eve_Attendee.getEventId().equals(eventIdInput)) {

                    Scanner sc = new Scanner(Database.getATTENDEEMAIL());

                    int counter = 0;

                    while (sc.hasNextLine()) {

                        String data = sc.nextLine();

                        counter++;
                    }

                    sc.close();

                    Scanner scanner = new Scanner(Database.getATTENDEEMAIL());

                    String[] tempDataHolder = new String[counter];

                    for (int i = 0; i < counter; i++) {
                        tempDataHolder[i] = scanner.nextLine();
                    }

                    scanner.close();

                    boolean result = Files.deleteIfExists(Database.getATTENDEEMAIL().toPath());

                    if (result) {

                        File newFile = Database.getATTENDEEMAIL();

                        if (newFile.createNewFile()) {
                            try (FileWriter myWriter = new FileWriter(newFile)) {

                                for (String tempData : tempDataHolder) {
                                    String[] dataArray = tempData.split(";");

                                    for (String data : dataArray) {
                                        myWriter.write(data + ";");
                                    }
                                    myWriter.write("\n");

                                }

                                myWriter.write(userIdInput + ";");
                                myWriter.write(eventIdInput + ";");
                                myWriter.write(message + ";");
                                myWriter.close();
                            } catch (IOException e) {
                                throw new IOException("Problem in myWriter filewriter!");
                            }
                        }
                    }

                    System.out.println("Message sent successfully");
                    return;
                }
            }
        }

        System.out.println("Error occur. Please try again...");

    }

    public void removeAttendee(String userIdInput, String eventIdInput, CreatedEventList createdEventList) throws FileNotFoundException, IOException {
//userId also need to check whether exist in attendee.txt
        boolean isUserIdExist = false;

        Scanner scAttendee = new Scanner(Database.getATTENDEE());

        while (scAttendee.hasNextLine()) {
            String data = scAttendee.nextLine();

            String[] arrOfData = data.split(";");

            if (arrOfData[0].equals(userIdInput)) {
                isUserIdExist = true;
                break;
            }
        }

        scAttendee.close();

        Scanner sc = new Scanner(Database.getAVAILABLEEVENT());

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            String[] arrOfData = data.split(";");

            for (int k = 0; k < arrOfData.length; k++) {
                for (int i = 0; i < createdEventList.getCreatedEvents().size(); i++) {
                    int numCurrentCapacity = Integer.parseInt(arrOfData[4]);

                    if (arrOfData[0].equals(eventIdInput) && (arrOfData[0].equals(createdEventList.getCreatedEvents().get(i).getEventId())) && arrOfData[7].equals("Normal") && isUserIdExist && (numCurrentCapacity != 0)) {

                        File newFile;
                        int numParticipantDeleted = 0;

//                      remove record in regisretedEvent.txt
                        Scanner scanRegisteredEvent = new Scanner(Database.getREGISTRATION());

                        int counter = 0;

                        while (scanRegisteredEvent.hasNextLine()) {
                            String dataRegisteredEvent = scanRegisteredEvent.nextLine();

                            counter++;
                        }

                        scanRegisteredEvent.close();

                        Scanner scanner = new Scanner(Database.getREGISTRATION());

                        String[] tempDataHolder = new String[counter];

                        for (int l = 0; l < counter; l++) {
                            tempDataHolder[l] = scanner.nextLine();
                        }

                        scanner.close();

                        boolean result = Files.deleteIfExists(Database.getREGISTRATION().toPath());

                        if (result) {
                            newFile = Database.getREGISTRATION();

                            if (newFile.createNewFile()) {
                                try (FileWriter myWriter = new FileWriter(newFile)) {

                                    for (String tempData : tempDataHolder) {
                                        String[] dataArray = tempData.split(";");

                                        if (dataArray[0].equals(userIdInput) && dataArray[1].equals(eventIdInput) && dataArray[8].equals("Normal")) {

                                            int numCapacity = Integer.parseInt(dataArray[6]);
                                            numParticipantDeleted += numCapacity;

                                            myWriter.write(dataArray[0] + ";");
                                            myWriter.write(dataArray[1] + ";");
                                            myWriter.write(dataArray[2] + ";");
                                            myWriter.write(dataArray[3] + ";");
                                            myWriter.write(dataArray[4] + ";");
                                            myWriter.write(dataArray[5] + ";");
                                            myWriter.write(dataArray[6] + ";");
                                            myWriter.write(dataArray[7] + ";");
                                            myWriter.write("Removed;");
                                            myWriter.write(dataArray[9] + ";");
                                            myWriter.write("\n");

                                            System.out.println("Sucessfully deleted attendee!");

                                            // send notification to removed attendee
                                            Scanner scanAttendeeMail = new Scanner(Database.getATTENDEEMAIL());

                                            counter = 0;

                                            while (scanAttendeeMail.hasNextLine()) {

                                                String temp = scanAttendeeMail.nextLine();

                                                counter++;
                                            }

                                            scanAttendeeMail.close();

                                            Scanner scannerAttendeeMail = new Scanner(Database.getATTENDEEMAIL());

                                            String[] tempDataHolders = new String[counter];

                                            for (int l = 0; l < counter; l++) {
                                                tempDataHolders[l] = scannerAttendeeMail.nextLine();
                                            }

                                            scannerAttendeeMail.close();

                                            boolean results = Files.deleteIfExists(Database.getATTENDEEMAIL().toPath());

                                            if (results) {

                                                File newFiles = Database.getATTENDEEMAIL();

                                                if (newFiles.createNewFile()) {
                                                    try (FileWriter myWriterNew = new FileWriter(newFiles)) {

                                                        for (String tempDatas : tempDataHolders) {
                                                            String[] dataArrays = tempDatas.split(";");

                                                            for (String datas : dataArrays) {
                                                                myWriterNew.write(datas + ";");
                                                            }
                                                            myWriterNew.write("\n");

                                                        }

                                                        myWriterNew.write(userIdInput + ";");
                                                        myWriterNew.write(eventIdInput + ";");
                                                        myWriterNew.write("You are removed from this event. Please contact customer service 012-3456789 to learn more;");
                                                        myWriterNew.write("\n");
                                                        myWriterNew.close();
                                                    } catch (IOException e) {
                                                        throw new IOException("Problem in myWriter filewriter!");
                                                    }
                                                }

                                            }

                                            System.out.println("Notification sucessfully sent to attendee!");

                                        } else {
                                            for (String dat : dataArray) {
                                                myWriter.write(dat + ";");
                                            }
                                            myWriter.write("\n");
                                        }

                                    }
                                    myWriter.close();
                                } catch (IOException e) {
                                    throw new IOException("Problem in myWriter filewriter!");
                                }
                            }
//                        update capacity in availableEvents.txt
                            Scanner scan = new Scanner(Database.getAVAILABLEEVENT());

                            int count = 0;

                            while (scan.hasNextLine()) {
                                String dataAvailableEvent = scan.nextLine();

                                count++;
                            }

                            scan.close();

                            Scanner tempScan = new Scanner(Database.getAVAILABLEEVENT());

                            String[] tempHolder = new String[count];

                            for (int j = 0; j < count; j++) {
                                tempHolder[j] = tempScan.nextLine();
                            }

                            tempScan.close();

                            sc.close();

                            boolean isDeleted = Files.deleteIfExists(Database.getAVAILABLEEVENT().toPath());

                            if (isDeleted) {
                                newFile = Database.getAVAILABLEEVENT();

                                if (newFile.createNewFile()) {
                                    try (FileWriter myWriter = new FileWriter(newFile)) {

                                        for (String tempData : tempHolder) {

                                            String[] dataArray = tempData.split(";");

                                            if (dataArray[0].equals(eventIdInput)) {

                                                Integer intCurrentCapacity = Integer.valueOf(dataArray[4]);

                                                int newTotalCapacity = intCurrentCapacity - numParticipantDeleted;

                                                String totalCapacity = Integer.toString(newTotalCapacity);

                                                myWriter.write(dataArray[0] + ";");
                                                myWriter.write(dataArray[1] + ";");
                                                myWriter.write(dataArray[2] + ";");
                                                myWriter.write(dataArray[3] + ";");
                                                myWriter.write(totalCapacity + ";");
                                                myWriter.write(dataArray[5] + ";");
                                                myWriter.write(dataArray[6] + ";");
                                                myWriter.write(dataArray[7] + ";");
                                                myWriter.write(dataArray[8] + ";");
                                                myWriter.write(dataArray[9] + ";");
                                                myWriter.write(dataArray[10] + ";");
                                                myWriter.write(dataArray[11] + ";");
                                                myWriter.write(dataArray[12] + ";");
                                                myWriter.write(dataArray[13] + ";");
                                                myWriter.write(dataArray[14] + ";");
                                                myWriter.write(dataArray[15] + ";");
                                                myWriter.write(dataArray[16] + ";");
                                                myWriter.write(dataArray[17] + ";");
                                                myWriter.write(dataArray[18] + ";");
                                                myWriter.write(dataArray[19] + ";");
                                                myWriter.write(dataArray[20] + ";");
                                                myWriter.write("\n");
                                            } else {
                                                myWriter.write(dataArray[0] + ";");
                                                myWriter.write(dataArray[1] + ";");
                                                myWriter.write(dataArray[2] + ";");
                                                myWriter.write(dataArray[3] + ";");
                                                myWriter.write(dataArray[4] + ";");
                                                myWriter.write(dataArray[5] + ";");
                                                myWriter.write(dataArray[6] + ";");
                                                myWriter.write(dataArray[7] + ";");
                                                myWriter.write(dataArray[8] + ";");
                                                myWriter.write(dataArray[9] + ";");
                                                myWriter.write(dataArray[10] + ";");
                                                myWriter.write(dataArray[11] + ";");
                                                myWriter.write(dataArray[12] + ";");
                                                myWriter.write(dataArray[13] + ";");
                                                myWriter.write(dataArray[14] + ";");
                                                myWriter.write(dataArray[15] + ";");
                                                myWriter.write(dataArray[16] + ";");
                                                myWriter.write(dataArray[17] + ";");
                                                myWriter.write(dataArray[18] + ";");
                                                myWriter.write(dataArray[19] + ";");
                                                myWriter.write(dataArray[20] + ";");
                                                myWriter.write("\n");
                                            }
                                        }

                                        myWriter.close();
                                    } catch (IOException e) {
                                        throw new IOException("Problem in myWriter filewriter!");
                                    }
                                }
                            }

                            return;

                        }

                    }
                }
            }

        }

        sc.close();

        System.out.println("Error occur...Try again later");
    }

    public void checkIn(String userIdInput, String eventIdInput) throws FileNotFoundException, IOException {
        boolean isUserIdExist = false;

        Scanner scAttendee = new Scanner(Database.getREGISTRATION());

        while (scAttendee.hasNextLine()) {
            String data = scAttendee.nextLine();

            String[] arrOfData = data.split(";");

            for (int i = 0; i < eve_Attendees.size(); i++) {
                if (arrOfData[0].equals(userIdInput) && arrOfData[1].equals(eventIdInput) && arrOfData[1].equals(eve_Attendees.get(i).getEventId()) && arrOfData[8].equals("Normal")) {
                    isUserIdExist = true;
                    break;
                }
            }

        }

        scAttendee.close();

        if (isUserIdExist) {
            Scanner scan = new Scanner(Database.getREGISTRATION());

            int count = 0;

            while (scan.hasNextLine()) {
                String data = scan.nextLine();

                count++;
            }

            scan.close();

            Scanner tempScan = new Scanner(Database.getREGISTRATION());

            String[] tempHolder = new String[count];

            for (int j = 0; j < count; j++) {
                tempHolder[j] = tempScan.nextLine();
            }

            tempScan.close();

            boolean isDeleted = Files.deleteIfExists(Database.getREGISTRATION().toPath());

            if (isDeleted) {
                File newFile = Database.getREGISTRATION();

                if (newFile.createNewFile()) {
                    try (FileWriter myWriter = new FileWriter(newFile)) {

                        for (String tempData : tempHolder) {

                            String[] dataArray = tempData.split(";");

                            if (dataArray[1].equals(eventIdInput) && dataArray[0].equals(userIdInput) && dataArray[8].equals("Normal")) {
                                myWriter.write(dataArray[0] + ";");
                                myWriter.write(dataArray[1] + ";");
                                myWriter.write(dataArray[2] + ";");
                                myWriter.write(dataArray[3] + ";");
                                myWriter.write(dataArray[4] + ";");
                                myWriter.write(dataArray[5] + ";");
                                myWriter.write(dataArray[6] + ";");
                                myWriter.write(dataArray[7] + ";");
                                myWriter.write(dataArray[8] + ";");
                                myWriter.write("Checked;");
                                myWriter.write("\n");
                                System.out.println("Check-in successfully!");
                            } else {
                                myWriter.write(dataArray[0] + ";");
                                myWriter.write(dataArray[1] + ";");
                                myWriter.write(dataArray[2] + ";");
                                myWriter.write(dataArray[3] + ";");
                                myWriter.write(dataArray[4] + ";");
                                myWriter.write(dataArray[5] + ";");
                                myWriter.write(dataArray[6] + ";");
                                myWriter.write(dataArray[7] + ";");
                                myWriter.write(dataArray[8] + ";");
                                myWriter.write(dataArray[9] + ";");
                                myWriter.write("\n");
                            }
                        }
                    } catch (IOException e) {
                        throw new IOException("Problem in myWriter filewriter!");
                    }
                }
            }
        } else {

            System.out.println("Check-in fail...Try again");
        }

    }

    public void addAttendee(String userIdInput, String eventIdInput, String numParticipant, CreatedEventList createdEventList) throws FileNotFoundException, IOException {
        //userId also need to check whether exist in attendee.txt
        boolean isUserIdExist = false;

        Scanner scAttendee = new Scanner(Database.getATTENDEE());

        while (scAttendee.hasNextLine()) {
            String data = scAttendee.nextLine();

            String[] arrOfData = data.split(";");

            if (arrOfData[0].equals(userIdInput)) {
                isUserIdExist = true;
                break;
            }
        }

        scAttendee.close();

        Scanner sc = new Scanner(Database.getAVAILABLEEVENT());

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            String[] arrOfData = data.split(";");

            for (int k = 0; k < arrOfData.length; k++) {
                for (int i = 0; i < createdEventList.getCreatedEvents().size(); i++) {
                    double currentCapacity = Double.parseDouble(arrOfData[4]);
                    double maxCapacity = Double.parseDouble(arrOfData[5]);
                    double doubleNumParticipants = Double.parseDouble(numParticipant);

                    if (arrOfData[0].equals(eventIdInput) && ((currentCapacity + doubleNumParticipants) <= maxCapacity) && (arrOfData[0].equals(createdEventList.getCreatedEvents().get(i).getEventId())) && arrOfData[7].equals("Normal") && isUserIdExist && doubleNumParticipants != 0) {

                        sc.close();

                        File newFile;

//                        update capacity in availableEvents.txt
                        Scanner scan = new Scanner(Database.getAVAILABLEEVENT());

                        int count = 0;

                        while (scan.hasNextLine()) {
                            String dataAvailableEvent = scan.nextLine();

                            count++;
                        }

                        scan.close();

                        Scanner tempScan = new Scanner(Database.getAVAILABLEEVENT());

                        String[] tempHolder = new String[count];

                        for (int j = 0; j < count; j++) {
                            tempHolder[j] = tempScan.nextLine();
                        }

                        tempScan.close();

                        boolean isDeleted = Files.deleteIfExists(Database.getAVAILABLEEVENT().toPath());

                        if (isDeleted) {
                            newFile = Database.getAVAILABLEEVENT();

                            if (newFile.createNewFile()) {
                                try (FileWriter myWriter = new FileWriter(newFile)) {

                                    for (String tempData : tempHolder) {

                                        String[] dataArray = tempData.split(";");

                                        if (dataArray[0].equals(eventIdInput)) {

                                            Integer intCurrentCapacity = Integer.valueOf(dataArray[4]);
                                            Integer intNumParticipant = Integer.valueOf(numParticipant);

                                            int newTotalCapacity = intCurrentCapacity + intNumParticipant;

                                            String totalCapacity = Integer.toString(newTotalCapacity);

                                            myWriter.write(dataArray[0] + ";");
                                            myWriter.write(dataArray[1] + ";");
                                            myWriter.write(dataArray[2] + ";");
                                            myWriter.write(dataArray[3] + ";");
                                            myWriter.write(totalCapacity + ";");
                                            myWriter.write(dataArray[5] + ";");
                                            myWriter.write(dataArray[6] + ";");
                                            myWriter.write(dataArray[7] + ";");
                                            myWriter.write(dataArray[8] + ";");
                                            myWriter.write(dataArray[9] + ";");
                                            myWriter.write(dataArray[10] + ";");
                                            myWriter.write(dataArray[11] + ";");
                                            myWriter.write(dataArray[12] + ";");
                                            myWriter.write(dataArray[13] + ";");
                                            myWriter.write(dataArray[14] + ";");
                                            myWriter.write(dataArray[15] + ";");
                                            myWriter.write(dataArray[16] + ";");
                                            myWriter.write(dataArray[17] + ";");
                                            myWriter.write(dataArray[18] + ";");
                                            myWriter.write(dataArray[19] + ";");
                                            myWriter.write(dataArray[20] + ";");
                                            myWriter.write("\n");
                                        } else {
                                            myWriter.write(dataArray[0] + ";");
                                            myWriter.write(dataArray[1] + ";");
                                            myWriter.write(dataArray[2] + ";");
                                            myWriter.write(dataArray[3] + ";");
                                            myWriter.write(dataArray[4] + ";");
                                            myWriter.write(dataArray[5] + ";");
                                            myWriter.write(dataArray[6] + ";");
                                            myWriter.write(dataArray[7] + ";");
                                            myWriter.write(dataArray[8] + ";");
                                            myWriter.write(dataArray[9] + ";");
                                            myWriter.write(dataArray[10] + ";");
                                            myWriter.write(dataArray[11] + ";");
                                            myWriter.write(dataArray[12] + ";");
                                            myWriter.write(dataArray[13] + ";");
                                            myWriter.write(dataArray[14] + ";");
                                            myWriter.write(dataArray[15] + ";");
                                            myWriter.write(dataArray[16] + ";");
                                            myWriter.write(dataArray[17] + ";");
                                            myWriter.write(dataArray[18] + ";");
                                            myWriter.write(dataArray[19] + ";");
                                            myWriter.write(dataArray[20] + ";");
                                            myWriter.write("\n");
                                        }

                                    }
                                } catch (IOException e) {
                                    throw new IOException("Problem in myWriter filewriter!");
                                }
                            }
                        }

//                        update 1 new registerEvent record in regisretedEvent.txt
                        Scanner scanRegisteredEvent = new Scanner(Database.getREGISTRATION());

                        int counter = 0;

                        while (scanRegisteredEvent.hasNextLine()) {
                            String dataRegisteredEvent = scanRegisteredEvent.nextLine();

                            counter++;
                        }

                        scanRegisteredEvent.close();

                        Scanner scanner = new Scanner(Database.getREGISTRATION());

                        String[] tempDataHolder = new String[counter];

                        for (int l = 0; l < counter; l++) {
                            tempDataHolder[l] = scanner.nextLine();
                        }

                        scanner.close();

                        boolean result = Files.deleteIfExists(Database.getREGISTRATION().toPath());

                        if (result) {
                            newFile = Database.getREGISTRATION();

                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                            Date date = new Date();

                            if (newFile.createNewFile()) {
                                try (FileWriter myWriter = new FileWriter(newFile)) {

                                    for (String tempData : tempDataHolder) {
                                        String[] dataArray = tempData.split(";");

                                        for (String dat : dataArray) {
                                            myWriter.write(dat + ";");
                                        }
                                        myWriter.write("\n");
                                    }

                                    myWriter.write(userIdInput + ";");
                                    myWriter.write(eventIdInput + ";");
                                    myWriter.write(arrOfData[1] + ";");
                                    myWriter.write(arrOfData[2] + ";");
                                    myWriter.write("0.00;");
                                    myWriter.write(arrOfData[6] + ";");
                                    myWriter.write(numParticipant + ";");
                                    myWriter.write(formatter.format(date) + ";");
                                    myWriter.write("Normal;");
                                    myWriter.write("Unchecked;");
                                    myWriter.close();

                                    System.out.println("Sucessfully added new attendee!");
                                    return;
                                } catch (IOException e) {
                                    throw new IOException("Problem in myWriter filewriter!");
                                }
                            }

                        }
                    }
                }
            }

        }

        sc.close();

        System.out.println("Error occur...Try again later");
    }

    public void addRecord(CreatedEventList createdEventList) throws FileNotFoundException {
        this.eve_Attendees = new ArrayList<>();

        Scanner sc = new Scanner(Database.getREGISTRATION());

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            String[] arrOfData = data.split(";");

            for (int i = 0; i < createdEventList.getCreatedEvents().size(); i++) {

                if (arrOfData[1].equals(createdEventList.getCreatedEvents().get(i).getEventId())) {

                    RegisteredEvent eve_Attendee = new RegisteredEvent(arrOfData[0],
                            arrOfData[1], arrOfData[2],
                            arrOfData[3], arrOfData[4],
                            arrOfData[5], arrOfData[6],
                            arrOfData[7], arrOfData[8], arrOfData[9]
                    );

                    eve_Attendees.add(eve_Attendee);
                }
            }

        }

        sc.close();
    }

    @Override
    public String toString() {
        return "Eve_AttendeeList{" + "eve_Attendees=" + eve_Attendees + '}';
    }

}
