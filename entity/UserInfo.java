package entity;

public class UserInfo {

    private final String identityCardNumber;
    private final String fullName;

    public UserInfo(String identityCardNumber, String fullName) {
        this.identityCardNumber = identityCardNumber;
        this.fullName = fullName;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public String getFullName() {
        return fullName;
    }

}
