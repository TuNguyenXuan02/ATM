package repository;

import entity.UserInfo;

public interface UserInfoInterface {
    UserInfo getUserInfo(String identityCardNumber);

    String getFullName(UserInfo userInfo);

    String getIdentityCardNumber(UserInfo userInfo);
}
