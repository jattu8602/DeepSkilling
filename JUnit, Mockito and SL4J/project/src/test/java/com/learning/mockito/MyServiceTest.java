package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MyServiceTest {

    @Mock
    private ExternalApi mockApi;

    @Test
    void testMockAndStub() {
        when(mockApi.getData()).thenReturn("Mock Data");
        MyService service = new MyService(mockApi);
        String result = service.fetchData();
        assertEquals("Mock Data", result);
    }

    @Test
    void testVerifyInteraction() {
        MyService service = new MyService(mockApi);
        service.fetchData();
        verify(mockApi).getData();
    }

    @Test
    void testArgumentMatching() {
        when(mockApi.getData()).thenReturn("Matched");
        MyService service = new MyService(mockApi);
        String result = service.fetchData();
        assertEquals("Matched", result);
        verify(mockApi, times(1)).getData();
    }

    @Test
    void testMultipleReturns() {
        when(mockApi.getData())
            .thenReturn("First")
            .thenReturn("Second");

        MyService service = new MyService(mockApi);
        assertEquals("First", service.fetchData());
        assertEquals("Second", service.fetchData());
    }

    @Test
    void testVerifyInteractionOrder() {
        MyService service = new MyService(mockApi);
        service.fetchData();
        service.fetchData();
        verify(mockApi, times(2)).getData();
    }
}
