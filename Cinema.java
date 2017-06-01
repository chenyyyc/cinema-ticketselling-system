package com.class28.group3.cinema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.class28.group3.entity.ScheduleItem;
import com.class28.group3.entity.Seat;
import com.class28.group3.ticket.Ticket;
import com.class28.group3.util.Schedule;


public class Cinema {
	/**
	 * ��ӳ�ƻ� �Զ���Schedule����
	 */
	private Schedule schedule;
	/**
	 * ��ӳ�ƻ��ĳ��� �Զ���ScheduleItem����
	 */
	private ScheduleItem scheduleItem;
	/**
	 * ������λ���� �Զ���Seat����
	 */
	private Seat seat;
	/**
	 * ���۳���ӰƱ�ļ��� ���ͼ���List<Ticket>����
	 */
	public List<Ticket> soldTickets = new ArrayList<Ticket>();

	/**
	 * �����������
	 */
	public void save() {
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(
					"ticket/ticket.bin"));
			oos.writeObject(soldTickets);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * ��ȡ�������
	 */
	public void load(Ticket ticket) {

		ObjectInputStream ois = null;
		try {
			File f = new File("ticket/");
			if (!f.exists()) {
				f.mkdir();// ����һ���ɸ�File�����ʾ��Ŀ¼
				System.out.println("ticket�ļ��д����ɹ���");
				File file = new File("ticket/ticket.bin");
				if (!file.exists()) {
					System.out.println("��Ʊ�ɹ���");
					ticket.print();
					/**
					 * �����һ����Ʊ
					 */
					save();
				}
			} else {

				ois = new ObjectInputStream(new FileInputStream(
						"ticket/ticket.bin"));

				ArrayList<Ticket> sTicket = (ArrayList<Ticket>) ois
						.readObject();
				for (int i = 0; i < sTicket.size(); i++) {
					String name = sTicket.get(i).getScheduleItem().getMovie()
							.getMovieName();// ��ֵ��ӰƱ����
					String time = sTicket.get(i).getScheduleItem().getTime();// ��ֵ��Ӱ��ӳʱ��
					String seatNum = sTicket.get(i).getSeat().getSeatNum();// ��ֵ��ӰƱ��λ��

					// �õ�ӰƱ���ơ�ʱ�����λ���ж��Ƿ�ͳ�ʼ����ͬ
					if ((ticket.getScheduleItem().getMovie().getMovieName())
							.equals(name)
							&& (ticket.getScheduleItem().getTime())
									.equals(time)
							&& (seat.getSeatNum()).equals(seatNum)) {
						System.out.println("��Ʊ���۳��������¹���");
						break;
					} else {
						System.out.println("��Ʊ�ɹ���");
						/**
						 * ��ӡƱ�ݵ�txt�ĵ�
						 */
						ticket.print();
						/**
						 * �������۳ɹ��ĵ�ӰƱ
						 */
						save();
						break;
					}

				}
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
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

}
