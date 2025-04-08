//package com.jlss.placelive.tracker.kafka;
//
//import com.jlss.placelive.tracker.entity.Tracker;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TrackerEventProducer {
//
//    private final KafkaTemplate<String, Tracker> trackerKafkaTemplate;
//    private final KafkaTemplate<String, Long> deleteKafkaTemplate;
//
//    private static final String Tracker_TOPIC = "tracker-events";
//    private static final String DELETE_TOPIC = "tracker-delete-events";
//
//    public TrackerEventProducer(KafkaTemplate<String, Tracker> trackerKafkaTemplate,
//                              KafkaTemplate<String, Long> deleteKafkaTemplate) {
//        this.trackerKafkaTemplate = trackerKafkaTemplate;
//        this.deleteKafkaTemplate = deleteKafkaTemplate;
//    }
//
//    // 🔹 Send event when creating or updating a place
//    public void sendTrackerEvent(Tracker tracker) {
//        trackerKafkaTemplate.send(Tracker_TOPIC, tracker);
//    }
//
//    // 🔹 Send event when deleting a place
//    public void sendDeleteTrackerEvent(Long trackerId) {
//        deleteKafkaTemplate.send(DELETE_TOPIC, trackerId);
//    }
//}
