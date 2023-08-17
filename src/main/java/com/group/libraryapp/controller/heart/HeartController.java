package com.group.libraryapp.controller.heart;

import com.group.libraryapp.domain.heart.Heart;
import com.group.libraryapp.dto.heart.request.HeartRequest;
import com.group.libraryapp.service.heart.HeartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartController {
    private final HeartService heartService;

    public HeartController(HeartService heartService) {
        this.heartService = heartService;
    }

    @PostMapping("/heart/save")
    public ResponseEntity<HeartRequest> saveHeart(@RequestBody HeartRequest request) {
        heartService.saveHeart(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @PostMapping("/heart/delete")
    public ResponseEntity<HeartRequest> deleteHeart(@RequestBody HeartRequest request) {
        heartService.deleteHeart(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
}
