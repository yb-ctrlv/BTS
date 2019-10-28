package com.kh.bts.model.biz;

import java.util.List;

import com.kh.bts.dto.RoomDto;

public interface RoomBiz {

	public List<RoomDto> roomList();
	public int makeRoom(RoomDto dto);
	public int updateRoom(int room_no);
	public RoomDto checkRoom(int room_no);
	public int outRoom(String room_addr);
	public int checkRoomVal(String room_addr);
	public int deleteRoom(String room_addr);
	
}
