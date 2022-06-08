package com.appdeveloperblog.photoapp.users.PhotoAppDiscoveryUsers.servise;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appdeveloperblog.photoapp.users.PhotoAppDiscoveryUsers.data.UserEntity;
import com.appdeveloperblog.photoapp.users.PhotoAppDiscoveryUsers.data.UserRepository;
import com.appdeveloperblog.photoapp.users.PhotoAppDiscoveryUsers.shared.UserDto;

@Service
public class UserServiceImpl implements UsersService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //AutoWired in the UserRepository class
    @Autowired
    public UserServiceImpl(UserRepository userRepository , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userdetails) {

        userdetails.setUserId(UUID.randomUUID().toString());
        userdetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userdetails.getPassword()));

        // Create an instance of ModelMapper to map/copy the data from the UserDto object
        // to the UserEntity object
        ModelMapper modelMapper = new ModelMapper();

        // Match the properties of the source and destination objects Exactly as it is
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // use ModelMapper to map/copy the UserDto object to UserEntity object
        UserEntity userEntity = modelMapper.map(userdetails, UserEntity.class);

        // Replaced with line 32 above
        // userEntity.setEncryptedPassword("test");

        //Save the data to the database
        userRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
        return returnValue;
    }

}
