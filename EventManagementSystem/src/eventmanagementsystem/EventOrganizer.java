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
import java.util.Date;
import java.util.Scanner;

public class EventOrganizer extends User {

    private Eve_AttendeeList eve_AttendeeList;
    private SupplierList supplierList;
    private CreatedEventList createdEventList;

    public EventOrganizer(String... args) {
        super(args);
        this.eve_AttendeeList = new Eve_AttendeeList();
        this.supplierList = new SupplierList();
        this.createdEventList = new CreatedEventList();
    }

    public EventOrganizer() {
    }

    public Eve_AttendeeList getEve_AttendeeList() {
        return eve_AttendeeList;
    }

    public void setEve_AttendeeList(Eve_AttendeeList eve_AttendeeList) {
        this.eve_AttendeeList = eve_AttendeeList;
    }

    public SupplierList getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(SupplierList supplierList) {
        this.supplierList = supplierList;
    }

    public CreatedEventList getCreatedEventList() {
        return createdEventList;
    }

    public void setCreatedEventList(CreatedEventList createdEventList) {
        this.createdEventList = createdEventList;
    }

    @Override
    public void showLoginSuccessMsg() {
        System.out.println("\nLogin as event organizer sucessfully!");
    }

    @Override
    public void showRegisterSuccessMsg() {
        System.out.println("\nRegistered as event organizer sucessfully!");
    }

    public void createEvent(String eventName, String eventDate, String eventPrice, String eventMaxCapacity, String supplierIdFoodDrink, String supplierIdVenue, String supplierIdStaff) throws FileNotFoundException, IOException, ParseException {

        boolean isSupplierIdFoodDrinkExist = false;
        boolean isSupplierIdVenueExist = false;
        boolean isSupplierIdStaffExist = false;

        ArrayList<Supplier> suppliers = this.supplierList.getSuppliers();

//        check if supplier exist
        for (int i = 0; i < suppliers.size(); i++) {
            if (suppliers.get(i).getSupplierId().equals(supplierIdFoodDrink) && suppliers.get(i).getCategory().equals("Food drink")) {
                isSupplierIdFoodDrinkExist = true;
            } else if (suppliers.get(i).getSupplierId().equals(supplierIdVenue) && suppliers.get(i).getCategory().equals("Venue")) {
                isSupplierIdVenueExist = true;
            } else if (suppliers.get(i).getSupplierId().equals(supplierIdStaff) && suppliers.get(i).getCategory().equals("Staff")) {
                isSupplierIdStaffExist = true;
            }
        }

        if (isSupplierIdFoodDrinkExist && isSupplierIdVenueExist && isSupplierIdStaffExist) {

//        check if venueSupplier max capacity is over eventMaxCapcity input
            for (int i = 0; i < suppliers.size(); i++) {
                if (suppliers.get(i) instanceof VenueSupplier && suppliers.get(i).getSupplierId().equals(supplierIdVenue)) {
                    VenueSupplier venueSupplier = (VenueSupplier) suppliers.get(i);
                    int numVenueMaxCapacity = Integer.parseInt(venueSupplier.getMaxCapacity());
                    int numEventMaxCapacity = Integer.parseInt(eventMaxCapacity);

                    if (numVenueMaxCapacity <= numEventMaxCapacity) {
                        System.out.println("\nVenue max capacity cannot fulfill event max capacity...Try again");
                        return;
                    }
                }
            }

            DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
            Date currentDate = new Date();

            if (currentDate.after(dfm.parse(eventDate))) {
                System.out.println("\nInvalid event date...Try again");
                return;
            }

//        update in availableEvent.txt
            Scanner sc = new Scanner(Database.getAVAILABLEEVENT());

            int counter = 0;

            while (sc.hasNextLine()) {
                String data = sc.nextLine();

                String[] arrOfData = data.split(";");

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
                    FileWriter myWriter = new FileWriter(newFile);

                    for (String tempData : tempDataHolder) {
                        String[] dataArray = tempData.split(";");

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

//                to get correct venue location info and then only write into available.txt
                    for (int i = 0; i < suppliers.size(); i++) {
                        if (suppliers.get(i) instanceof VenueSupplier && suppliers.get(i).getSupplierId().equals(supplierIdVenue)) {
                            VenueSupplier venueSupplier = (VenueSupplier) suppliers.get(i);

                            myWriter.write(String.valueOf(counter + 1) + ";");
                            myWriter.write(eventName + ";");
                            myWriter.write(eventDate + ";");
                            myWriter.write(eventPrice + ";");
                            myWriter.write("0;");
                            myWriter.write(eventMaxCapacity + ";");
                            myWriter.write(venueSupplier.getLocation() + ";");
                            myWriter.write("Normal;");
                            myWriter.write(this.getUserId() + ";");
                            break;
                        }
                    }

//                to get correct food drink info and then only write into available.txt
                    for (int i = 0; i < suppliers.size(); i++) {
                        if (suppliers.get(i) instanceof FoodDrinkSupplier && suppliers.get(i).getSupplierId().equals(supplierIdFoodDrink)) {
                            FoodDrinkSupplier foodDrinkSupplier = (FoodDrinkSupplier) suppliers.get(i);

                            myWriter.write(foodDrinkSupplier.getCompanyName() + ";");
                            myWriter.write(foodDrinkSupplier.getEmail() + ";");
                            myWriter.write(foodDrinkSupplier.getFoodDrinkDescription() + ";");
                            myWriter.write(foodDrinkSupplier.getPricePerPax() + ";");
                            break;
                        }

                    }

//                to get correct venue info and then only write into available.txt
                    for (int i = 0; i < suppliers.size(); i++) {
                        if (suppliers.get(i) instanceof VenueSupplier && suppliers.get(i).getSupplierId().equals(supplierIdVenue)) {
                            VenueSupplier venueSupplier = (VenueSupplier) suppliers.get(i);

                            myWriter.write(venueSupplier.getCompanyName() + ";");
                            myWriter.write(venueSupplier.getEmail() + ";");
                            myWriter.write(venueSupplier.getLocation() + ";");
                            myWriter.write(venueSupplier.getPricePerDay() + ";");
                            myWriter.write(venueSupplier.getMaxCapacity() + ";");
                            break;
                        }

                    }

//                to get correct staff info and then only write into available.txt
                    for (int i = 0; i < suppliers.size(); i++) {
                        if (suppliers.get(i) instanceof StaffSupplier && suppliers.get(i).getSupplierId().equals(supplierIdStaff)) {
                            StaffSupplier staffSupplier = (StaffSupplier) suppliers.get(i);

                            myWriter.write(staffSupplier.getCompanyName() + ";");
                            myWriter.write(staffSupplier.getEmail() + ";");
                            myWriter.write(staffSupplier.getPricePerDay() + ";");
                            break;
                        }

                    }
                    System.out.println("\nSucessfully create Event!");
                    myWriter.close();
                }
            }
        } else {
            System.out.println("\n\nInvalid input...Try again!");
        }

    }

//    show number of people check in
    public void showNumPeopleCheckedIn(String eventIdInput) {
        boolean isEventIdExist = false;

        // check if event id is valid
        for (int i = 0; i < this.createdEventList.getCreatedEvents().size(); i++) {

            if (eventIdInput.equals(this.createdEventList.getCreatedEvents().get(i).getEventId())) {
                isEventIdExist = true;
                break;
            }
        }

        if (isEventIdExist) {
            int numPeopleCheckIn = 0;

            for (int i = 0; i < this.getEve_AttendeeList().getEve_Attendees().size(); i++) {

                if (this.getEve_AttendeeList().getEve_Attendees().get(i).getEventId().equals(eventIdInput) && this.getEve_AttendeeList().getEve_Attendees().get(i).getCheckInStatus().equals("Checked")) {
                    numPeopleCheckIn = numPeopleCheckIn + 1;
                }
            }

            System.out.println("\nResult: \n");

            for (int i = 0; i < this.createdEventList.getCreatedEvents().size(); i++) {
                if (eventIdInput.equals(this.createdEventList.getCreatedEvents().get(i).getEventId())) {
                    System.out.println("Event id: " + eventIdInput);
                    System.out.println("Event name: " + this.createdEventList.getCreatedEvents().get(i).getName());
                    System.out.println("Event location: " + this.createdEventList.getCreatedEvents().get(i).getLocation());
                    System.out.println("Number of people check-in: " + numPeopleCheckIn);
                    System.out.println("Number people participant: " + this.createdEventList.getCreatedEvents().get(i).getCurrentCapacity() + "/" + this.createdEventList.getCreatedEvents().get(i).getMaxCapacity());
                    System.out.println("Event status: " + this.createdEventList.getCreatedEvents().get(i).getEventStatus());
                    break;
                }

            }

        } else {
            System.out.println("Invalid input...Try again");
        }

    }

    //    show cost, revenue, profit report
    public void showProfitReport(String eventIdInput) {
        boolean isEventIdExist = false;

        // check if event id is valid
        for (int i = 0; i < this.createdEventList.getCreatedEvents().size(); i++) {

            if (eventIdInput.equals(this.createdEventList.getCreatedEvents().get(i).getEventId())) {
                isEventIdExist = true;
                break;
            }
        }

        if (isEventIdExist) {
            double totalRevenue = 0, totalParticipants = 0;
//            calculate total revenue from registration.txt
            for (int i = 0; i < this.getEve_AttendeeList().getEve_Attendees().size(); i++) {
                if (this.getEve_AttendeeList().getEve_Attendees().get(i).getEventId().equals(eventIdInput) && !(this.getEve_AttendeeList().getEve_Attendees().get(i).getEventStatus().equals("Removed"))) {
                    double numParticipantDouble = Double.parseDouble(this.getEve_AttendeeList().getEve_Attendees().get(i).getNumParticipants());
                    double priceDouble = Double.parseDouble(this.getEve_AttendeeList().getEve_Attendees().get(i).getPrice());

                    totalRevenue = totalRevenue + (numParticipantDouble * priceDouble);
                    totalParticipants = totalParticipants + numParticipantDouble;

                }
            }

            System.out.println("\nResult: \n");

            for (int i = 0; i < this.createdEventList.getCreatedEvents().size(); i++) {
                if (eventIdInput.equals(this.createdEventList.getCreatedEvents().get(i).getEventId())) {
                    double staffPriceDouble = Double.parseDouble(this.createdEventList.getCreatedEvents().get(i).getStaffPricePerDay());
                    double venueDoublePrice = Double.parseDouble(this.createdEventList.getCreatedEvents().get(i).getVenuePricePerDay());
                    double foodDrinkDoublePricePerPax = Double.parseDouble(this.createdEventList.getCreatedEvents().get(i).getFoodDrinkPricePerPax());
                    double totalFoodDrinkPrice = foodDrinkDoublePricePerPax * totalParticipants;
                    double totalCost = staffPriceDouble + totalFoodDrinkPrice + venueDoublePrice;
                    double totalProfit = totalRevenue - totalCost;
                    String formattedTotalCost = String.format("%.2f", totalCost);
                    String formattedRevenue = String.format("%.2f", totalRevenue);
                    String formattedProfit = String.format("%.2f", totalProfit);

                    System.out.println("Event id: " + eventIdInput);
                    System.out.println("Event name: " + this.createdEventList.getCreatedEvents().get(i).getName());
                    System.out.println("Total cost: RM " + formattedTotalCost);
                    System.out.println("Total revenue: RM " + formattedRevenue);
                    System.out.println("Total profit: RM " + formattedProfit);
                    break;
                }

            }

        } else {
            System.out.println("Invalid input...Try again");
        }

    }

    @Override
    public String toString() {
        return "EventOrganizer{" + "eve_AttendeeList=" + eve_AttendeeList + ", supplierList=" + supplierList + ", createdEventList=" + createdEventList + '}';
    }

}
