package com.class28.group3.biz;

import com.class28.group3.ticket.FreeTicket;
import com.class28.group3.ticket.StudentTicket;
import com.class28.group3.ticket.Ticket;

/**
 * <p>
 * ��ӰƱ�����࣬���������ֵ���жϴ�����ͬ�ĵ�ӰƱ����
 * </p>
 * 
 * 
 */
public class TicketFactory {
	public Ticket ticketTxt(int i) {
		Ticket newTicket = null;
		switch (i) {
		case 1:
			newTicket = new Ticket();
			break;
		case 2:
			newTicket = new StudentTicket();
			break;
		case 3:
			newTicket = new FreeTicket();
			break;
		}
		return newTicket;
	}

}
