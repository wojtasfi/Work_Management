package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import addtaskpanels.AddTaskEvent;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.AddFixedTaskEvent;
import model.Database;
import model.Person;
import model.Task;
import model.Task;

public class Controller {

	private Database db;
	private Alert alert;

	public Controller() {
		db = new Database();

	}

	public Map<String, String> getUsers() {
		return db.getUsers();

	}

	public boolean validate(String user, String pass) {
		return db.validateUser(user, pass);
	}

	public void loadUsersData() throws SQLException, Exception {
		db.loadUsersData();

	}

	public Person getCurrentUser() {
		return db.getCurrentUser();
	}

	public List<Task> getTasks() {
		// System.out.println(db.getTasks());
		return db.getTasks();
	}

	public ObservableList<String> loadCategories(Person person) {

		try {
			return db.loadCategories(person);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public void loadTasks(Person person, LocalDate date) {
		try {
			db.loadMyTasks(person, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addTask(AddTaskEvent ev) {
		try {
			db.addTask(ev);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Task getTaskById(int id, String type) {
		// TODO Auto-generated method stub
		try {
			// types: Fixed, Basic, Pass
			
			return db.getTaskById(id, type);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void updateTaskStatus(int id, int procent) {
		try {
			db.updateTaskStatus(id, procent);
			alert = new Alert(AlertType.INFORMATION);

			if (procent < 100) {
				alert.setContentText("Status saved!");
			} else {
				alert.setContentText("Task completed!");
			}
			alert.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void taskReviewed(int id, boolean reviewed) {
		try {
			db.taskReviewed(id, reviewed);
			alert = new Alert(AlertType.INFORMATION);

			if (reviewed = true) {
				alert.setContentText("Task reviewed!");
			} else {
				alert.setContentText("Task is not reviewed!");
			}
			alert.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ObservableList<String> loadAllCategories() {
		// TODO Auto-generated method stub
		try {
			return db.loadAllCategories();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void deputeTask(AddTaskEvent ev) {
		try {
			db.deputeTask(ev);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ObservableList<String> loadPreparers(Person currentUser) {
		// TODO Auto-generated method stub
		try {
			return db.loadPreparers(currentUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void loadTasksToReview(Person currentUser, LocalDate date) {
		try {
			db.loadTasksToReview(currentUser, date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ObservableList<Task> getTasksToReview() {
		// TODO Auto-generated method stub
		return db.getTasksToReview();
	}

	public void createTask(AddTaskEvent ev) {
		try {
			db.createTask(ev);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public ObservableList<Task> loadTasksToPass(Person currentUser) {
		// TODO Auto-generated method stub
		try {
			return db.loadTasksToPass(currentUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void passTask(int taskID, String choosenPrep, String choosenRev) {
		try {
			db.passTask( taskID,  choosenPrep,  choosenRev);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ObservableList<String> loadReviewers(Person currentUser) {
		// TODO Auto-generated method stub
		try {
			return db.loadReviewers(currentUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ObservableList<String> loadFrequencies() {
		// TODO Auto-generated method stub
		try {
			return db.loadFrequencies();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void addFixedTask(AddFixedTaskEvent ev) {
		// TODO Auto-generated method stub
		try {
			db.addFixedTask(ev);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ObservableList<String> loadTeams() {
		// TODO Auto-generated method stub
		try {
			return db.loadTeams();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ObservableList<Task> loadFixedTasks() {
		// TODO Auto-generated method stub
		try {
			return db.loadFixedTasks() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void fixedTaskDeleted(int taskID) {
		// TODO Auto-generated method stub
		try {
			db.fixedTaskDeleted(taskID);
			alert.setContentText("Task deleted!");
			alert.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
