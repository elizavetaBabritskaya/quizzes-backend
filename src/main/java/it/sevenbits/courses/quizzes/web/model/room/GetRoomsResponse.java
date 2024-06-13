package it.sevenbits.courses.quizzes.web.model.room;

import java.util.List;

/**
 * get rooms response
 */
public class GetRoomsResponse {
    private List<RoomsResponse> roomsResponse;

    /**
     * get all rooms
     * @param roomsResponse - list room response
     */
    public GetRoomsResponse(final List<RoomsResponse> roomsResponse) {
        this.roomsResponse = roomsResponse;
    }

    public List<RoomsResponse> getRoomsResponse() {
        return roomsResponse;
    }

    public void setRoomsResponse(final List<RoomsResponse> roomsResponse) {
        this.roomsResponse = roomsResponse;
    }
}
