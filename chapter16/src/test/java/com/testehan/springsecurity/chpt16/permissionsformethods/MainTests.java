package com.testehan.springsecurity.chpt16.permissionsformethods;


import com.testehan.springsecurity.chpt16.permissionsformethods.service.NameServiceHardCodedData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MainTests {
    @Autowired
    private NameServiceHardCodedData nameServiceHardCodedData;

    @Test
    void testNameServiceWithNoUser() {
        assertThrows(AuthenticationException.class,
                () -> nameServiceHardCodedData.getName());
    }
    @Test
    @WithMockUser(authorities = "read")
    void testNameServiceWithUserButWrongAuthority() {
        assertThrows(AccessDeniedException.class,
                () -> nameServiceHardCodedData.getName());
    }
    @Test
    @WithMockUser(authorities = "write")
    void testNameServiceWithUserButCorrectAuthority() {
        var result = nameServiceHardCodedData.getName();
        assertEquals("Fantastico", result);
    }
}