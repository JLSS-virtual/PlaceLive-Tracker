--liquibase formatted sql

--changeset Jeet:1
--v1 : Creating high-level Database Schema for Tracker



CREATE TABLE tracker (
    tracker_id BIGINT NOT NULL AUTO_INCREMENT,
 --activities
    name VARCHAR(255),
    is_user_in BOOLEAN,
    user_visit_count BIGINT,
 -- timeStamps
    tracker_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_visit_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_exit_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 -- ids
    geofence_id BIGINT,
    place_id BIGINT,
    user_id BIGINT,
 --geofenceData
    radius Double,
    latitude Double,
    longitude Double

);





