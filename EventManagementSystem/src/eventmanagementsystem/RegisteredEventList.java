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

public class RegisteredEventList {

    private ArrayList<RegisteredEvent> registeredEvents;

    public RegisteredEventList() {
        this.registeredEvents = new ArrayList<>();
    }

    public ArrayList<RegisteredEvent> getRegisteredEvents() {
        return registeredEvents;
    }

    public void setRegisteredEvents(ArrayList<RegisteredEvent> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public void viewAllRecordByDate() {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");

        Collections.sort(registeredEvents, new Comparator<>() {
            public int compare(RegisteredEvent data1, RegisteredEvent data2) {

                try {
                    return dfm.parse(data1.getDate()).compareTo(dfm.parse(data2.getDate()));
                } catch (ParseException ex) {
                    Logger.getLogger(AvailableEventList.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 0;

            }
        });

        for (RegisteredEvent data : registeredEvents) {

            double price = Double.parseDouble(data.getPrice());
            double quantity = Double.parseDouble(data.getNumParticipants());

            System.out.println("Event ID: " + data.getEventId());
            System.out.println("Event Name: " + data.getName());
            System.out.println("Event Date: " + data.getDate());
            System.out.println("Location: " + data.getLocation());
            System.out.println("Number of Participants: " + data.getNumParticipants());
            System.out.printf("Total Price Ticket: RM %.2f \n", (price * quantity));
            System.out.println("Event Status: " + data.getEventStatus());
            System.out.println("Check-in Status: " + data.getCheckInStatus());
            System.out.println("Registration Date: " + data.getRegistrationDate() + "\n");
        }

        System.out.println("Total registered events: " + registeredEvents.size() + "\n");

    }

    public static void updateLatestRecord() throws FileNotFoundException, IOException, ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();

        Scanner sc = new Scanner(Database.getREGISTRATION());

        int counter = 0;

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            counter++;
        }

        sc.close();

        Scanner scanner = new Scanner(Database.getREGISTRATION());

        String[] tempDataHolder = new String[counter];

        for (int k = 0; k < counter; k++) {
            tempDataHolder[k] = scanner.nextLine();
        }

        scanner.close();

        boolean result = Files.deleteIfExists(Database.getREGISTRATION().toPath());

        if (result) {

            File newFile = Database.getREGISTRATION();

            if (newFile.createNewFile()) {
                try (FileWriter myWriter = new FileWriter(newFile)) {

                    for (String tempData : tempDataHolder) {

                        String[] dataArray = tempData.split(";");

                        if ((currentDate.equals(dfm.parse(dataArray[3])) || currentDate.after(dfm.parse(dataArray[3]))) && dataArray[8].equals("Normal")) {
                            myWriter.write(dataArray[0] + ";");
                            myWriter.write(dataArray[1] + ";");
                            myWriter.write(dataArray[2] + ";");
                            myWriter.write(dataArray[3] + ";");
                            myWriter.write(dataArray[4] + ";");
                            myWriter.write(dataArray[5] + ";");
                            myWriter.write(dataArray[6] + ";");
                            myWriter.write(dataArray[7] + ";");
                            myWriter.write("Completed;");
                            myWriter.write(dataArray[9] + ";");
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
                            myWriter.write("\n");
                        }

                    }
                    myWriter.close();
                } catch (IOException e) {
                    throw new IOException("Problem in myWriter filewriter!");
                }
            }
        }

    }

    public void addRecord(String userName) throws FileNotFoundException {

        this.registeredEvents = new ArrayList<>();

        Scanner sc = new Scanner(Database.getREGISTRATION());

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            String[] arrOfData = data.split(";");

            if (userName.equals(arrOfData[0])) {
                RegisteredEvent registeredEvent = new RegisteredEvent(arrOfData[0], arrOfData[1], arrOfData[2], arrOfData[3], arrOfData[4], arrOfData[5], arrOfData[6], arrOfData[7], arrOfData[8], arrOfData[9]);

                registeredEvents.add(registeredEvent);
            }

        }
        sc.close();
    }

    @Override
    public String toString() {
        return "RegisteredEventList{" + "registeredEvents=" + registeredEvents + '}';
    }

}
