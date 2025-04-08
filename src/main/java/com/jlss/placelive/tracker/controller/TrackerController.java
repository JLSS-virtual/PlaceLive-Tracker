package com.jlss.placelive.tracker.controller;

import com.jlss.placelive.commonlib.dto.ResponseDto;
import com.jlss.placelive.commonlib.dto.ResponseListDto;
import com.jlss.placelive.tracker.dto.NotificationDto;
import com.jlss.placelive.tracker.entity.Tracker;
import com.jlss.placelive.tracker.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tracker")
@Validated
public class TrackerController {

    @Autowired
    private TrackerService trackerService;
    /**
     * Creating a high level-logic to handle incoming requests of tracking reports over the geofence.
     * Storing this data as logs and the aveidance of the pressence of the users on places.
     * Therefor the search service can use it get the data of users on places filterd by items
     * so that users will get the data of people on places.
     * **/

    @PostMapping
    ResponseEntity<ResponseDto<NotificationDto>> createTrackerLogAndGetMessage(@RequestBody Tracker tracker){
        ResponseDto<NotificationDto> responseDto = trackerService.createTrackerLogAndGetMessage(tracker);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Tracker>> updateTracker(@PathVariable Long id, @RequestBody Tracker tracker) {
        ResponseDto<Tracker> response = trackerService.updateTracker(id, tracker);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Tracker>> getTrackerById(@PathVariable Long id) {
        ResponseDto<Tracker> response = trackerService.getTrackerById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseListDto<List<Tracker>>> getAllTracker(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) Integer size,
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "") String search) {
        if (size == null || size == 0) {
            // Fetch total number of trackers from the database
            size = trackerService.getTotalCount(); // Define this method in your service
        }
        ResponseListDto<List<Tracker>> response = trackerService.getAllTracker(page, size, filter, search);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<String>> deleteTracker(@PathVariable Long id) {
        ResponseDto<String> response = trackerService.deleteTracker(id);

        return ResponseEntity.ok(response);
    }

}
