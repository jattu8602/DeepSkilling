package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    private Repository mockRepository;

    @Test
    void testServiceWithMockRepository() {
        when(mockRepository.getData()).thenReturn("Mock Data");
        Service service = new Service(mockRepository);
        String result = service.processData();
        assertEquals("Processed Mock Data", result);
    }

    @Test
    void testMultipleReturnValues() {
        when(mockRepository.getData())
            .thenReturn("First Mock Data")
            .thenReturn("Second Mock Data");

        Service service = new Service(mockRepository);
        String firstResult = service.processData();
        String secondResult = service.processData();
        assertEquals("Processed First Mock Data", firstResult);
        assertEquals("Processed Second Mock Data", secondResult);
    }
}
