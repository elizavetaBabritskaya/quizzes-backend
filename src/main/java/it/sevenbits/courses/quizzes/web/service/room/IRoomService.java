package it.sevenbits.courses.quizzes.web.service.room;

import it.sevenbits.courses.quizzes.web.controller.security.UserCredentials;
import it.sevenbits.courses.quizzes.web.model.room.GetRoomsResponse;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomResponse;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomRequest;
import it.sevenbits.courses.quizzes.web.model.room.GetRoomResponse;

/**
 * interface room service
 */

public interface IRoomService {
    /**
     * get room
     *
     * @return rooms response
     */
    GetRoomsResponse getRooms();

    /**
     * create room
     *
     * @param createRoomRequest - request
     * @param userCredentials   - parse token user
     * @return response
     */
    CreateRoomResponse createRoom(CreateRoomRequest createRoomRequest, UserCredentials userCredentials);

    /**
     * get room by id
     *
     * @param roomId - room id
     * @return response
     */
    GetRoomResponse getRoomId(String roomId);

    /**
     * leave room
     *
     * @param leaveRoomRequest - room request
     * @return status
     */
    int leaveRoom(UserCredentials leaveRoomRequest);
}
