package com.kh.bts.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kh.bts.dto.RoomDto;
import com.kh.bts.model.dao.RoomDao;

@Service
public class RoomBizImpl implements RoomBiz {

	@Autowired
	private RoomDao dao;

	@Override
	public List<RoomDto> roomList() {
		return dao.roomList();
	}

	@Override
	public int makeRoom(RoomDto dto) {
		return dao.makeRoom(dto);
	}

	@Override
	public int updateRoom(int room_no) {
		return dao.updateRoom(room_no);
	}

	@Override
	public RoomDto checkRoom(int room_no) {
		return dao.checkRoom(room_no);
	}

	@Override
	public int outRoom(String room_addr) {
		return dao.outRoom(room_addr);
	}

	@Override
	public int checkRoomVal(String room_addr) {
		return dao.checkRoomVal(room_addr);
	}

	@Override
	public int deleteRoom(String room_addr) {
		return dao.deleteRoom(room_addr);
	}
	


}
