package eventmanagementsystem;

import java.io.File;

public abstract class Database {

    private final static File ATTENDEE = new File("./src/eventmanagementsystem/attendee.txt");

    private final static File RECOVERYCODE = new File("./src/mailbox/recoveryCode.txt");

    private final static File AVAILABLEEVENT = new File("./src/eventmanagementsystem/availableEvent.txt");

    private final static File REGISTRATION = new File("./src/eventmanagementsystem/registration.txt");

    private final static File EVENTORGANIZER = new File("./src/eventmanagementsystem/eventOrganizer.txt");

    private final static File ATTENDEEMAIL = new File("./src/mailbox/attendeeMail.txt");

    private final static File SUPPLIER = new File("./src/eventmanagementsystem/supplier.txt");

    public static File getSUPPLIER() {
        return SUPPLIER;
    }

    public static File getAVAILABLEEVENT() {
        return AVAILABLEEVENT;
    }

    public static File getREGISTRATION() {
        return REGISTRATION;
    }

    public static File getATTENDEE() {
        return ATTENDEE;
    }

    public static File getRECOVERYCODE() {
        return RECOVERYCODE;
    }

    public static File getEVENTORGANIZER() {
        return EVENTORGANIZER;
    }

    public static File getATTENDEEMAIL() {
        return ATTENDEEMAIL;
    }

}
