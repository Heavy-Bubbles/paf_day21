package sg.edu.nus.iss.paf_day21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day21.model.Room;
import sg.edu.nus.iss.paf_day21.repository.RoomRepo;

@Service
public class RoomService {

    @Autowired
    RoomRepo roomRepo;

    public int count(){
        return roomRepo.count();
    }

    public Boolean save(Room room){
       return roomRepo.save(room);
    }

    public List<Room> findAll(){
        return roomRepo.getAllRooms();
    }

    public Room findNyId(int id){
       return roomRepo.getRoomById(id);
    }

    public int update(Room room){
        return roomRepo.update(room);
    }

    public int deleteById(int id){
        return roomRepo.deleteById(id);
    }
}
