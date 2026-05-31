package com.learning.spring;

import com.learning.spring.entity.User;
import com.learning.spring.repository.UserRepository;
import com.learning.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserByIdFound() {
        User mockUser = new User(1L, "John");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.getUserById(1L);
        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(userService.getUserById(99L));
    }

    @Test
    void testGetUserByIdOrThrow() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(java.util.NoSuchElementException.class,
            () -> userService.getUserByIdOrThrow(99L));
    }

    @Test
    void testFindUsersByName() {
        when(userRepository.findByName("John"))
            .thenReturn(java.util.List.of(new User(1L, "John")));
        assertEquals(1, userService.findUsersByName("John").size());
    }
}
