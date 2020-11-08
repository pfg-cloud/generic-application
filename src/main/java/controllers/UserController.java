package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.InstrumentationAgent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.instrument.Instrumentation;

import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {


    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) throws JsonProcessingException {
        long start = System.currentTimeMillis();
        ObjectMapper om = new ObjectMapper();
        byte[] bytes = om.writeValueAsBytes(user);
        /*Here we're going to insert some db operation and capture some data related to it*/
        log.info(String.format("User %s bytes size saved in db (alternative)", bytes.length));
        long finish = System.currentTimeMillis();
        log.info(String.format("It took %s ms to process this request", finish - start));
        return ResponseEntity.ok().build();
    }
}
