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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreatedEventList {

    private ArrayList<AvailableEvent> createdEvents;

    public CreatedEventList() {
        this.createdEvents = new ArrayList<>();
    }

    public ArrayList<AvailableEvent> getCreatedEvents() {
        return createdEvents;
    }

    public void setCreatedEvents(ArrayList<AvailableEvent> createdEvents) {
        this.createdEvents = createdEvents;
    }

    public void viewAllRecordByDate() {
        System.out.println("\nResult:\n");
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");

        Collections.sort(createdEvents, new Comparator<AvailableEvent>() {
            public int compare(AvailableEvent data1, AvailableEvent data2) {

                try {
                    return dfm.parse(data1.getDate()).compareTo(dfm.parse(data2.getDate()));
                } catch (ParseException ex) {
                    Logger.getLogger(AvailableEventList.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 0;

            }
        });

        for (AvailableEvent data : createdEvents) {
            double doubleEventPrice = Double.parseDouble(data.getPrice());
            double doubleFoodDrinkPricePerPax = Double.parseDouble(data.getFoodDrinkPricePerPax());
            double doubleVenuePricePerDay = Double.parseDouble(data.getVenuePricePerDay());
            double doubleStaffPricePerDay = Double.parseDouble(data.getStaffPricePerDay());

            System.out.println("Event Id: " + data.getEventId());
            System.out.println("Name: " + data.getName());
            System.out.println("Date: " + data.getDate());
            System.out.printf("Price per pax: RM %.2f \n", doubleEventPrice);
            System.out.println("Capacity: " + data.getCurrentCapacity() + "/" + data.getMaxCapacity());
            System.out.println("Location: " + data.getLocation());
            System.out.println("Food drink supplier name: " + data.getFoodDrinkName());
            System.out.println("Food drink supplier email: " + data.getFoodDrinkEmail());
            System.out.println("Food drink supplier description: " + data.getFoodDrinkDescription());
            System.out.printf("Food drink supplier price per pax: RM %.2f \n", doubleFoodDrinkPricePerPax);
            System.out.println("Venue supplier name: " + data.getVenueName());
            System.out.println("Venue supplier email: " + data.getVenueEmail());
            System.out.println("Venue supplier location: " + data.getVenueLocation());
            System.out.printf("Venue supplier price per day: RM %.2f \n", doubleVenuePricePerDay);
            System.out.println("Venue supplier max capacity: " + data.getVenueMaxCapacity());
            System.out.println("Staff supplier name: " + data.getStaffName());
            System.out.println("Staff supplier email: " + data.getStaffEmail());
            System.out.printf("Staff supplier price per day: RM %.2f \n", doubleStaffPricePerDay);
            System.out.println("Event status: " + data.getEventStatus() + "\n");
        }

        System.out.println("Notice:\n"
                + "a. Remember event id to cancel event \n"
                + "-------------------------------------");
    }

    public void addRecord(String eventOrganizerId) throws FileNotFoundException {

        this.createdEvents = new ArrayList<>();

        Scanner sc = new Scanner(Database.getAVAILABLEEVENT());

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            String[] arrOfData = data.split(";");

            if (eventOrganizerId.equals(arrOfData[8])) {

                AvailableEvent createdEvent = new AvailableEvent(arrOfData[0], arrOfData[1], arrOfData[2], arrOfData[3], arrOfData[4], arrOfData[5], arrOfData[6], arrOfData[7], arrOfData[8], arrOfData[9], arrOfData[10], arrOfData[11], arrOfData[12], arrOfData[13], arrOfData[14], arrOfData[15], arrOfData[16], arrOfData[17], arrOfData[18], arrOfData[19], arrOfData[20]);

                createdEvents.add(createdEvent);
            }

        }

        sc.close();

    }

    public void cancelRecord(String eventIdInput, String eventOrganizerId, Eve_AttendeeList eve_AttendeeList) throws FileNotFoundException, IOException {
        boolean isEventIdExist = false;

        Scanner sc = new Scanner(Database.getAVAILABLEEVENT());

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            String[] arrOfData = data.split(";");

            if (arrOfData[0].equals(eventIdInput) && arrOfData[8].equals(eventOrganizerId) && arrOfData[7].equals("Normal")) {
                isEventIdExist = true;
                break;
            }
        }

        sc.close();

        if (isEventIdExist) {

            Scanner scAvailableEvent = new Scanner(Database.getAVAILABLEEVENT());

            int counter = 0;

            while (scAvailableEvent.hasNextLine()) {
                String data = scAvailableEvent.nextLine();

                counter++;

            }

            scAvailableEvent.close();

            Scanner scanner = new Scanner(Database.getAVAILABLEEVENT());

            String[] tempDataHolder = new String[counter];

            for (int l = 0; l < counter; l++) {
                tempDataHolder[l] = scanner.nextLine();
            }

            scanner.close();

            boolean result = Files.deleteIfExists(Database.getAVAILABLEEVENT().toPath());

            if (result) {
                File newFile = Database.getAVAILABLEEVENT();

                if (newFile.createNewFile()) {
                    try (FileWriter myWriter = new FileWriter(newFile)) {

                        for (String tempData : tempDataHolder) {
                            String[] dataArray = tempData.split(";");

                            if (dataArray[0].equals(eventIdInput) && dataArray[8].equals(eventOrganizerId) && dataArray[7].equals("Normal")) {
                                myWriter.write(dataArray[0] + ";");
                                myWriter.write(dataArray[1] + ";");
                                myWriter.write(dataArray[2] + ";");
                                myWriter.write(dataArray[3] + ";");
                                myWriter.write(dataArray[4] + ";");
                                myWriter.write(dataArray[5] + ";");
                                myWriter.write(dataArray[6] + ";");
                                myWriter.write("Cancelled;");
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
                                System.out.println("Sucessfully cancelled event!");

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
                                        try (FileWriter myWriterATTENDEEMAIL = new FileWriter(newFiles)) {

                                            for (String tempDat : tempDataHolders) {
                                                String[] dataArrays = tempDat.split(";");

                                                for (String datas : dataArrays) {
                                                    myWriterATTENDEEMAIL.write(datas + ";");
                                                }
                                                myWriterATTENDEEMAIL.write("\n");

                                            }

//                                            repeat send all attendee in this event
                                            for (int i = 0; i < eve_AttendeeList.getEve_Attendees().size(); i++) {

                                                if (eve_AttendeeList.getEve_Attendees().get(i).getEventStatus().equals("Normal") && (eve_AttendeeList.getEve_Attendees().get(i).getEventId().equals(eventIdInput))) {

                                                    myWriterATTENDEEMAIL.write(eve_AttendeeList.getEve_Attendees().get(i).getUserId() + ";");
                                                    myWriterATTENDEEMAIL.write(eventIdInput + ";");
                                                    myWriterATTENDEEMAIL.write("You are removed from this event. Please contact customer service 012-3456789 to learn more;");
                                                    myWriterATTENDEEMAIL.write("\n");
                                                }
                                            }

                                            myWriterATTENDEEMAIL.close();
                                        } catch (IOException e) {
                                            throw new IOException("Problem in myWriter filewriter!");
                                        }
                                    }
//                                          update cancel status in registeredEvent.txt
                                    Scanner scRegisteredEvent = new Scanner(Database.getREGISTRATION());

                                    counter = 0;

                                    while (scRegisteredEvent.hasNextLine()) {
                                        String data = scRegisteredEvent.nextLine();
                                        counter++;
                                    }

                                    scRegisteredEvent.close();

                                    Scanner scannerRegisteredEvent = new Scanner(Database.getREGISTRATION());

                                    String[] tempDataHolderRegisteredEvent = new String[counter];

                                    for (int l = 0; l < counter; l++) {
                                        tempDataHolderRegisteredEvent[l] = scannerRegisteredEvent.nextLine();

                                    }

                                    scannerRegisteredEvent.close();

                                    boolean resultRegisteredEvent = Files.deleteIfExists(Database.getREGISTRATION().toPath());
                                    if (resultRegisteredEvent) {

                                        File newFilesRegisteredEvent = Database.getREGISTRATION();

                                        if (newFilesRegisteredEvent.createNewFile()) {
                                            FileWriter myWriterRegisteredEvent = new FileWriter(newFilesRegisteredEvent);

                                            for (String tempDat : tempDataHolderRegisteredEvent) {
                                                String[] dataArrays = tempDat.split(";");

                                                if (dataArrays[1].equals(eventIdInput) && dataArrays[8].equals("Normal")) {
                                                    myWriterRegisteredEvent.write(dataArrays[0] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[1] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[2] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[3] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[4] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[5] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[6] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[7] + ";");
                                                    myWriterRegisteredEvent.write("Cancelled;");
                                                    myWriterRegisteredEvent.write(dataArrays[9] + ";");
                                                    myWriterRegisteredEvent.write("\n");
                                                } else {
                                                    myWriterRegisteredEvent.write(dataArrays[0] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[1] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[2] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[3] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[4] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[5] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[6] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[7] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[8] + ";");
                                                    myWriterRegisteredEvent.write(dataArrays[9] + ";");
                                                    myWriterRegisteredEvent.write("\n");
                                                }
                                            }

                                            myWriterRegisteredEvent.close();
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
            }

        } else {
            System.out.println("Wrong input...Try again later");
        }
    }

    @Override
    public String toString() {
        return "CreatedEventList{" + "createdEvents=" + createdEvents + '}';
    }

}
