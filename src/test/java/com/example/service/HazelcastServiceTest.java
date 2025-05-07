package com.example.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class HazelcastServiceTest {

    private HazelcastInstance hazelcastInstance;
    private IMap<String, String> mockMap;
    private HazelcastService hazelcastService;

    @BeforeEach
    void setUp() {
        hazelcastInstance = mock(HazelcastInstance.class);
        mockMap = mock(IMap.class);

        when(hazelcastInstance.<String, String>getMap("my-distributed-map")).thenReturn(mockMap);

        hazelcastService = new HazelcastService(hazelcastInstance);
    }

    @Test
    void testPreloadData_isCalledOnceAndInsertsCorrectEntries() {
        verify(mockMap).put("1", "John");
        verify(mockMap).put("2", "Mary");
        verify(mockMap).put("3", "Jane");
    }

    @Test
    void testGetValue_returnsCorrectValue() {
        when(mockMap.get("1")).thenReturn("John");

        String value = hazelcastService.getValue("1");

        assertEquals("John", value);
        verify(mockMap).get("1");
    }

}