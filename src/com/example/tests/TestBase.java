package com.example.tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;

public class TestBase {

	protected ApplicationManager app;

	@BeforeTest
	public void setUp() throws Exception {
		app = new ApplicationManager();
	}

	@DataProvider
	public Iterator<Object[]> randomValidGroupGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		// ...fill
		Random rnd = new Random();
		for (int i = 0; i < 5; i++) {
			GroupData group = new GroupData();
			group.groupname = generateRandomString();
			group.header = generateRandomString();
			group.footer = generateRandomString();
			list.add(new Object[] { group });
		}
		return list.iterator();
	}

	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		// fill
		Random rnd = new Random();
		for (int i = 0; i < 5; i++) {
			ContactData contact = new ContactData();
			Date date = generateRandomDate(rnd);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			contact.firstname = generateRandomAsciiString();
			contact.lastname = generateRandomAsciiString();
			contact.address = generateRandomAsciiString();
			contact.address2 = generateRandomAsciiString();
			contact.bday = "" + calendar.get(Calendar.DAY_OF_MONTH);
			contact.bmonth = ""
					+ calendar.getDisplayName(Calendar.MONTH, Calendar.LONG,
							Locale.ENGLISH);
			contact.byear = "" + calendar.get(Calendar.YEAR);
			contact.email = generateRandomAsciiString();
			contact.email2 = generateRandomAsciiString();
			contact.home = generateRandomAsciiString();
			contact.mobile = generateRandomAsciiString();
			contact.newgroup = null;
			contact.phone2 = generateRandomAsciiString();
			contact.work = generateRandomAsciiString();
			list.add(new Object[] { contact });

		}
		return list.iterator();
	}

	public String generateRandomAsciiString() {
		return RandomStringUtils.randomAscii(10);
	}

	public String generateRandomString() {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0) {
			return "";
		} else {
			return "test" + rnd.nextInt();
		}
	}

	private Date generateRandomDate(Random rnd) {
		Date date = new Date(rnd.nextLong());
		return date;
	}

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	}
}
