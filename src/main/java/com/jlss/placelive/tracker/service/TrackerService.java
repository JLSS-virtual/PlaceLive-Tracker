package com.jlss.placelive.tracker.service;


import com.jlss.placelive.commonlib.dto.ResponseDto;
import com.jlss.placelive.commonlib.dto.ResponseListDto;
import com.jlss.placelive.tracker.dto.NotificationDto;
import com.jlss.placelive.tracker.entity.Tracker;

import java.util.List;

public interface TrackerService {


    ResponseDto<NotificationDto> createTrackerLogAndGetMessage(Tracker tracker);

    Integer getTotalCount();

    ResponseDto<Tracker> updateTracker(Long id, Tracker tracker);

    ResponseDto<Tracker> getTrackerById(Long id);

    ResponseListDto<List<Tracker>> getAllTracker(int page, Integer size, String filter, String search);

    ResponseDto<String> deleteTracker(Long id);
}
