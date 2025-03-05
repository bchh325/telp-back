package com.example.telpback.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {


    private final UserService userService = new UserService();

    @Test
    void testFunction() {
        String testFunctionValue = userService.testFunction();
    }


}
