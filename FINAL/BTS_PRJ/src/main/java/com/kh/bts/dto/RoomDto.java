package com.kh.bts.dto;

import java.sql.Date;

public class RoomDto {
	
	private int room_no;
	private String room_master;
	private String room_name;
	private String room_password;
	private int room_nowval;
	private int room_maxval;
	private Date room_regdate;
	private String room_addr;
	private String room_image;


	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}

	public String getRoom_master() {
		return room_master;
	}

	public void setRoom_master(String room_master) {
		this.room_master = room_master;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getRoom_password() {
		return room_password;
	}

	public void setRoom_password(String room_password) {
		this.room_password = room_password;
	}

	public int getRoom_nowval() {
		return room_nowval;
	}

	public void setRoom_nowval(int room_nowval) {
		this.room_nowval = room_nowval;
	}

	public int getRoom_maxval() {
		return room_maxval;
	}

	public void setRoom_maxval(int room_maxval) {
		this.room_maxval = room_maxval;
	}

	public Date getRoom_regdate() {
		return room_regdate;
	}

	public void setRoom_regdate(Date room_regdate) {
		this.room_regdate = room_regdate;
	}

	public String getRoom_addr() {
		return room_addr;
	}

	public void setRoom_addr(String room_addr) {
		this.room_addr = room_addr;
	}

	public String getRoom_image() {
		return room_image;
	}

	public void setRoom_image(String room_image) {
		this.room_image = room_image;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
