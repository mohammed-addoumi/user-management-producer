package com.usermanagement.usermanagementproducer.mappers;

import com.usermanagement.usermanagementproducer.events.UserEvent;
import com.usermanagement.usermanagementproducer.models.User;

public class UserEventMapper {

    private UserEventMapper(){}

    public static UserEvent mapUserToUserEvent(User user){
        return UserEvent.newBuilder()
                .setUserId(user.getUserId())
                .setUserName(user.getUserName())
                .setUserEmail(user.getUserEmail())
                .setPassword(user.getPassword())
                .build();

    }
}
