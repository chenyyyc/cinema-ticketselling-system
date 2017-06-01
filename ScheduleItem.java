package com.class28.group3.entity;

import java.io.Serializable;



public class ScheduleItem implements Serializable{
	
	/**
	 * ��������ӳ��Ӱ����
	 * �Զ���Movie����
	 */
	private Movie movie;
	
	/**
	 * ��ӳʱ��
	 * String����
	 */
	private String time;

	public ScheduleItem(Movie movie, String time) {
		this.movie = movie;
		this.time = time;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
