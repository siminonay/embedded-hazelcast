package com.example.controller;

import com.example.service.HazelcastService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hazelcast")
@RequiredArgsConstructor
public class HazelcastController {

    private final HazelcastService hazelcastService;

    @GetMapping("/{key}")
    public String getValue(@PathVariable String key) {
        return hazelcastService.getValue(key);
    }

    @GetMapping
    public Map<String, String> getAllValues() {
        return hazelcastService.getAll();
    }
}
