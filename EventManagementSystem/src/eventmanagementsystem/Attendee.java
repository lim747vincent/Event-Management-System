package eventmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Attendee extends User {

    private AvailableEventList availableEventList;
    private RegisteredEventList registeredEventList;

    public Attendee(String... args) {
        super(args);
        this.availableEventList = new AvailableEventList();
        this.registeredEventList = new RegisteredEventList();
    }

    public Attendee() {

    }

    public AvailableEventList getAvailableEventList() {
        return availableEventList;
    }

    public RegisteredEventList getRegisteredEventList() {
        return registeredEventList;
    }

    public void setAvailableEventList(AvailableEventList availableEventList) {
        this.availableEventList = availableEventList;
    }

    public void setRegisteredEventList(RegisteredEventList registeredEventList) {
        this.registeredEventList = registeredEventList;
    }

    @Override
    public void showLoginSuccessMsg() {
        System.out.println("\nLogin as attendee sucessfully!");
    }

    @Override
    public void showRegisterSuccessMsg() {
        System.out.println("\nRegistered as attendee sucessfully!");
    }

    public void viewMessage(String userId) throws FileNotFoundException {
        int counter = 0;

        System.out.println("Result:");
        Scanner sc = new Scanner(Database.getATTENDEEMAIL());

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            String[] arrOfData = data.split(";");

            if (arrOfData[0].equals(userId)) {
                System.out.println("To user ID: " + arrOfData[0]);
                System.out.println("Event ID: " + arrOfData[1]);
                System.out.println("Message: " + arrOfData[2]);
                System.out.println();

                counter++;
            }

        }

        System.out.println("Total mails: " + counter);

        sc.close();

    }

    public void registerEvent(String eventId, String numParticipants, ArrayList<AvailableEvent> availableEvents) throws FileNotFoundException, IOException {

        double doubleNumParticipants = Double.parseDouble(numParticipants);

        for (int i = 0; i < availableEvents.size(); i++) {
            double currentCapacity = Double.parseDouble(availableEvents.get(i).getCurrentCapacity());
            double maxCapacity = Double.parseDouble(availableEvents.get(i).getMaxCapacity());

            if (eventId.equals(availableEvents.get(i).getEventId()) && ((currentCapacity + doubleNumParticipants) <= maxCapacity) && doubleNumParticipants != 0) {

                Scanner sc = new Scanner(Database.getREGISTRATION());

                int counter = 0;

                while (sc.hasNextLine()) {
                    String data = sc.nextLine();

                    String[] arrOfData = data.split(";");

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

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                    Date date = new Date();

                    if (newFile.createNewFile()) {
                        try (FileWriter myWriter = new FileWriter(newFile)) {

                            for (String tempData : tempDataHolder) {
                                String[] dataArray = tempData.split(";");

                                for (String data : dataArray) {
                                    myWriter.write(data + ";");
                                }
                                myWriter.write("\n");
                            }

                            myWriter.write(super.getUserId() + ";");
                            myWriter.write(availableEvents.get(i).getEventId() + ";");
                            myWriter.write(availableEvents.get(i).getName() + ";");
                            myWriter.write(availableEvents.get(i).getDate() + ";");
                            myWriter.write(availableEvents.get(i).getPrice() + ";");
                            myWriter.write(availableEvents.get(i).getLocation() + ";");
                            myWriter.write(numParticipants + ";");
                            myWriter.write(formatter.format(date) + ";");
                            myWriter.write("Normal;");
                            myWriter.write("Unchecked;");
                            myWriter.close();

                        } catch (IOException e) {
                            throw new IOException("Problem in myWriter filewriter!");
                        }

                    }

//                    update availableeventlist
                    Scanner scan = new Scanner(Database.getAVAILABLEEVENT());

                    int count = 0;

                    while (scan.hasNextLine()) {
                        String data = scan.nextLine();

                        String[] arrOfData = data.split(";");

                        count++;
                    }

                    scan.close();

                    Scanner tempScan = new Scanner(Database.getAVAILABLEEVENT());

                    String[] tempHolder = new String[count];

                    for (int k = 0; k < count; k++) {
                        tempHolder[k] = tempScan.nextLine();
                    }

                    tempScan.close();

                    boolean isDeleted = Files.deleteIfExists(Database.getAVAILABLEEVENT().toPath());

                    if (isDeleted) {
                        newFile = Database.getAVAILABLEEVENT();

                        if (newFile.createNewFile()) {
                            try (FileWriter myWriter = new FileWriter(newFile)) {

                                for (String tempData : tempHolder) {

                                    String[] dataArray = tempData.split(";");

                                    if (dataArray[0].equals(eventId)) {

                                        Integer intCurrentCapacity = Integer.valueOf(dataArray[4]);
                                        Integer intNumParticipant = Integer.valueOf(numParticipants);

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

                                myWriter.close();
                            } catch (IOException e) {
                                throw new IOException("Problem in myWriter filewriter!");
                            }
                        }
                    }

                    double doublePrice = Double.parseDouble(availableEvents.get(i).getPrice());

                    System.out.println("\nEvent registration succeed!");
                    System.out.println("\nRemember to show your user Id to check in the event");
                    System.out.println("\n|*** Receipt ***| \n");
                    System.out.println("Purchaser: " + super.getName());

                    System.out.println("Event Id: " + availableEvents.get(i).getEventId());
                    System.out.println("Event Name: " + availableEvents.get(i).getName());
                    System.out.println("Event Date: " + availableEvents.get(i).getDate());
                    System.out.println("Number of Participants: " + numParticipants);
                    System.out.printf("Total Price : RM %.2f \n", (doublePrice * doubleNumParticipants));
                    System.out.println("Registration Date: " + formatter.format(date) + "\n");
                }

                break;
            } else if (i == availableEvents.size() - 1) {
                System.out.println("\nInvalid input...Please try again");
            }
        }
    }

    @Override
    public String toString() {
        return "Attendee{" + "availableEventList=" + availableEventList + ", registeredEventList=" + registeredEventList + '}';
    }

}
