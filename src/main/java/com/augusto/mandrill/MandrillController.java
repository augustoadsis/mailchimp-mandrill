package com.augusto.mandrill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mandrill")
@Slf4j
public class MandrillController {

    @Autowired
    MandrillService mandrillService;

    @GetMapping("/send")
    public ResponseEntity<Object> ping() {
        ResponseEntity<Object> response = mandrillService.send();
        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
    }

}
