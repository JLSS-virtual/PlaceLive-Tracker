package com.jlss.placelive.tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;


import java.util.Collections;
import java.util.List;

/**
 * the tracker will be only used to creaetd when tehre is an acticity under geofences. hits.
 * **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tracker")
public class Tracker {

    /**
     * Tracker specific data
     * **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracker_id")
    private long id;  // Changed to long (Firebase/Google UID can be stored as String if needed)

    @Column(name = "name")
    private String name;
    // most important one instance field that will helpd drastically in searching.
    @Column(name = "is_user_in")
    private boolean isUserIn;// will help to provide friends on places.
    // a field will be used in client to track the isstillin or out to reduce server calls teh server will only calss by two times
    //  when 1st enters a geofence. and exits a geofence to track two things 1. last exits.

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tracker_created_at")
    private Date trackerCreatedAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_visit_at")
    private Date lastVisitAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_exit_at")
    private Date lastExitAt;

    @Column(name = "user_visit_count")
    private long userVisitCount;// ti track whihc user comes which times each time it will increase

    /**
     * ids
     * **/
    @Column(name = "geofence_id")
    private long geofenceId;

    @Column(name = "user_id")
    private long userId;// whoom traced.

    @Column(name = "place_id")
    private long placeId;

    /**
     * geofence related data
     * **/

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "radius")
    private Double radius;
    // TODO we will check in tracker service every time event occurs to find that anyone else is also in this geofence or near to the location of this user with an another log with active or isuserin = true . to send the message to friends that you two are near make a trip or plan an meetup.

}