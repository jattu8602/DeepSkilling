package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NetworkServiceTest {

    @Mock
    private NetworkClient mockNetworkClient;

    @Test
    void testServiceWithMockNetworkClient() {
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");
        NetworkService networkService = new NetworkService(mockNetworkClient);
        String result = networkService.connectToServer();
        assertEquals("Connected to Mock Connection", result);
    }
}
