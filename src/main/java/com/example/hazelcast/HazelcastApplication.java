package com.example.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class HazelcastApplication {

	public static void main(String[] args) {

		Config myClusterConfig = new Config();
		myClusterConfig.setClusterName("my-cluster");

		//initialize hazelcast server/instance
		HazelcastInstance hz = Hazelcast.newHazelcastInstance(myClusterConfig);
		HazelcastInstance hz2 = Hazelcast.newHazelcastInstance(myClusterConfig);
		HazelcastInstance hz3 = Hazelcast.newHazelcastInstance(myClusterConfig);

		//create a simple map
		Map<String, String> map = hz.getMap("my-distributed-map");
		map.put("1", "John");
		map.put("2", "Mary");
		map.put("3", "Jane");

		//print values to console
		System.out.println(map.get("1"));
		System.out.println(map.get("2"));
		System.out.println(map.get("3"));


		SpringApplication.run(HazelcastApplication.class, args);
	}

}
