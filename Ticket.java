package com.class28.group3.ticket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;

import com.class28.group3.entity.Movie;
import com.class28.group3.entity.ScheduleItem;
import com.class28.group3.entity.Seat;



public class Ticket implements Serializable {

	/**
	 * ����ƻ�������scheduleItem
	 */
	private ScheduleItem scheduleItem;

	/**
	 * ������λ����seat
	 */
	private Seat seat;

	/**
	 * Ʊ������price
	 */
	private double price;

	/**
	 * print()���������� MyCinemaProject\ticket Ŀ¼�´�ӡ��ӰƱ
	 * 
	 */
	public void print() {
		try {
			String time = scheduleItem.getTime();
			String ticketName = null;
			ticketName = scheduleItem.getMovie().getMovieName() + " "
					+ seat.getSeatNum() + " " + time.substring(0, 2) + "��"
					+ time.substring(3) + "(��ͨƱ).txt";

			File file = new File("ticket/");
			if (!file.exists()) {
				file.mkdirs();
			}

			FileWriter fos = new FileWriter("ticket/" + ticketName);
			BufferedWriter bw = new BufferedWriter(fos);
			bw.write("***********************");
			bw.newLine();
			bw.write("\t\t����ӰԺ                         ");
			bw.newLine();
			bw.write("-----------------------\n");
			bw.newLine();
			bw.write("��Ӱ����" + scheduleItem.getMovie().getMovieName());
			bw.newLine();
			bw.write("ʱ�䣺" + scheduleItem.getTime() );
			bw.newLine();
			bw.write("��λ�ţ�" + seat.getSeatNum() );
			bw.newLine();
			bw.write("�۸�" + price  );
			bw.newLine();
			bw.write("***********************");
			bw.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * compute()�������ڼ���Ʊ��
	 */
	public void compute() {
		price = scheduleItem.getMovie().getPrice();

	}

	public ScheduleItem getScheduleItem() {
		return scheduleItem;
	}

	public void setScheduleItem(ScheduleItem scheduleItem) {
		this.scheduleItem = scheduleItem;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
