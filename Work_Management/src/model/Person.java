package model;

import java.sql.Date;

public abstract class Person {

	private int id;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String position;
	private String team;
	private String hours_per_day;
	private Date start_date;

	

	public Person(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public Person(String login, String name, String surname, String position) {
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.position = position;
	}

	public Person(int id, String login, String password, String name, String surname, String position, String team,
			String hours_per_day, Date start_date) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.team = team;
		this.hours_per_day = hours_per_day;
		this.start_date = start_date;
	}
	
	public Person(int id, String login, String password, String name, String surname, String position,
			String hours_per_day, Date start_date) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.hours_per_day = hours_per_day;
		this.start_date = start_date;
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getJob() {
		return position;
	}
	
	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getPosition() {
		return position;
	}

	public String getTeam() {
		return team;
	}

	public String getHours_per_day() {
		return hours_per_day;
	}

	public Date getStart_date() {
		return start_date;
	}

}
