package com.example.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class HazelcastService {

    private final HazelcastInstance hazelcastInstance;

    public HazelcastService(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
        preloadData();
    }

    private void preloadData() {
        IMap<String, String> map = hazelcastInstance.getMap("my-distributed-map");
        map.put("1", "John");
        map.put("2", "Mary");
        map.put("3", "Jane");
    }

    public String getValue(String key) {
        IMap<String, String> map = hazelcastInstance.getMap("my-distributed-map");
        return map.get(key);
    }

    public Map<String, String> getAll() {
        log.info("getAll() Getting all values from hazelcast.");
        return hazelcastInstance.getMap("my-distributed-map");
    }

}