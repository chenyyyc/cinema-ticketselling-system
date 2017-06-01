package com.class28.group3.entity;

import java.io.Serializable;



public class Movie implements Serializable{
	
	/**
	 * ��Ӱ������
	 * String����
	 */
	private String movieName;
	
	/**
	 * ��ӰӢ����
	 * String����
	 */
	private String poster;
	
	/**
	 * ������
	 * String����
	 */
	private String director;
	
	/**
	 * ����
	 * String ����
	 */
	private String actor;
	
	/**
	 * ��Ӱ����
	 * �Զ���ö������
	 */
	private String movieType;
	
	/**
	 * Ʊ������
	 * int����
	 */
	private double price;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
