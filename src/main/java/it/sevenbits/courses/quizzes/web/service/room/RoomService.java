package it.sevenbits.courses.quizzes.web.service.room;

import it.sevenbits.courses.quizzes.core.repository.room.RoomRepository;
import it.sevenbits.courses.quizzes.web.controller.security.UserCredentials;
import it.sevenbits.courses.quizzes.web.model.room.GetRoomsResponse;
import it.sevenbits.courses.quizzes.web.model.room.GetRoomResponse;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomResponse;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomRequest;
import org.springframework.stereotype.Service;

/**
 * Room Service
 */
@Service
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;
    private static final int HTTPS_REQUEST_NULL = 400;
    private static final int HTTP_NOT_FOUND = 404;
    private static final int HTTP_SUCCESSFUL = 200;

    /**
     * constructor
     *
     * @param roomRepository - room Repository
     */

    public RoomService(final RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public GetRoomsResponse getRooms() {
        return new GetRoomsResponse(roomRepository.getRooms());
    }

    /**
     * create room
     *
     * @param createRoomRequest - request
     * @return response
     */
    @Override
    public CreateRoomResponse createRoom(final CreateRoomRequest createRoomRequest, final UserCredentials userCredentials) {
        return roomRepository.createRoom(createRoomRequest, userCredentials);
    }

    /**
     * get room by id
     *
     * @param roomId - room id
     * @return response
     */
    @Override
    public GetRoomResponse getRoomId(final String roomId) {
        return new GetRoomResponse(roomRepository.getRoomId(roomId).getRoomId(), roomRepository.getRoomId(roomId).getOwnerId(),
                roomRepository.getRoomId(roomId).getRoomName(), roomRepository.getRoomId(roomId).getPlayers());
    }

    /**
     * leave room
     *
     * @param leaveRoomRequest - room request
     * @return status
     */
    @Override
    public int leaveRoom(final UserCredentials leaveRoomRequest) {
        if (leaveRoomRequest == null | "".equals(leaveRoomRequest.getPlayerId())) {
            return HTTPS_REQUEST_NULL;
        } else if (!roomRepository.leaveRoom(leaveRoomRequest)) {
            return HTTP_NOT_FOUND;
        } else {
            return HTTP_SUCCESSFUL;
        }
    }
}
