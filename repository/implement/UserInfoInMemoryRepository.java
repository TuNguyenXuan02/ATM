package repository.implement;

import entity.UserInfo;
import repository.DatabaseInMemory;
import repository.UserInfoInterface;

public class UserInfoInMemoryRepository implements UserInfoInterface {
    @Override
    public UserInfo getUserInfo(String identityCardNumber) {
        for (UserInfo userInfo : DatabaseInMemory.getUserInfoList()) {
            if (userInfo.getIdentityCardNumber().equals(identityCardNumber)) {
                return userInfo;
            }
        }
        return null;
    }

    @Override
    public String getFullName(UserInfo userInfo) {
        return userInfo.getFullName();
    }

    @Override
    public String getIdentityCardNumber(UserInfo userInfo) {
        return userInfo.getIdentityCardNumber();
    }
}
