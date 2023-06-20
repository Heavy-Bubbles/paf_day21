package sg.edu.nus.iss.paf_day21.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_day21.model.Room;
import sg.edu.nus.iss.paf_day21.service.RoomService;

// rest controller to call api and request resources
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired 
    RoomService roomService;

    @GetMapping ("/count")
    // use response entity to get json data
    public ResponseEntity<Integer> getRoomCount(){
        Integer countResult = roomService.count();

        return ResponseEntity.ok().body(countResult);
        // return new ResponseEntity<Integer>(countResult, HttpStatus.OK);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        rooms = roomService.findAll();

        if (rooms.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(rooms);
        }
    }

    // http://localhost:8080/api/rooms/{roomId}
    // specify path variable name when there are multiple path variables, otherwise not needed
    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable("roomId") int roomId){
        Room room = roomService.findNyId(roomId);

        if (room == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(room);
        }
    }

    @PostMapping ("/create")
    public ResponseEntity<Boolean> createRoom(@RequestBody Room room) {
        Boolean created = roomService.save(room);

        if (created) {
            return ResponseEntity.ok().body(created);
        } else {
            return ResponseEntity.internalServerError().build();
        }
       
    }

    @DeleteMapping("{roomId}")
    public ResponseEntity<Integer> deleteRoomById(@PathVariable("roomId") int roomId){
        int deleted = roomService.deleteById(roomId);

        if (deleted == 1){
            return ResponseEntity.ok().body(deleted);
        } else {
            return new ResponseEntity<Integer>(deleted, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> updateRoom(@RequestBody Room room){
        int updated = roomService.update(room);

        if (updated == 1){
            return ResponseEntity.ok().body(updated);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
