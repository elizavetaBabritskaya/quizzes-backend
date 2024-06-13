package it.sevenbits.courses.quizzes.core.repository.room;

import it.sevenbits.courses.quizzes.web.controller.security.UserCredentials;
import it.sevenbits.courses.quizzes.web.model.room.RoomsResponse;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomRequest;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomResponse;
import it.sevenbits.courses.quizzes.web.model.room.GetRoomResponse;

import java.util.List;

/**
 * interface room repository
 */
public interface IRoomRepository {
    /**
     * get all rooms
     *
     * @return List rooms response
     */

    List<RoomsResponse> getRooms();
    /**
     * create new room
     *
     * @param createRoomRequest - request room
     * @param userCredentials - user credentials
     * @return response
     */
    CreateRoomResponse createRoom(CreateRoomRequest createRoomRequest, UserCredentials userCredentials);

    /**
     * get room id
     *
     * @param roomId - room id
     * @return get room response
     */

    GetRoomResponse getRoomId(String roomId);

    /**
     * leave room
     *
     * @param userCredentials - request
     * @return true - leave room, false else
     */
    boolean leaveRoom(UserCredentials userCredentials);
}
