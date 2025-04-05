package com.jlss.placelive.tracker.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    private String notificationMessage; // the message place owner or geofence owner wants to send
    private long visitCount;// to say like you visited this place 10 times. we will geather this data rom tracker.
    private String placeName;
    private String aboutPlace;// place discription
    private String type;// park ,cafe.
    //TODO products details or details about what place offers. like photocopy, tea, flowers
    private Double radius;// range on which range this place is eg:this place is teh best for this item (tea,flowers) in the near 800 meters
    /**
     * todo Providing timeStamps to let know the time of activity too.
     * **/
}


// TODO we should use websockets for this notification pullings but for now let it be.
//  when we got cloud we just need to change some methods but core logic will reamins same means data or notificarion will be same but just hte way of sending will changes.