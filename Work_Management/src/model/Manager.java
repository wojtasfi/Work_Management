package model;

import java.sql.Date;

public class Manager extends Person {

	public Manager(int id, String login, String password, String name, String surname, String position,
			String hours_per_day, Date start_date) {
		super(id, login, password, name, surname, position, hours_per_day, start_date);
		// TODO Auto-generated constructor stub
	}

	public void promoteEmployee(Person person, String position) {

	}

	public void assignTask(Task task, Person person) {

	}

}
