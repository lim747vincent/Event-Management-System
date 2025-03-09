package eventmanagementsystem;

public class RegisteredEvent extends Event {

    private String numParticipants;
    private String registrationDate;
    private String checkInStatus;
    private String userId;

    public RegisteredEvent(String userId, String eventId, String name, String date, String price, String location, String numParticipants, String registrationDate, String eventStatus, String checkInStatus) {
        super(name, date, price, location, eventId, eventStatus);
        this.numParticipants = numParticipants;
        this.registrationDate = registrationDate;
        this.checkInStatus = checkInStatus;
        this.userId = userId;
    }

    public RegisteredEvent() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCheckInStatus() {
        return checkInStatus;
    }

    public void setCheckInStatus(String checkInStatus) {
        this.checkInStatus = checkInStatus;
    }

    public String getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(String numParticipants) {
        this.numParticipants = numParticipants;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "RegisteredEvent{" + "numParticipants=" + numParticipants + ", registrationDate=" + registrationDate + '}';
    }

}
