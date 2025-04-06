//package com.SpringBootProject.journalApp.Services;
//
//import com.SpringBootProject.journalApp.Entity.UserEntity;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Collections;
//import java.util.stream.Stream;
//
//public class UserArgumentProvider implements ArgumentsProvider {
//
//
//
//    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
//        return Stream.of(
//               Arguments.of(UserEntity.builder().username("Rohan").password("Rohan").build()),
//                Arguments.of(UserEntity.builder().username("Samarth").password("Samarth").build())
//                );
//
//    }
//}
