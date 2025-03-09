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

public class AvailableEventList {

    private ArrayList<AvailableEvent> availableEvents;

    public AvailableEventList() {
        this.availableEvents = new ArrayList<>();
    }

    public void setAvailableEvents(ArrayList<AvailableEvent> availableEvents) {
        this.availableEvents = availableEvents;
    }

    public ArrayList<AvailableEvent> getAvailableEvents() {
        return availableEvents;
    }

    public void searchEventById(String eventId) {

        System.out.println("\nResult:\n");

        for (int i = 0; i < availableEvents.size(); i++) {
            if (eventId.equals(availableEvents.get(i).getEventId())) {

                System.out.println(toString(availableEvents.get(i).getEventId(), availableEvents.get(i).getName(), availableEvents.get(i).getDate(), availableEvents.get(i).getPrice(), availableEvents.get(i).getCurrentCapacity(), availableEvents.get(i).getMaxCapacity(), availableEvents.get(i).getLocation()));

                break;
            } else if (i == availableEvents.size() - 1) {
                System.out.println("\nEvent selected is not available...Please try again");
            }
        }
    }

    public void viewAllRecordByDate() {
        System.out.println("\nResult:\n");
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");

        Collections.sort(availableEvents, new Comparator<AvailableEvent>() {
            public int compare(AvailableEvent data1, AvailableEvent data2) {

                try {
                    return dfm.parse(data1.getDate()).compareTo(dfm.parse(data2.getDate()));
                } catch (ParseException ex) {
                    Logger.getLogger(AvailableEventList.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 0;

            }
        });

        for (AvailableEvent data : availableEvents) {
            System.out.println(toString(data.getEventId(), data.getName(), data.getDate(), data.getPrice(), data.getCurrentCapacity(), data.getMaxCapacity(), data.getLocation()));
        }

        System.out.println("Total events available: " + availableEvents.size() + "\n");

        System.out.println("Notice:\n"
                + "a. Remember event id to register event \n"
                + "-------------------------------------");

    }

    public void viewAllRecordByPrice() {
        System.out.println("\nResult:\n");

        Collections.sort(availableEvents, new Comparator<AvailableEvent>() {
            public int compare(AvailableEvent data1, AvailableEvent data2) {
                double price1 = Double.parseDouble(data1.getPrice());
                double price2 = Double.parseDouble(data2.getPrice());

                // Compare based on price
                return Double.compare(price1, price2);
            }
        });

        for (AvailableEvent data : availableEvents) {
            System.out.println(toString(data.getEventId(), data.getName(), data.getDate(), data.getPrice(), data.getCurrentCapacity(), data.getMaxCapacity(), data.getLocation()));
        }

        System.out.println("Total events available: " + availableEvents.size() + "\n");

        System.out.println("Notice:\n"
                + "a. Remember event id to register event \n"
                + "-------------------------------------");

    }

    public void addRecord() throws FileNotFoundException {

        this.availableEvents = new ArrayList<>();

        Scanner sc = new Scanner(Database.getAVAILABLEEVENT());

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            String[] arrOfData = data.split(";");

            if (arrOfData[7].equals("Normal")) {
                AvailableEvent availableEvent = new AvailableEvent(arrOfData[0], arrOfData[1], arrOfData[2], arrOfData[3], arrOfData[4], arrOfData[5], arrOfData[6], arrOfData[7], arrOfData[8], arrOfData[9], arrOfData[10], arrOfData[11], arrOfData[12], arrOfData[13], arrOfData[14], arrOfData[15], arrOfData[16], arrOfData[17], arrOfData[18], arrOfData[19], arrOfData[20]);

                availableEvents.add(availableEvent);

            }

        }

        sc.close();

    }

    public static void updateLatestRecord() throws FileNotFoundException, IOException, ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();

        Scanner sc = new Scanner(Database.getAVAILABLEEVENT());

        int counter = 0;

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            counter++;
        }

        sc.close();

        Scanner scanner = new Scanner(Database.getAVAILABLEEVENT());

        String[] tempDataHolder = new String[counter];

        for (int k = 0; k < counter; k++) {
            tempDataHolder[k] = scanner.nextLine();
        }

        scanner.close();

        boolean result = Files.deleteIfExists(Database.getAVAILABLEEVENT().toPath());

        if (result) {

            File newFile = Database.getAVAILABLEEVENT();

            if (newFile.createNewFile()) {
                try (FileWriter myWriter = new FileWriter(newFile)) {

                    for (String tempData : tempDataHolder) {

                        String[] dataArray = tempData.split(";");

                        if ((currentDate.equals(dfm.parse(dataArray[2])) || currentDate.after(dfm.parse(dataArray[2]))) && dataArray[7].equals("Normal")) {
                            myWriter.write(dataArray[0] + ";");
                            myWriter.write(dataArray[1] + ";");
                            myWriter.write(dataArray[2] + ";");
                            myWriter.write(dataArray[3] + ";");
                            myWriter.write(dataArray[4] + ";");
                            myWriter.write(dataArray[5] + ";");
                            myWriter.write(dataArray[6] + ";");
                            myWriter.write("Completed;");
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

    }

    @Override
    public String toString() {
        return "AvailableEventList{" + "availableEvents=" + availableEvents + '}';
    }

    public String toString(String eventId, String eventName, String eventDate, String eventPrice, String currentCapacity, String maxCapacity, String location) {
        double doublePrice = Double.parseDouble(eventPrice);
        String formattedPrice = String.format("%.2f", doublePrice);

        return "Event Id: " + eventId
                + "\nName: " + eventName
                + "\nDate: " + eventDate
                + "\nPrice per pax: RM " + formattedPrice
                + "\nCapacity: " + currentCapacity + "/" + maxCapacity
                + "\nLocation: " + location + "\n";
    }

}
