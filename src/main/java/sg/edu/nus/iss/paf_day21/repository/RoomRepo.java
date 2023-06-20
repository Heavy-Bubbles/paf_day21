package sg.edu.nus.iss.paf_day21.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day21.model.Room;

@Repository
public class RoomRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String countSql = "select count(*) from room";
    private final String findAllSql = "select * from room";
    private final String findByIdSql = "select * from room where id = ?";
    private final String deletebyIdSql = "delete from room where id = ?";
    private final String insertSql = "insert into room (room_type, price) values (?, ?)";
    private final String updateSql = "update room set price = ? where id = ?";

    public int count(){

        Integer result = 0;
        result = jdbcTemplate.queryForObject(countSql, Integer.class);
        return result;
    }

    public List<Room> getAllRooms(){
        List<Room> roomList = new ArrayList<>();
        roomList = jdbcTemplate.query(findAllSql, 
            BeanPropertyRowMapper.newInstance(Room.class));
        return roomList;
    }

     public Room getRoomById(int id){
        Room room = new Room();
        jdbcTemplate.queryForObject(findByIdSql, 
            BeanPropertyRowMapper.newInstance(Room.class), id);
        return room;
    }

    public Boolean save(Room room){
        Boolean saved = false;

        saved = jdbcTemplate.execute(insertSql, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                
                ps.setString(1, room.getRoomType());
                ps.setInt(2, room.getPrice());
                return ps.execute();

            }
            
        });

        return saved;
    }

    public int update(Room room){
        int updated = 0;

        updated = jdbcTemplate.update(updateSql, room.getPrice(), room.getId());

        return updated;
    }

    public int deleteById(int id){
        int deleted = 0;
        deleted = jdbcTemplate.update(deletebyIdSql, id);
        return deleted;
    }
}
