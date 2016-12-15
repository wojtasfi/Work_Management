package model;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import addtaskpanels.AddTaskEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {

	public ObservableList<Task> getTasksToReview() {
		return tasksToReview;
	}

	public ObservableList<Task> getTasksToPass() {
		return tasksToPass;
	}

	private Map<String, String> usersPasswords;
	private Map<String, Person> usersData;
	private Person currentUser;
	private ObservableList<String> categories;
	private DbConnection dbC;
	private ObservableList<String> categoriesAll;
	private ObservableList<String> preps;
	private ObservableList<String> revs;
	private ObservableList<String> frequencies;
	private ObservableList<String> teams;
	private ArrayList<String> catTemp;
	private ArrayList<String> prepTemp;
	private ArrayList<String> allTemp;
	private ArrayList<String> revTemp;
	private ArrayList<String> freqTemp;
	private ArrayList<String> teamsTemp;

	final ObservableList<Task> tasks = FXCollections.observableArrayList();
	final ObservableList<Task> tasksToReview = FXCollections.observableArrayList();
	final ObservableList<Task> tasksToPass = FXCollections.observableArrayList();
	final ObservableList<Task> fixedTasks = FXCollections.observableArrayList();

	public Database() {
		usersPasswords = new HashMap<String, String>();
		usersData = new HashMap<String, Person>();
		dbC = new DbConnection();

		catTemp = new ArrayList<String>();
		prepTemp = new ArrayList<String>();
		allTemp = new ArrayList<String>();
		revTemp = new ArrayList<String>();
		freqTemp = new ArrayList<String>();
		teamsTemp = new ArrayList<String>();
	}

	public void loadUsersData() throws SQLException, Exception {

		Statement st = dbC.Connect().createStatement();
		ResultSet rs = st.executeQuery("select * from users");

		while (rs.next()) {
			int id = rs.getInt(1);
			String login = rs.getString(2);
			String password = rs.getString(3);
			String name = rs.getString(4);
			String surname = rs.getString(5);
			String position = rs.getString(6);
			String team = rs.getString(7);
			String hours_per_day = rs.getString(8);
			Date start_date = rs.getDate(9);

			Person person = null;

			if (position.equals("Analyst")) {
				person = new Analyst(id, login, password, name, surname, position, team, hours_per_day, start_date);
			} else if (position.equals("Senior")) {
				person = new Supervisor(id, login, password, name, surname, position, team, hours_per_day, start_date);
			} else if (position.equals("Manager")) {
				person = new Manager(id, login, password, name, surname, position, hours_per_day, start_date);
			} else if (position.equals("Supervisor")) {
				person = new Manager(id, login, password, name, surname, position, hours_per_day, start_date);
			}

			usersData.put(login, person);
			usersPasswords.put(login, password);

		}
	}

	public void loadMyTasks(Person person, LocalDate date) throws SQLException, Exception {

		tasks.clear();
		String login = person.getLogin();

		// Date format

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String text = date.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);

		Statement st = dbC.Connect().createStatement();
		ResultSet rs = st.executeQuery(
				"select * from tasks where preparer = '" + login + "' and deadline ='" + parsedDate + "'");

		while (rs.next()) {

			// Wszędzie gdzie może być null będę robił ""

			int id = rs.getInt(1);
			String task_name = rs.getString(2);
			String preparer = rs.getString(3);
			String reviewer = rs.getString(4);

			if (reviewer == null) {
				reviewer = "";

			}
			Date deadline = rs.getDate(5);
			Time deadline_hour = rs.getTime(6);
			int minutes = rs.getInt(7);
			String task_type = rs.getString(8);
			String frequency = rs.getString(9);
			String comment = rs.getString(10);
			File attachment = (File) rs.getObject(11);
			float prc_status = rs.getFloat(12);
			boolean review_required = rs.getBoolean(13);
			boolean done = rs.getBoolean(14);
			String orderer = rs.getString(15);
			String category_1 = rs.getString(16);
			boolean reviewed = rs.getBoolean(17);

			Task task = new Task(id, task_name, preparer, reviewer, deadline, deadline_hour, minutes, task_type,
					frequency, comment, attachment, prc_status, review_required, done, orderer, category_1, reviewed);

			tasks.add(task);
		}
	}

	public void loadTasksToReview(Person person, LocalDate date) throws SQLException, Exception {

		tasksToReview.clear();
		String login = person.getLogin();

		// Date format

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String text = date.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);

		Statement st = dbC.Connect().createStatement();

		ResultSet rs = st.executeQuery(
				"select * from tasks where reviewer = '" + login + "' and deadline ='" + parsedDate + "'");

		while (rs.next()) {

			// Wszędzie gdzie może być null będę robił ""

			int id = rs.getInt(1);
			String task_name = rs.getString(2);
			String preparer = rs.getString(3);
			String reviewer = rs.getString(4);
			Date deadline = rs.getDate(5);
			Time deadline_hour = rs.getTime(6);
			int minutes = rs.getInt(7);
			String task_type = rs.getString(8);
			String frequency = rs.getString(9);
			String comment = rs.getString(10);
			File attachment = (File) rs.getObject(11);
			float prc_status = rs.getFloat(12);
			boolean review_required = rs.getBoolean(13);
			boolean done = rs.getBoolean(14);
			String orderer = rs.getString(15);
			String category_1 = rs.getString(16);
			boolean reviewed = rs.getBoolean(17);

			Task task = new Task(id, task_name, preparer, reviewer, deadline, deadline_hour, minutes, task_type,
					frequency, comment, attachment, prc_status, review_required, done, orderer, category_1, reviewed);

			tasksToReview.add(task);
		}
	}

	public boolean validateUser(String user, String pass) {

		if (usersPasswords.containsKey(user)) {

			String password = usersPasswords.get(user);

			if (password.equals(pass)) {

				currentUser = usersData.get(user);
				return true;

			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public ObservableList<String> loadCategories(Person person) throws SQLException, Exception {

		String position = person.getPosition();

		catTemp.clear();
		// System.out.println(position);

		Statement st = dbC.Connect().createStatement();
		ResultSet rs = st.executeQuery("select * from categories where " + position + " = true");

		while (rs.next()) {
			String category = rs.getString(1);
			catTemp.add(category);
		}
		categories = FXCollections.observableList(catTemp);

		return categories;

	}

	public Connection Connect() throws Exception {
		return dbC.Connect();
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public Map<String, String> getUsers() {

		return usersPasswords;
	}

	public Person getCurrentUser() {
		return currentUser;
	}

	public void addTask(AddTaskEvent ev) throws SQLException, Exception {

		String team = currentUser.getTeam();

		// if (position.equals("Analyst")) {

		String task_name = ev.getTask_name();
		String prep = ev.getPreparer();
		java.sql.Date deadline = ev.getDeadline();
		LocalTime deadline_hour = ev.getDeadline_hour();
		int minutes = ev.getMinutes();
		String category = ev.getCategory_1();
		String comment = ev.getComment();

		String query = "Insert into tasks(task_name, preparer, deadline, deadline_hour, minutes, frequency, prc_status, done,reviewed, category_1, task_type,"
				+ " comment, orderer, team)" + " values('" + task_name + "', '" + prep + "', '" + deadline + "', '"
				+ deadline_hour + "', " + minutes + ", 'Variable', 0, 0, 0, '" + category + "', 'V', '" + comment
				+ "', '" + prep + "', '" + team + "')";

		Statement st = dbC.Connect().createStatement();
		PreparedStatement insertStmt = dbC.Connect().prepareStatement(query);
		insertStmt.executeUpdate();
		// }
	}

	public Task getTaskById(int id, String type) throws Exception {
		Statement st = dbC.Connect().createStatement();
		ResultSet rs;

		Task task = null;

		if (type.equals("Fixed")) {
			rs = st.executeQuery("select * from fixed_tasks where id = " + id);
			while (rs.next()) {

				String task_name = rs.getString(2);

				int minutes = rs.getInt(3);
				String task_type = rs.getString(4);
				String frequency = rs.getString(5);
				String comment = rs.getString(6);
				boolean review_required = rs.getBoolean(8);
				String category_1 = rs.getString(9);
				String team = rs.getString(10);
				Time deadline_hour = rs.getTime(11);

				task = new FixedTask(id, task_name, minutes, task_type, frequency, comment, review_required, category_1,
						team, deadline_hour);

			}
		} else {
			rs = st.executeQuery("select * from tasks where id = " + id);
			System.out.println("esle");
			while (rs.next()) {

				String task_name = rs.getString(2);
				String preparer = rs.getString(3);
				String reviewer = rs.getString(4);

				Date deadline = rs.getDate(5);
				Time deadline_hour = rs.getTime(6);
				int minutes = rs.getInt(7);
				String task_type = rs.getString(8);
				String frequency = rs.getString(9);
				String comment = rs.getString(10);
				File attachment = (File) rs.getObject(11);
				float prc_status = rs.getFloat(12);
				boolean review_required = rs.getBoolean(13);
				boolean done = rs.getBoolean(14);
				String orderer = rs.getString(15);
				String category_1 = rs.getString(16);
				boolean reviewed = rs.getBoolean(17);

				task = new Task(id, task_name, preparer, reviewer, deadline, deadline_hour, minutes, task_type,
						frequency, comment, attachment, prc_status, review_required, done, orderer, category_1,
						reviewed);

			}

		}
		st.close();
		return task;

	}

	public void updateTaskStatus(int id, int procent) throws SQLException, Exception {
		String query;
		if (procent < 100) {
			query = "Update tasks set prc_status = " + procent + ", done = 0 where id = " + id;
		} else {
			query = "Update tasks set prc_status = " + procent + ", done = 1 where id = " + id;
		}

		Statement st = dbC.Connect().createStatement();
		PreparedStatement insertStmt = dbC.Connect().prepareStatement(query);
		insertStmt.executeUpdate();

	}

	public void taskReviewed(int id, boolean reviewed) throws SQLException, Exception {

		String query;

		query = "Update tasks set reviewed = " + reviewed + " where id = " + id;

		Statement st = dbC.Connect().createStatement();
		PreparedStatement insertStmt = dbC.Connect().prepareStatement(query);
		insertStmt.executeUpdate();

	}

	public ObservableList<String> loadAllCategories() throws Exception {

		allTemp.clear();
		// System.out.println(position);

		Statement st = dbC.Connect().createStatement();
		ResultSet rs = st.executeQuery("select * from categories");

		while (rs.next()) {
			String category = rs.getString(1);
			allTemp.add(category);
		}
		categoriesAll = FXCollections.observableList(allTemp);

		return categoriesAll;
	}

	public void deputeTask(AddTaskEvent ev) throws SQLException, Exception {
		String team = currentUser.getTeam();

		String task_name = ev.getTask_name();
		String prep = ev.getPreparer();
		String rev = ev.getReviewer();
		java.sql.Date deadline = ev.getDeadline();
		LocalTime deadline_hour = ev.getDeadline_hour();
		int minutes = ev.getMinutes();
		String category = ev.getCategory_1();
		String comment = ev.getComment();

		String query = "Insert into tasks(task_name, preparer, reviewer, deadline, deadline_hour, minutes, frequency, prc_status, done,reviewed, category_1, task_type, "
				+ " comment, orderer, team)" + " values('" + task_name + "', '" + prep + "', '" + rev + "', '"
				+ deadline + "', '" + deadline_hour + "', " + minutes + ", 'Variable', 0, 0, 0, '" + category
				+ "', 'V', '" + comment + "', '" + rev + "', '" + team + "')";

		Statement st = dbC.Connect().createStatement();
		PreparedStatement insertStmt = dbC.Connect().prepareStatement(query);
		insertStmt.executeUpdate();

	}

	public ObservableList<String> loadPreparers(Person currentUser) throws Exception {

		prepTemp.clear();
		ResultSet rs = null;
		// System.out.println(position);

		Statement st = dbC.Connect().createStatement();

		if (currentUser.getPosition().equals("Senior")) {
			rs = st.executeQuery("select login from users where position = 'Analyst'");
		} else if (currentUser.getPosition().equals("Supervisor")) {

			rs = st.executeQuery("select login from users where position = 'Analyst' or position = 'Senior'");

		} else if (currentUser.getPosition().equals("Manager")) {
			rs = st.executeQuery("select distinct team from users");
		}

		while (rs.next()) {
			String category = rs.getString(1);
			prepTemp.add(category);
		}
		preps = FXCollections.observableList(prepTemp);

		return preps;
	}

	public ObservableList<String> loadTeams() throws SQLException, Exception {

		teamsTemp.clear();
		ResultSet rs = null;
		// System.out.println(position);

		Statement st = dbC.Connect().createStatement();

		rs = st.executeQuery("select distinct team from users");

		while (rs.next()) {
			String team = rs.getString(1);
			teamsTemp.add(team);
		}
		teams = FXCollections.observableList(teamsTemp);

		return teams;
	}

	public void createTask(AddTaskEvent ev) throws SQLException, Exception {

		String task_name = ev.getTask_name();
		String team = ev.getPreparer();
		String rev = ev.getReviewer();
		java.sql.Date deadline = ev.getDeadline();
		LocalTime deadline_hour = ev.getDeadline_hour();
		int minutes = ev.getMinutes();
		String category = ev.getCategory_1();
		String comment = ev.getComment();
		int revReq;

		if (ev.isReview_required()) {
			revReq = 1;
		} else {
			revReq = 0;
		}

		String query = "Insert into tasks(task_name, team,  deadline, deadline_hour, minutes, frequency, prc_status, done,reviewed, 		category_1, task_type,"
				+ " comment, orderer, review_required)" + " values('" + task_name + "', '" + team + "', '" + deadline
				+ "', '" + deadline_hour + "', " + minutes + ", 'Variable', 0, 0, 0, '" + category + "', 'V', '"
				+ comment + "', '" + rev + "', '" + revReq + "')";

		Statement st = dbC.Connect().createStatement();
		PreparedStatement insertStmt = dbC.Connect().prepareStatement(query);
		insertStmt.executeUpdate();

	}
	// Trzeba dodac joina z users przy dodawaniu reszty taskow zeby team sie
	// updatowal

	public ObservableList<Task> loadTasksToPass(Person currentUser) throws Exception {

		tasksToPass.clear();
		String team = currentUser.getName();

		Statement st = dbC.Connect().createStatement();
		ResultSet rs = st.executeQuery("select * from tasks where preparer is null and team ='" + team + "'");

		while (rs.next()) {

			// Wszędzie gdzie może być null będę robił ""

			int id = rs.getInt(1);
			String task_name = rs.getString(2);
			String preparer = rs.getString(3);
			String reviewer = rs.getString(4);

			if (reviewer == null) {
				reviewer = "";

			}
			Date deadline = rs.getDate(5);
			Time deadline_hour = rs.getTime(6);
			int minutes = rs.getInt(7);
			String task_type = rs.getString(8);
			String frequency = rs.getString(9);
			String comment = rs.getString(10);
			File attachment = (File) rs.getObject(11);
			float prc_status = rs.getFloat(12);
			boolean review_required = rs.getBoolean(13);
			boolean done = rs.getBoolean(14);
			String orderer = rs.getString(15);
			String category_1 = rs.getString(16);
			boolean reviewed = rs.getBoolean(17);

			Task task = new Task(id, task_name, preparer, reviewer, deadline, deadline_hour, minutes, task_type,
					frequency, comment, attachment, prc_status, review_required, done, orderer, category_1, reviewed);

			tasksToPass.add(task);
		}
		return tasksToPass;
	}

	public void passTask(int taskID, String choosenPrep, String choosenRev) throws SQLException, Exception {

		String query = "update tasks set preparer = '" + choosenPrep + "', reviewer = '" + choosenRev + "' where id = "
				+ taskID;
		Statement st = dbC.Connect().createStatement();
		PreparedStatement insertStmt = dbC.Connect().prepareStatement(query);
		insertStmt.executeUpdate();
	}

	public ObservableList<String> loadReviewers(Person currentUser2) throws SQLException, Exception {
		revTemp.clear();
		ResultSet rs = null;
		// System.out.println(position);

		Statement st = dbC.Connect().createStatement();

		if (currentUser.getPosition().equals("Senior")) {
			rs = st.executeQuery("select login from users where position = 'Analyst'");
		} else if (currentUser.getPosition().equals("Supervisor")) {

			rs = st.executeQuery("select login from users where position = 'Senior'");

		} else if (currentUser.getPosition().equals("Manager")) {
			rs = st.executeQuery("select login from users where position = 'Senior' or position = 'Supervisor'");
		}

		while (rs.next()) {
			String category = rs.getString(1);
			revTemp.add(category);
		}
		revs = FXCollections.observableList(revTemp);

		return revs;

	}

	public ObservableList<String> loadFrequencies() throws SQLException, Exception {
		freqTemp.clear();
		ResultSet rs = null;

		Statement st = dbC.Connect().createStatement();

		rs = st.executeQuery("select frequency from frequencies");

		while (rs.next()) {
			String freq = rs.getString(1);
			freqTemp.add(freq);
		}
		frequencies = FXCollections.observableList(freqTemp);

		return frequencies;
	}

	public void addFixedTask(AddFixedTaskEvent ev) throws SQLException, Exception {

		String team = ev.getTeam();
		String task_name = ev.getTask_name();
		int minutes = ev.getMinutes();
		String category = ev.getCategory_1();
		String comment = ev.getComment();
		boolean revReq = ev.isReview_required();
		String frequency = ev.getFrequency();
		LocalTime deadline_hour = ev.getDeadline_hour();

		String query = "Insert into fixed_tasks(task_name ,  minutes ,task_type , frequency, "
				+ "comment, review_required ,category_1 , team, deadline_hour)" + " values('" + task_name + "', "
				+ minutes + ", 'Fixed', '" + frequency + "' , '" + comment + "', " + revReq + ", '" + category + "', '"
				+ team + "', '" + deadline_hour + "')";

		Statement st = dbC.Connect().createStatement();
		PreparedStatement insertStmt = dbC.Connect().prepareStatement(query);
		insertStmt.executeUpdate();
	}

	public ObservableList<Task> loadFixedTasks() throws SQLException, Exception {
		fixedTasks.clear();

		Statement st = dbC.Connect().createStatement();
		ResultSet rs = st.executeQuery("select * from fixed_tasks");

		while (rs.next()) {

			int id = rs.getInt(1);
			String task_name = rs.getString(2);

			int minutes = rs.getInt(3);
			String task_type = rs.getString(4);
			String frequency = rs.getString(5);
			String comment = rs.getString(6);
			boolean review_required = rs.getBoolean(8);
			String category_1 = rs.getString(9);
			String team = rs.getString(10);
			Time deadline_hour = rs.getTime(11);

			FixedTask task = new FixedTask(id, task_name, minutes, task_type, frequency, comment, review_required,
					category_1, team, deadline_hour);

			fixedTasks.add(task);
		}
		return fixedTasks;
	}

	public void fixedTaskDeleted(int taskID) throws SQLException, Exception {

		Statement st = dbC.Connect().createStatement();
		st.executeUpdate("delete from fixed_tasks where id = " + Integer.toString(taskID));

	}

}
