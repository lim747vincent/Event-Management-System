package eventmanagementsystem;

import java.util.regex.Pattern;

public abstract class InputValidation {

    public static boolean login(String userId, String password) {

        String regex = "[^a-zA-Z0-9]";

        Pattern p = Pattern.compile(regex);

        boolean userIdMatcher = !(p.matcher(userId).find());
        boolean passwordMatcher = !(p.matcher(password).find());

        return userIdMatcher && passwordMatcher;
    }

    public static boolean message(String message) {

        String regex = "^[a-zA-Z0-9.,!? ]+$";

        Pattern p = Pattern.compile(regex);

        boolean userIdMatcher = p.matcher(message).find();

        return userIdMatcher;
    }

    public static boolean payment(String bankNo, String cvc, String expiryDate, String eventId, String numParticipants) {
        boolean isExpiryDateValid = false;

        String regexNum = "^[0-9]+$";
        Pattern pNum = Pattern.compile(regexNum);
        boolean isEventIdValid = pNum.matcher(eventId).find();
        boolean isNumParticipantsValid = pNum.matcher(numParticipants).find();
        boolean isBankNoValid = false;
        boolean isCvcValid = false;

        if (cvc.length() == 3 && pNum.matcher(cvc).find()) {
            isCvcValid = true;
        }

        if (bankNo.length() == 8 && pNum.matcher(bankNo).find()) {
            isBankNoValid = true;
        }

        if (expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}")) {
            isExpiryDateValid = true;
        }

        return isBankNoValid && isCvcValid && isExpiryDateValid && isEventIdValid && isNumParticipantsValid;
    }

    public static boolean registration(String userId, String password, String userName, String userEmail) {
        String regex = "[^a-zA-Z0-9]";
        String regexUserName = "^[a-zA-Z ]+$";
        String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        Pattern p = Pattern.compile(regex);
        Pattern pUserName = Pattern.compile(regexUserName);
        Pattern pEmail = Pattern.compile(regexEmail);

        boolean userIdMatcher = !(p.matcher(userId).find());
        boolean passwordMatcher = !(p.matcher(password).find());
        boolean userEmailMatcher = pEmail.matcher(userEmail).find();
        boolean userNameMatcher = pUserName.matcher(userName).find();

        return userIdMatcher && passwordMatcher && userEmailMatcher && userNameMatcher;
    }

    public static boolean updateProfile(String userName, String userEmail) {

        String regexUserName = "^[a-zA-Z ]+$";
        String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        Pattern pUserName = Pattern.compile(regexUserName);
        Pattern pEmail = Pattern.compile(regexEmail);

        boolean userEmailMatcher = pEmail.matcher(userEmail).find();
        boolean userNameMatcher = pUserName.matcher(userName).find();

        return userEmailMatcher && userNameMatcher;
    }

    public static boolean forgotPassword(String userEmail) {

        String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        Pattern pEmail = Pattern.compile(regexEmail);

        boolean userEmailMatcher = pEmail.matcher(userEmail).find();

        return userEmailMatcher;
    }

    public static boolean forgotPassword(String password, String passwordAgain) {
        String regex = "[^a-zA-Z0-9]";

        Pattern p = Pattern.compile(regex);

        boolean passwordMatcher = !(p.matcher(password).find());

        return passwordMatcher;
    }

    public static boolean addSupplier(String companyName, String email, String pricePerPax, String foodDrinkDescription) {
        String regex = "^[a-zA-Z0-9,. ]+$*";
        String regexDescription = "^[a-zA-Z0-9.,!? ]+$*";
        String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        String regexDigits = "^\\d{0,8}[.]?\\d{1,2}$";

        Pattern p = Pattern.compile(regex);
        Pattern pEmail = Pattern.compile(regexEmail);
        Pattern pPrice = Pattern.compile(regexDigits);
        Pattern pDescription = Pattern.compile(regexDescription);

        boolean emailMatcher = pEmail.matcher(email).find();
        boolean companyNameMatcher = p.matcher(companyName).find();
        boolean foodDrinkDescriptionMatcher = pDescription.matcher(foodDrinkDescription).find();
        boolean pricePerPaxMatcher = pPrice.matcher(pricePerPax).find();

        return companyNameMatcher && foodDrinkDescriptionMatcher && emailMatcher && pricePerPaxMatcher;
    }

    public static boolean addSupplier(String companyName, String email, String location, String pricePerDay, String maxCapacity) {
        String regex = "^[a-zA-Z0-9,. ]+$*";
        String regexMaxCapacity = "^[0-9]+$";
        String regexLocation = "^[a-zA-Z0-9,. ]+$*";
        String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        String regexDigits = "^\\d{0,8}[.]?\\d{1,2}$";

        Pattern p = Pattern.compile(regex);
        Pattern pEmail = Pattern.compile(regexEmail);
        Pattern pLocation = Pattern.compile(regexLocation);
        Pattern pMaxCapacity = Pattern.compile(regexMaxCapacity);
        Pattern pPrice = Pattern.compile(regexDigits);

        boolean emailMatcher = pEmail.matcher(email).find();
        boolean companyNameMatcher = p.matcher(companyName).find();
        boolean locationMatcher = pLocation.matcher(location).find();
        boolean maxCapacityMatcher = pMaxCapacity.matcher(maxCapacity).find();
        boolean pricePerDayMatcher = pPrice.matcher(pricePerDay).find();

        return companyNameMatcher && locationMatcher && emailMatcher && maxCapacityMatcher && pricePerDayMatcher;
    }

    public static boolean addSupplier(String companyName, String email, String pricePerDay) {
        String regex = "^[a-zA-Z0-9,. ]+$*";
        String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        String regexDigits = "^\\d{0,8}[.]?\\d{1,2}$";

        Pattern p = Pattern.compile(regex);
        Pattern pEmail = Pattern.compile(regexEmail);
        Pattern pPrice = Pattern.compile(regexDigits);

        boolean emailMatcher = pEmail.matcher(email).find();
        boolean companyNameMatcher = p.matcher(companyName).find();
        boolean pricePerDayMatcher = pPrice.matcher(pricePerDay).find();

        return companyNameMatcher && emailMatcher && pricePerDayMatcher;
    }

    public static boolean editSupplier(String supplierName, String email, String foodDrinkDescription, String pricePerPax) {
        String regex = "^[a-zA-Z0-9,. ]+$*";
        String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        String regexDigits = "^\\d{0,8}[.]?\\d{1,2}$";
        String regexDescription = "^[a-zA-Z0-9.,!? ]+$*";

        Pattern p = Pattern.compile(regex);
        Pattern pEmail = Pattern.compile(regexEmail);
        Pattern pPrice = Pattern.compile(regexDigits);
        Pattern pDescription = Pattern.compile(regexDescription);

        boolean emailMatcher = pEmail.matcher(email).find();
        boolean supplierNameMatcher = p.matcher(supplierName).find();
        boolean pricePerPaxMatcher = pPrice.matcher(pricePerPax).find();
        boolean foodDrinkDescriptionMatcher = pDescription.matcher(foodDrinkDescription).find();

        return supplierNameMatcher && emailMatcher && foodDrinkDescriptionMatcher && pricePerPaxMatcher;
    }

    public static boolean editSupplier(String supplierName, String email, String location, String pricePerDay, String maxCapacity) {
        String regex = "^[a-zA-Z0-9,. ]+$*";
        String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        String regexDigits = "^\\d{0,8}[.]?\\d{1,2}$";
        String regexLocation = "^[a-zA-Z0-9,. ]+$*";
        String regexMaxCapacity = "^[0-9]+$";

        Pattern p = Pattern.compile(regex);
        Pattern pEmail = Pattern.compile(regexEmail);
        Pattern pPrice = Pattern.compile(regexDigits);
        Pattern pLocation = Pattern.compile(regexLocation);
        Pattern pMaxCapacity = Pattern.compile(regexMaxCapacity);

        boolean emailMatcher = pEmail.matcher(email).find();
        boolean supplierNameMatcher = p.matcher(supplierName).find();
        boolean pricePerDayMatcher = pPrice.matcher(pricePerDay).find();
        boolean locationMatcher = pLocation.matcher(location).find();
        boolean maxCapacityMatcher = pMaxCapacity.matcher(maxCapacity).find();

        return supplierNameMatcher && emailMatcher && locationMatcher && pricePerDayMatcher && maxCapacityMatcher;
    }

    public static boolean editSupplier(String supplierName, String email, String pricePerPax) {
        String regex = "^[a-zA-Z0-9,. ]+$*";
        String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        String regexDigits = "^\\d{0,8}[.]?\\d{1,2}$";

        Pattern p = Pattern.compile(regex);
        Pattern pEmail = Pattern.compile(regexEmail);
        Pattern pPrice = Pattern.compile(regexDigits);

        boolean emailMatcher = pEmail.matcher(email).find();
        boolean supplierNameMatcher = p.matcher(supplierName).find();
        boolean pricePerPaxMatcher = pPrice.matcher(pricePerPax).find();

        return supplierNameMatcher && emailMatcher && pricePerPaxMatcher;
    }

    public static boolean addAttendee(String userIdInput, String eventIdInput, String numParticipant) {
        String regexNum = "^[0-9]+$";
        String regex = "[^a-zA-Z0-9]";

        Pattern p = Pattern.compile(regex);
        Pattern pNum = Pattern.compile(regexNum);

        boolean isUserIdMatcherValid = !(p.matcher(userIdInput).find());
        boolean isEventIdValid = pNum.matcher(eventIdInput).find();
        boolean isNumParticipantValid = pNum.matcher(numParticipant).find();

        return isNumParticipantValid && isEventIdValid && isUserIdMatcherValid;
    }

    public static boolean createEvent(String eventName, String eventDate, String eventPrice, String eventMaxCapacity) {
        String regex = "^[a-zA-Z0-9 ]+$*";
        String regexDigits = "^\\d{0,8}[.]?\\d{1,2}$";
        String regexMaxCapacity = "^[0-9]+$";

        Pattern p = Pattern.compile(regex);
        Pattern pPrice = Pattern.compile(regexDigits);
        Pattern pMaxCapacity = Pattern.compile(regexMaxCapacity);

        boolean eventNameMatcher = p.matcher(eventName).find();
        boolean priceMatcher = pPrice.matcher(eventPrice).find();
        boolean maxCapacityMatcher = pMaxCapacity.matcher(eventMaxCapacity).find();
        boolean isEventDateValid = false;

        for (int i = 0; i < eventDate.length(); i++) {
            if ((eventDate.charAt(i) == '0' || eventDate.charAt(i) == '1' || eventDate.charAt(i) == '2' || eventDate.charAt(i) == '3' || eventDate.charAt(i) == '4' || eventDate.charAt(i) == '5' || eventDate.charAt(i) == '6' || eventDate.charAt(i) == '7' || eventDate.charAt(i) == '8' || eventDate.charAt(i) == '9' || eventDate.charAt(i) == '-') && (eventDate.charAt(2) == '-' && eventDate.charAt(5) == '-')) {
                isEventDateValid = true;
            } else {
                isEventDateValid = false;
                break;
            }
        }

        return eventNameMatcher && priceMatcher && maxCapacityMatcher && isEventDateValid;
    }
}
