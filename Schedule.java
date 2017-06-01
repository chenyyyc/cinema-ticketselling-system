package com.class28.group3.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.class28.group3.entity.Movie;
import com.class28.group3.entity.ScheduleItem;


@SuppressWarnings("all")
public class Schedule {
	/**
	 * ��ӳ��������
	 */
	private List<ScheduleItem> items = new ArrayList<ScheduleItem>();

	public List<ScheduleItem> getItems() {
		return items;
	}

	public void setItems(List<ScheduleItem> items) {
		this.items = items;
	}

	/**
	 * ��ȡXML�ļ���ȡ��ӳ���ϼ��ϵ�loadItems()����
	 */
	public void loadItems() {
		SAXReader xmlReader = new SAXReader();
		try {
			/**
			 * ����Document���󣬲�����XML�ļ�
			 */
			Document doc = xmlReader.read("NewFile.xml");
			Element ele = doc.getRootElement();

			String movieName = null;
			String poster = null;
			String director = null;
			String actor = null;
			String movieType = null;
			String price = null;

			/**
			 * ������Ԫ�أ��õ�Movie�ڵ�
			 */
			for (Iterator it = ele.elementIterator(); it.hasNext();) {

				Element eleMovie = (Element) it.next();

				// ��Movie�ڵ�ȡNameҶ�ڵ������
				movieName = eleMovie.elementText("Name");

				// ��Movie�ڵ�ȡPosterҶ�ڵ������
				poster = eleMovie.elementText("Poster");

				// ��Movie�ڵ�ȡDirectorҶ�ڵ������
				director = eleMovie.elementText("Director");

				// ��Movie�ڵ�ȡActorҶ�ڵ������
				actor = eleMovie.elementText("Actor");

				// ��Movie�ڵ�ȡTypeҶ�ڵ������
				movieType = eleMovie.elementText("Type");

				// ��Movie�ڵ�ȡPriceҶ�ڵ������
				price = eleMovie.elementText("Price");

				/**
				 * ����Movie�ڵ��µĽڵ㣬������ڵ�Ҷ�ڵ��нڵ㣬��Schedule�ڵ�
				 */
				for (Iterator it1 = eleMovie.elementIterator(); it1.hasNext();) {
					Element eleSchedule = (Element) it1.next();
					/**
					 * ����Schedule�ڵ��µĽڵ�
					 */
					for (Iterator it2 = eleSchedule.elementIterator(); it2
							.hasNext();) {
						Element eleItem = (Element) it2.next();
						/**
						 * ����ScheduleItem���ʵ�����
						 */
						ScheduleItem item = new ScheduleItem(null, price);
						/**
						 * ����Movie���ʵ�����
						 */
						Movie movie = new Movie();

						// ��ʼ��ScheduleItem���е�time����ֵ��ΪItem�ڵ��µ�����
						item.setTime(eleItem.getText());

						// ��ʼ��Movie���movieName����
						movie.setMovieName(movieName);

						// ��ʼ��Movie���poster����
						movie.setPoster(poster);

						// ��ʼ��Movie���actor����
						movie.setActor(actor);

						// ��ʼ��Movie���director����
						movie.setDirector(director);

						// ��ʼ��Movie���movieType����
						movie.setMovieType(movieType);

						/**
						 * �˴����String���͵�priceת����double���� ��ʼ��Movie���price����
						 */

						movie.setPrice(Double.parseDouble(price));

						// ��ʼ��ScheduleItem���е�movie����ֵ��Ϊ�ѳ�ʼ����movie��
						item.setMovie(movie);
						items.add(item);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
