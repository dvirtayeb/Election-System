package model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class MyDate {
	private int year, month, day;
	private final int MAX_YEAR = 2020, MIN_YEAR = 1900;
	private final int DAY = 1, MONTH = 1;

	public MyDate(int day, int month, int year) {
		if (year > MAX_YEAR || year < MIN_YEAR)
			this.year = MAX_YEAR;
		else
			this.year = year;
		if (month > 12 || month < 1)
			this.month = MONTH;
		else
			this.month = month;

		int monthLength = YearMonth.of(this.year, this.month).lengthOfMonth();
		if (day < 1 || day > monthLength) {
			this.day = DAY;
		} else {
			this.day = day;
		}
	}

	public MyDate(MyDate date) {
		this(date.day, date.month, date.year);
	}
	public MyDate() {
		this(1,1,2020);
	}
	public MyDate(LocalDate date) {
		this(date.getDayOfMonth(),date.getMonthValue(),date.getYear());
	}

	public int daysCount(MyDate d) {
		LocalDate enter = LocalDate.of(year, month, day);
		LocalDate out = LocalDate.of(d.year, d.month, d.day);
		int difference = Math.abs((int) ChronoUnit.DAYS.between(enter, out));
		return difference;
	}

	public int yearsCount(MyDate d) {
		LocalDate birth = LocalDate.of(year, month, day);
		LocalDate electionDate = LocalDate.of(d.year, d.month, d.day);
		int age = (int) ChronoUnit.YEARS.between(birth, electionDate);
		return age;
	}
	public int yearsValue() {
		MyDate now=new MyDate(LocalDate.now());
		int yearsValue = this.yearsCount(now);
		return yearsValue;
	}

	public String toString() {
		return day + "/" + month + "/" + year;
	}

}
