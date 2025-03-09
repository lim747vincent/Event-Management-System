package eventmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class SupplierList {

    private ArrayList<Supplier> suppliers;

    public SupplierList() {
        this.suppliers = new ArrayList<>();
    }

    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(ArrayList<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    //add staff supplier
    public void addSupplier(String eventOrganizerId, String companyName, String email, String pricePerDay) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(Database.getSUPPLIER());

        int counter = 0;

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            counter++;
        }

        sc.close();

        Scanner scanner = new Scanner(Database.getSUPPLIER());

        String[] tempDataHolder = new String[counter];

        for (int k = 0; k < counter; k++) {
            tempDataHolder[k] = scanner.nextLine();
        }

        scanner.close();

        boolean result = Files.deleteIfExists(Database.getSUPPLIER().toPath());

        if (result) {

            File newFile = Database.getSUPPLIER();
            boolean writeOnce = false;

            if (newFile.createNewFile()) {

                try (FileWriter myWriter = new FileWriter(newFile)) {
                    int counterSupplierId = 1;

                    for (String tempData : tempDataHolder) {
                        String[] dataArray = tempData.split(";");
                        int numberData = Integer.valueOf(dataArray[1]);
                        if ((numberData != counterSupplierId) && eventOrganizerId.equals(dataArray[0]) && writeOnce == false) {
                            myWriter.write(eventOrganizerId + ";");
                            myWriter.write(String.valueOf(counterSupplierId) + ";");
                            myWriter.write(companyName + ";");
                            myWriter.write("Staff;");
                            myWriter.write(email + ";");
                            myWriter.write(pricePerDay + ";");
                            myWriter.write("\n");
                            for (String data : dataArray) {
                                myWriter.write(data + ";");
                            }
                            myWriter.write("\n");
                            writeOnce = true;
                            counterSupplierId++;
                            System.out.println("Sucessfully added new supplier!");

                        } else if (!eventOrganizerId.equals(dataArray[0])) {
                            for (String data : dataArray) {
                                myWriter.write(data + ";");
                            }
                            myWriter.write("\n");
                        } else {
                            counterSupplierId++;

                            for (String data : dataArray) {
                                myWriter.write(data + ";");
                            }
                            myWriter.write("\n");

                        }
                    }

                    if (writeOnce == false) {
                        myWriter.write(eventOrganizerId + ";");
                        myWriter.write(String.valueOf(counterSupplierId) + ";");
                        myWriter.write(companyName + ";");
                        myWriter.write("Staff;");
                        myWriter.write(email + ";");
                        myWriter.write(pricePerDay + ";");
                        myWriter.write("\n");

                        System.out.println("Sucessfully added new supplier!");
                    }
                    myWriter.close();
                } catch (IOException e) {
                    throw new IOException("Problem in myWriter filewriter!");
                }

            }
        }
    }

    //add venue supplier
    public void addSupplier(String eventOrganizerId, String companyName, String email, String location, String pricePerDay, String maxCapacity) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(Database.getSUPPLIER());

        int counter = 0;

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            counter++;
        }

        sc.close();

        Scanner scanner = new Scanner(Database.getSUPPLIER());

        String[] tempDataHolder = new String[counter];

        for (int k = 0; k < counter; k++) {
            tempDataHolder[k] = scanner.nextLine();
        }

        scanner.close();

        boolean result = Files.deleteIfExists(Database.getSUPPLIER().toPath());

        if (result) {

            File newFile = Database.getSUPPLIER();
            boolean writeOnce = false;

            if (newFile.createNewFile()) {

                try (FileWriter myWriter = new FileWriter(newFile)) {
                    int counterSupplierId = 1;

                    for (String tempData : tempDataHolder) {
                        String[] dataArray = tempData.split(";");
                        int numberData = Integer.valueOf(dataArray[1]);
                        if ((numberData != counterSupplierId) && eventOrganizerId.equals(dataArray[0]) && writeOnce == false) {
                            myWriter.write(eventOrganizerId + ";");
                            myWriter.write(String.valueOf(counterSupplierId) + ";");
                            myWriter.write(companyName + ";");
                            myWriter.write("Venue;");
                            myWriter.write(email + ";");
                            myWriter.write(location + ";");
                            myWriter.write(pricePerDay + ";");
                            myWriter.write(maxCapacity + ";");
                            myWriter.write("\n");
                            for (String data : dataArray) {
                                myWriter.write(data + ";");
                            }
                            myWriter.write("\n");
                            writeOnce = true;
                            counterSupplierId++;
                            System.out.println("Sucessfully added new supplier!");

                        } else if (!eventOrganizerId.equals(dataArray[0])) {
                            for (String data : dataArray) {
                                myWriter.write(data + ";");
                            }
                            myWriter.write("\n");
                        } else {
                            counterSupplierId++;

                            for (String data : dataArray) {
                                myWriter.write(data + ";");
                            }
                            myWriter.write("\n");

                        }
                    }

                    if (writeOnce == false) {
                        myWriter.write(eventOrganizerId + ";");
                        myWriter.write(String.valueOf(counterSupplierId) + ";");
                        myWriter.write(companyName + ";");
                        myWriter.write("Venue;");
                        myWriter.write(email + ";");
                        myWriter.write(location + ";");
                        myWriter.write(pricePerDay + ";");
                        myWriter.write(maxCapacity + ";");
                        myWriter.write("\n");

                        System.out.println("Sucessfully added new supplier!");
                    }
                    myWriter.close();
                } catch (IOException e) {
                    throw new IOException("Problem in myWriter filewriter!");
                }

            }
        }
    }

//add fooddrink supplier
    public void addSupplier(String eventOrganizerId, String foodDrinkDescription, String pricePerPax, String companyName, String email) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(Database.getSUPPLIER());

        int counter = 0;

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            counter++;
        }

        sc.close();

        Scanner scanner = new Scanner(Database.getSUPPLIER());

        String[] tempDataHolder = new String[counter];

        for (int k = 0; k < counter; k++) {
            tempDataHolder[k] = scanner.nextLine();
        }

        scanner.close();

        boolean result = Files.deleteIfExists(Database.getSUPPLIER().toPath());

        if (result) {

            File newFile = Database.getSUPPLIER();
            boolean writeOnce = false;

            if (newFile.createNewFile()) {

                try (FileWriter myWriter = new FileWriter(newFile)) {
                    int counterSupplierId = 1;

                    for (String tempData : tempDataHolder) {
                        String[] dataArray = tempData.split(";");
                        int numberData = Integer.valueOf(dataArray[1]);
                        if ((numberData != counterSupplierId) && eventOrganizerId.equals(dataArray[0]) && writeOnce == false) {

                            myWriter.write(eventOrganizerId + ";");
                            myWriter.write(String.valueOf(counterSupplierId) + ";");
                            myWriter.write(companyName + ";");
                            myWriter.write("Food drink;");
                            myWriter.write(email + ";");
                            myWriter.write(foodDrinkDescription + ";");
                            myWriter.write(pricePerPax + ";");
                            myWriter.write("\n");
                            for (String data : dataArray) {
                                myWriter.write(data + ";");
                            }
                            myWriter.write("\n");
                            writeOnce = true;
                            counterSupplierId++;

                            System.out.println("Sucessfully added new supplier!");

                        } else if (!eventOrganizerId.equals(dataArray[0])) {
                            for (String data : dataArray) {
                                myWriter.write(data + ";");
                            }
                            myWriter.write("\n");
                        } else {
                            counterSupplierId++;

                            for (String data : dataArray) {
                                myWriter.write(data + ";");
                            }
                            myWriter.write("\n");

                        }

                    }

                    if (writeOnce == false) {
                        myWriter.write(eventOrganizerId + ";");
                        myWriter.write(String.valueOf(counterSupplierId) + ";");
                        myWriter.write(companyName + ";");
                        myWriter.write("Food drink;");
                        myWriter.write(email + ";");
                        myWriter.write(foodDrinkDescription + ";");
                        myWriter.write(pricePerPax + ";");
                        myWriter.write("\n");

                        System.out.println("Sucessfully added new supplier!");
                    }
                    myWriter.close();
                } catch (IOException e) {
                    throw new IOException("Problem in myWriter filewriter!");
                }

            }

        }
    }

    public void removeRecord(String supplierId, String userId) throws FileNotFoundException, IOException {
        boolean isSupplierIdExist = false;

        Scanner scSupplier = new Scanner(Database.getSUPPLIER());

        while (scSupplier.hasNextLine()) {
            String data = scSupplier.nextLine();

            String[] arrOfData = data.split(";");

            if (arrOfData[1].equals(supplierId) && arrOfData[0].equals(userId)) {
                isSupplierIdExist = true;
                break;
            }
        }

        scSupplier.close();

        if (isSupplierIdExist) {
            Scanner sc = new Scanner(Database.getSUPPLIER());

            int counter = 0;

            while (sc.hasNextLine()) {
                String data = sc.nextLine();

                counter++;

            }

            sc.close();

            Scanner scanner = new Scanner(Database.getSUPPLIER());

            String[] tempDataHolder = new String[counter];

            for (int l = 0; l < counter; l++) {
                tempDataHolder[l] = scanner.nextLine();
            }

            scanner.close();

            boolean result = Files.deleteIfExists(Database.getSUPPLIER().toPath());

            if (result) {
                File newFile = Database.getSUPPLIER();

                if (newFile.createNewFile()) {
                    try (FileWriter myWriter = new FileWriter(newFile)) {

                        for (String tempData : tempDataHolder) {
                            String[] dataArray = tempData.split(";");

                            if (dataArray[0].equals(userId) && dataArray[1].equals(supplierId)) {
                                System.out.println("Sucessfully deleted supplier!");
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

//    edit fooddrink supplier
    public void editRecord(String userId, String supplierId, String supplierName, String email, String foodDrinkDescription, String pricePerPax) throws FileNotFoundException, IOException {
        boolean isSupplierIdExist = false;

        Scanner scSupplier = new Scanner(Database.getSUPPLIER());

        while (scSupplier.hasNextLine()) {
            String data = scSupplier.nextLine();

            String[] arrOfData = data.split(";");

            if (arrOfData[1].equals(supplierId) && arrOfData[0].equals(userId) && arrOfData[3].equals("Food drink")) {
                isSupplierIdExist = true;
                break;
            }
        }
        scSupplier.close();

        if (isSupplierIdExist) {
            Scanner sc = new Scanner(Database.getSUPPLIER());

            int counter = 0;

            while (sc.hasNextLine()) {
                String data = sc.nextLine();

                counter++;

            }

            sc.close();

            Scanner scanner = new Scanner(Database.getSUPPLIER());

            String[] tempDataHolder = new String[counter];

            for (int l = 0; l < counter; l++) {
                tempDataHolder[l] = scanner.nextLine();
            }

            scanner.close();

            boolean result = Files.deleteIfExists(Database.getSUPPLIER().toPath());

            if (result) {
                File newFile = Database.getSUPPLIER();

                if (newFile.createNewFile()) {
                    try (FileWriter myWriter = new FileWriter(newFile)) {

                        for (String tempData : tempDataHolder) {
                            String[] dataArray = tempData.split(";");

                            if (dataArray[0].equals(userId) && dataArray[1].equals(supplierId)) {
                                myWriter.write(dataArray[0] + ";");
                                myWriter.write(dataArray[1] + ";");
                                myWriter.write(supplierName + ";");
                                myWriter.write("Food drink;");
                                myWriter.write(email + ";");
                                myWriter.write(foodDrinkDescription + ";");
                                myWriter.write(pricePerPax + ";");
                                myWriter.write("\n");
                            } else {
                                for (String dat : dataArray) {
                                    myWriter.write(dat + ";");
                                }
                                myWriter.write("\n");
                            }

                        }

                        System.out.println("Sucessfully edited supplier!");
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

//    edit venue supplier
    public void editRecord(String userId, String supplierId, String supplierName, String email, String location, String pricePerDay, String maxCapacity) throws FileNotFoundException, IOException {
        boolean isSupplierIdExist = false;

        Scanner scSupplier = new Scanner(Database.getSUPPLIER());

        while (scSupplier.hasNextLine()) {
            String data = scSupplier.nextLine();

            String[] arrOfData = data.split(";");

            if (arrOfData[1].equals(supplierId) && arrOfData[0].equals(userId) && arrOfData[3].equals("Venue")) {
                isSupplierIdExist = true;
                break;
            }
        }
        scSupplier.close();

        if (isSupplierIdExist) {
            Scanner sc = new Scanner(Database.getSUPPLIER());

            int counter = 0;

            while (sc.hasNextLine()) {
                String data = sc.nextLine();

                counter++;

            }

            sc.close();

            Scanner scanner = new Scanner(Database.getSUPPLIER());

            String[] tempDataHolder = new String[counter];

            for (int l = 0; l < counter; l++) {
                tempDataHolder[l] = scanner.nextLine();
            }

            scanner.close();

            boolean result = Files.deleteIfExists(Database.getSUPPLIER().toPath());

            if (result) {
                File newFile = Database.getSUPPLIER();

                if (newFile.createNewFile()) {
                    try (FileWriter myWriter = new FileWriter(newFile)) {

                        for (String tempData : tempDataHolder) {
                            String[] dataArray = tempData.split(";");

                            if (dataArray[0].equals(userId) && dataArray[1].equals(supplierId)) {
                                myWriter.write(dataArray[0] + ";");
                                myWriter.write(dataArray[1] + ";");
                                myWriter.write(supplierName + ";");
                                myWriter.write("Venue;");
                                myWriter.write(email + ";");
                                myWriter.write(location + ";");
                                myWriter.write(pricePerDay + ";");
                                myWriter.write(maxCapacity + ";");
                                myWriter.write("\n");
                            } else {
                                for (String dat : dataArray) {
                                    myWriter.write(dat + ";");
                                }
                                myWriter.write("\n");
                            }

                        }
                        myWriter.close();
                        System.out.println("Sucessfully edited supplier!");

                    } catch (IOException e) {
                        throw new IOException("Problem in myWriter filewriter!");
                    }
                }
            }

        } else {
            System.out.println("Wrong input...Try again later");
        }
    }

    //    edit fooddrink supplier
    public void editRecord(String userId, String supplierId, String supplierName, String email, String pricePerDay) throws FileNotFoundException, IOException {
        boolean isSupplierIdExist = false;

        Scanner scSupplier = new Scanner(Database.getSUPPLIER());

        while (scSupplier.hasNextLine()) {
            String data = scSupplier.nextLine();

            String[] arrOfData = data.split(";");

            if (arrOfData[1].equals(supplierId) && arrOfData[0].equals(userId) && arrOfData[3].equals("Staff")) {
                isSupplierIdExist = true;
                break;
            }
        }
        scSupplier.close();

        if (isSupplierIdExist) {
            Scanner sc = new Scanner(Database.getSUPPLIER());

            int counter = 0;

            while (sc.hasNextLine()) {
                String data = sc.nextLine();

                counter++;

            }

            sc.close();

            Scanner scanner = new Scanner(Database.getSUPPLIER());

            String[] tempDataHolder = new String[counter];

            for (int l = 0; l < counter; l++) {
                tempDataHolder[l] = scanner.nextLine();
            }

            scanner.close();

            boolean result = Files.deleteIfExists(Database.getSUPPLIER().toPath());

            if (result) {
                File newFile = Database.getSUPPLIER();

                if (newFile.createNewFile()) {
                    try (FileWriter myWriter = new FileWriter(newFile)) {

                        for (String tempData : tempDataHolder) {
                            String[] dataArray = tempData.split(";");

                            if (dataArray[0].equals(userId) && dataArray[1].equals(supplierId)) {
                                myWriter.write(dataArray[0] + ";");
                                myWriter.write(dataArray[1] + ";");
                                myWriter.write(supplierName + ";");
                                myWriter.write("Staff;");
                                myWriter.write(email + ";");
                                myWriter.write(pricePerDay + ";");
                                myWriter.write("\n");
                            } else {
                                for (String dat : dataArray) {
                                    myWriter.write(dat + ";");
                                }
                                myWriter.write("\n");
                            }

                        }

                        System.out.println("Sucessfully edited supplier!");
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

    public void viewAllRecord() {
        System.out.println("\nResult:\n");
        for (Supplier supplier : suppliers) {
            System.out.println(supplier.toString());
            System.out.println("");
        }

        System.out.println("Notice:\n"
                + "a. Remember supplier id to perform edit/remove action\n"
                + "-------------------------------------");
    }

    public void viewAllRecordByCategory(String categoryInput) {
        String category = null;
        if (categoryInput.equals("1")) {
            category = "Food drink";
        } else if (categoryInput.equals("2")) {
            category = "Venue";
        } else if (categoryInput.equals("3")) {
            category = "Staff";
        }

        System.out.println("\nResult:\n");
        for (Supplier supplier : suppliers) {
            if (supplier.getCategory().equals(category)) {
                System.out.println(supplier.toString());
                System.out.println("");
            }
        }

        System.out.println("Notice:\n"
                + "a. Remember supplier id to perform edit/remove action\n"
                + "-------------------------------------");
    }

    public void addRecord(String eventOrganizerId) throws FileNotFoundException {

        this.suppliers = new ArrayList<>();

        Scanner sc = new Scanner(Database.getSUPPLIER());

        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            String[] arrOfData = data.split(";");

            if (eventOrganizerId.equals(arrOfData[0]) && arrOfData[3].equals("Food drink")) {
                Supplier supplier = new FoodDrinkSupplier(arrOfData[5], arrOfData[6], arrOfData[2], arrOfData[3], arrOfData[4], arrOfData[1]);

                suppliers.add(supplier);
            } else if (eventOrganizerId.equals(arrOfData[0]) && arrOfData[3].equals("Venue")) {
                Supplier supplier = new VenueSupplier(arrOfData[5], arrOfData[6], arrOfData[7], arrOfData[2], arrOfData[3], arrOfData[4], arrOfData[1]);

                suppliers.add(supplier);
            } else if (eventOrganizerId.equals(arrOfData[0]) && arrOfData[3].equals("Staff")) {
                Supplier supplier = new StaffSupplier(arrOfData[5], arrOfData[2], arrOfData[3], arrOfData[4], arrOfData[1]);

                suppliers.add(supplier);
            }

        }

        sc.close();
    }

    @Override
    public String toString() {
        return "SupplierList{" + "suppliers=" + suppliers + '}';
    }

}
