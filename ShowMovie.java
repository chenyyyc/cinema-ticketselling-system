package com.class28.group3.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.class28.group3.biz.TicketFactory;
import com.class28.group3.cinema.Cinema;
import com.class28.group3.ticket.FreeTicket;
import com.class28.group3.ticket.StudentTicket;
import com.class28.group3.ticket.Ticket;
import com.class28.group3.util.Schedule;


public class ShowMovie {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		Schedule sc = new Schedule();
		sc.loadItems();

		/**
		 * ���÷�ӳ�б�չʾquery()
		 */
		query(sc);

		Cinema cinema = new Cinema();
		Seat seat = new Seat();

		/**
		 * ������λ��Ϣ�����ļ���
		 */
		List<String> list = seatForm();

		String flag = null;
		do {
			TicketFactory tf = new TicketFactory();
			String Name = inputMovieName(input, sc);// ���������Ӱ���Ƶķ���
			String Time = inputMovieTime(input, sc);// ��������ʱ��ķ���
			Ticket newTicket = ticketType(input, tf);// ���������ӰƱ���͵ķ���
			String seatId = inputSeat(input, list);// ���������ӰƱ��λ�ķ���
			seat.setSeatNum(seatId);// �õ���λinputSeat()��������λ��ֵ����ֵ
			cinema.setSeat(seat);// Cinema���setSeat���Ը�ֵ
			newTicket.setSeat(seat);// ticket���seat���Ը�ֵ
			/**
			 * <p>
			 * ���������ӰԺ�ĵ�ӰƱ����ӰƱ�ļ۸�͹�Ʊʱ��
			 * </p>
			 * ���� scName��scPrice scName��String ���� scPrice��Double����
			 */
			for (int j = 0; j < sc.getItems().size(); j++) {
				String scName = sc.getItems().get(j).getMovie().getMovieName();
				Double scPrice = sc.getItems().get(j).getMovie().getPrice();
				/**
				 * ʹ��if�ж�������Ӱ�����Ƿ���ͬ
				 */
				if (Name.equals(scName)) {
					newTicket.setScheduleItem(sc.getItems().get(j));// ticket���schedule���Ը�ֵ
					newTicket.setPrice(scPrice);// ticket���price ���Ը�ֵ
					newTicket.getScheduleItem().setTime(Time);// schduleItem��time��ֵ
					break;
				}
			}
			/**
			 * ���ü���Ʊ�۷���
			 */
			newTicket.compute();
			cinema.soldTickets.add(newTicket);// �����۵�Ʊ��ӵ�soldTickets������
			/**
			 * ��ȡ�������(���������۳ɹ��ĵ�ӰƱ����)
			 */
			cinema.load(newTicket);

			System.out.println("��0���¹���,���������˳���");
			flag = input.next();
		} while (flag.equals("0"));
		System.out.println("�Ѱ�ȫ�˳�ϵͳ��");
	}

	/**
	 * ����������λ�����쳣�ķ���
	 * 
	 * @param input
	 * @param list
	 * @return seatId
	 */
	public static String inputSeat(Scanner input, List list) {
		System.out.println("��������Ҫѡ�����λ�ţ�");
		String seatId = input.next();

		// �жϹ������λ�Ƿ��뷵��ֵseatId��ͬ
		if (!list.contains(seatId)) {
			System.out.println("���������λ�Ų����ڣ����������룡");
			seatId = inputSeat(input, list);//
		}
		return seatId;
	}

	/**
	 * ����Ҫѡ���Ʊ�����͵��쳣
	 * 
	 * @param input
	 * @param tf
	 * @return ticketType
	 */
	public static Ticket ticketType(Scanner input, TicketFactory tf) {
		try {
			System.out.println("��ѡ����Ҫ��Ʊ�����ͣ�1.��ͨƱ2.ѧ��Ʊ3.����Ʊ");
			int number = input.nextInt();
			Ticket newTicket = tf.ticketTxt(number);// ����Ҫ��Ʊ���͵Ķ���
			/**
			 * ����ѡƱ���Ͳ�ͬ��ʾ��ͬ����
			 */
			switch (number) {
			case 1:
				break;
			case 2:
				System.out.println("����������Ҫ���ۿۣ�1-9����������");
				StudentTicket st = (StudentTicket) newTicket; // ǿ��ת��Ϊѧ��Ʊ
				int disD = input.nextInt();
				if (disD > 0 && disD < 10) {
					st.setDiscout(disD); // �����ۿ۸�ֵ
				} else {
					System.err.println("��������ȷ��1-9��������");
					ticketType(input, tf);
				}
				break;

			case 3:
				System.out.println("��������Ʊ�˵����֣�");
				FreeTicket ft = (FreeTicket) newTicket;
				ft.setCustomerName(input.next()); // CustomerName��ֵ
				break;
			default:// ����������ֵ����1-3����������
				System.out.println("������1-3��������");
				newTicket = ticketType(input, tf);

			}
			return newTicket;

		} catch (Exception e) {
			System.out.println("�밴����ʾ������ȷ��������");
			input.nextLine();// ��ÿ�εõ���ɨ����Ϣ���
			return ticketType(input, tf);
		}
	}

	/**
	 * ���������Ӱ�����쳣�ķ���
	 * 
	 * @param input
	 * @param sc
	 * @return Name
	 */
	public static String inputMovieName(Scanner input, Schedule sc) {
		System.out.println("��ѡ���Ӱ���ƣ�");
		String Name = input.next();

		List<String> list = new ArrayList<String>();// ������ʼ����Ӱ���Ƶļ���
		// ������ʼ����Ӱ����
		for (ScheduleItem item : sc.getItems()) {
			list.add(item.getMovie().getMovieName());// ��ÿ�εõ��ĵ�Ӱ������ӵ�������
		}
		// �ж�ѡ��ĵ�Ӱ�����Ƿ����ʼ��������ͬ
		if (!list.contains(Name)) {
			System.out.println("��Ӱ�����ڣ����������룡");
			Name = inputMovieName(input, sc);
		}
		return Name;
	}

	/**
	 * ���������Ӱ��ӳʱ���쳣�ķ���
	 * 
	 * @param input
	 * @param sc
	 * @return time
	 */
	public static String inputMovieTime(Scanner input, Schedule sc) {
		System.out.println("��ѡ���Ӱʱ�䣺");
		String time = input.next();

		List<String> list = new ArrayList<String>();// ������ʼ����Ӱ��ӳʱ��ļ���
		// ������ʼ����Ӱ��ӳʱ��
		for (ScheduleItem item : sc.getItems()) {
			list.add(item.getTime());// ��ÿ�εõ��ĵ�Ӱ��ӳʱ����ӵ�������
		}
		// �ж�ѡ��ĵ�Ӱ��ӳʱ���Ƿ����ʼ����Ӱ��ӳʱ����ͬ
		if (!list.contains(time)) {
			System.out.println("ʱ�䲻���ڣ����������룡");
			time = inputMovieTime(input, sc);
		}
		return time;
	}

	/**
	 * ��λ�ṹͼ�ķ���
	 * 
	 * @return list
	 */
	public static List<String> seatForm() {
		System.out.println("----------����ΪӰԺ����λ�ṹͼ----------");
		System.out.println("\t----------��Ļ----------");

		List<String> list = new ArrayList<String>();

		// forѭ����ʾ��ӰԺ����λ�ֲ����
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 7; j++) {
				System.out.print(i + "-" + j + "   ");
				list.add(i + "-" + j);

			}
			System.out.println();
		}
		return list;
	}

	/**
	 * ��ѯ��Ӱ���Ƶ���Ϣ
	 * 
	 * @param sc
	 */
	public static void query(Schedule sc) {
		int id = 1;
		System.out.println("���\t" + "��Ӱ����\t" + "Ӣ������\t" + "����\t" + "��Ա\t"
				+ "��Ӱ����\t" + "�۸�\t" + "ʱ��\t");

		for (ScheduleItem si : sc.getItems()) {

			System.out.println(id + "\t" + si.getMovie().getMovieName() + "\t"
					+ si.getMovie().getPoster() + "\t"
					+ si.getMovie().getDirector() + "\t"
					+ si.getMovie().getActor() + "\t"
					+ si.getMovie().getMovieType() + "\t"
					+ si.getMovie().getPrice() + "\t" + si.getTime());
			id++;

		}
	}

}
