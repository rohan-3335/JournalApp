package com.SpringBootProject.journalApp.services;

import ch.qos.logback.classic.Logger;
import com.SpringBootProject.journalApp.Entity.UserEntity;
import com.SpringBootProject.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;


    //logger without using anotation, by creating variabe(if we use Slf4j annotation over the class then no need to initialize the variable for logger manually)
//    private static  final  Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);



    // ✅ Inject PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserEntity entry) {
//        entry.setPassword(passwordEncoder.encode(entry.getPassword()));  // ✅ Hash password before saving
//        entry.setRoles(Arrays.asList("USER"));
        userRepository.save(entry);
    }
    public boolean saveNewUser(UserEntity user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
//            logger.error("Entry without unique username {}",user.getUsername(),e);      used when manually initialized Logger
            log.error("Entry without unique username {}",user.getUsername(),e.getMessage());
            log.trace("Entry without unique username {}",user.getUsername(),e.getMessage());
            log.info("Entry without unique username {}",user.getUsername(),e.getMessage());
            log.debug("Entry without unique username {}",user.getUsername(),e.getMessage());
            log.warn("Entry without unique username {}",user.getUsername(),e.getMessage());
           return  false;
        }

    }

    public void saveAdmin(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);

    }

    public List<UserEntity> getall(){
       return userRepository.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id){
        return userRepository.findById(id);
    }
    public void deleteEntry(ObjectId id){
        userRepository.deleteById(id);
    }

    public UserEntity findbyusername(String username){
        return userRepository.findByUsername(username);
    }


}
