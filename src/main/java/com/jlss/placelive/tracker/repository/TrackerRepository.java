package com.jlss.placelive.tracker.repository;

import com.jlss.placelive.tracker.entity.Tracker;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TrackerRepository extends JpaRepository<Tracker, Long>, JpaSpecificationExecutor<Tracker> {

    Optional<Tracker> findByGeofenceIdAndUserId(Long geofenceId, Long userId);

}
