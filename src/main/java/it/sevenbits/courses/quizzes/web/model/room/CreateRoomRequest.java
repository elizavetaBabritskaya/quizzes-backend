package it.sevenbits.courses.quizzes.web.model.room;

import java.io.Serializable;

/**
 * create room request
 */
public class CreateRoomRequest implements Serializable {

    private String roomName;

    /**
     * simple constructor
     */
    public CreateRoomRequest() {
    }

    /**
     * constructor
     * @param roomName - room name
     */
    public CreateRoomRequest(final String roomName) {
        this.roomName = roomName;
    }

    public void setRoomName(final String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }
}

