package com.jlss.placelive.tracker.service.impl;

import com.jlss.placelive.commonlib.dto.PaginatedDto;
import com.jlss.placelive.commonlib.dto.ResponseDto;
import com.jlss.placelive.commonlib.dto.ResponseListDto;
import com.jlss.placelive.commonlib.service.impl.GenericServiceImpl;
import com.jlss.placelive.tracker.client.NotificationServiceClient;
import com.jlss.placelive.tracker.client.SearchServiceTrackerClient;
import com.jlss.placelive.tracker.dto.NotificationDto;
import com.jlss.placelive.tracker.entity.Tracker;

import com.jlss.placelive.tracker.repository.TrackerRepository;
import com.jlss.placelive.tracker.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TrackerServiceImpl extends GenericServiceImpl<Tracker, TrackerRepository> implements TrackerService {



    private final NotificationServiceClient notificationServiceClient;
    private final SearchServiceTrackerClient searchServiceTrackerClient;

    @Autowired
    public TrackerServiceImpl(TrackerRepository repository, TrackerRepository trackerRepository, NotificationServiceClient notificationServiceClient, SearchServiceTrackerClient searchServiceTrackerClient) {
        super(repository);
        this.notificationServiceClient = notificationServiceClient;
        this.searchServiceTrackerClient = searchServiceTrackerClient;
    }


    @Override
    public ResponseDto<NotificationDto> createTrackerLogAndGetMessage(Tracker tracker) {
       // now here first we check that need to create or update then in both update or create we will send a request to placelive-geofencing and
        // notification and sent tact to client and client will handle the row data and create a notification and popup it.

        Optional<Tracker> existingTracker = repository.findByGeofenceIdAndUserId(tracker.getGeofenceId(), tracker.getUserId());

        if (existingTracker.isPresent()) {
            // Updating the (custom)count and (auto)isUserIn,lastvisitat,
            Tracker trackerToUpdate = existingTracker.get();
            trackerToUpdate.setUserVisitCount(trackerToUpdate.getUserVisitCount()+1);
           // now here using genericservice method to update or to deal with database interection
            super.objectsIdPut((int) existingTracker.get().getId(),trackerToUpdate);
            /**
             * Calling here the elastic Search service to update the user availability on
             * **/
            try{
                searchServiceTrackerClient.putTrackerToSearchService(existingTracker.get().getId(),tracker);
            }catch (Exception e){
                throw new RuntimeException();
            }
            // now calling the notification service and returning it to the client
            return notificationServiceClient.getNotificationByTracker(trackerToUpdate).getBody();
        } else {
            // Create new tracker
            try{
                searchServiceTrackerClient.postTrackerToSearchService(tracker);
            }catch (Exception e){
                throw new RuntimeException();
            }
            tracker.setUserVisitCount(1);
           super.createObject(tracker);
            // calling notification service here.
            return notificationServiceClient.getNotificationByTracker(tracker).getBody();
        }

    }

    public Integer getTotalCount() {
        return (int) repository.count(); // Assuming you use Spring Data JPA
    }

    @Override
    public ResponseDto<Tracker> updateTracker(Long id, Tracker tracker) {
        /**
         * Calling here the elastic Search service to update on
         * **/
        try{
            searchServiceTrackerClient.postTrackerToSearchService(tracker);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return new ResponseDto<>(true,super.objectsIdPut(Math.toIntExact(id),tracker),null,null);
    }

    @Override
    public ResponseDto<Tracker> getTrackerById(Long id) {
        return new ResponseDto<>(true,super.objectsIdGet(Math.toIntExact(id)),null,null);
    }

    @Override
    public ResponseListDto<List<Tracker>> getAllTracker(int page, Integer size, String filter, String search) {
        Page<Tracker> trackers = super.getListOfObjects(page,size,filter,search);
        // extracting content and pagination to send .
        List<Tracker> trackerList = trackers.getContent();
        PaginatedDto pagination = new PaginatedDto(
                trackers.getNumber(),         // Current page index
                trackers.getSize(),           // Page size
                trackers.getTotalElements(),  // Total elements
                trackers.getTotalPages()      // Total pages
        );
        return new ResponseListDto<>(true,trackerList,pagination,null,null);
    }

    @Override
    public ResponseDto<String> deleteTracker(Long id) {
        String OK = super.deleteObject(Math.toIntExact(id));
        try{
            searchServiceTrackerClient.deleteTrackerToSearchService(id);
        }
        catch (
                Exception e
        ){
            throw new RuntimeException();
        }
        return new ResponseDto<>(true,OK,null,null);
    }


}
