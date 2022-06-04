package com.developerblog.app.ws.UserService;

import com.developerblog.app.ws.uiModelRequest.UserDetailsRequestModel;
import com.developerblog.app.ws.uiModelResponse.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetails);
    
}
