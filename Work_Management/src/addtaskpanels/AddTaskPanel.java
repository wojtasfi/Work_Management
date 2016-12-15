package addtaskpanels;

import java.time.LocalDate;
import java.time.LocalTime;

import controller.Controller;
import gui.FormListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Person;

public abstract class AddTaskPanel extends Pane {

	protected TextField task_name;
	protected ComboBox<String> category;
	protected Spinner minutes;
	protected DatePicker deadline;
	protected Button addTask;
	protected ObservableList<String> categories;
	protected TextField hour;
	protected TextField min;
	protected HBox hour_deadline;
	protected GridPane grid;
	protected FormListener formListener;
	protected Alert alert;
	protected TextArea comment;
	protected int row;
	protected Person currentUser;
	protected Controller controller;

	public void setFormListener(FormListener formListener) {
		this.formListener = formListener;
	}

	public AddTaskPanel(ObservableList<String> categories, Person currentUser, Controller controller) {
		setPadding(new Insets(10, 0, 0, 0));
		this.categories = categories;
		this.currentUser = currentUser;
		this.controller = controller;

		category = new ComboBox<String>();
		category.setItems(categories);
		task_name = new TextField();
		minutes = new Spinner(1, 1000, 1);
		deadline = new DatePicker();
		deadline.setValue(LocalDate.now());
		hour = new TextField();
		min = new TextField();
		addTask = new Button("Add Task");
		hour_deadline = new HBox();
		grid = new GridPane();
		alert = new Alert(AlertType.INFORMATION);
		comment = new TextArea();

		comment.setPrefSize(50, 50);

		addTask.setOnAction(new EventHandler<ActionEvent>() {
			// Validation
			@Override
			public void handle(ActionEvent event) {

				try {
					int h = Integer.parseInt(hour.getText());
					int m = Integer.parseInt(min.getText());

					if (task_name.getText() == null || task_name.getText().trim().isEmpty()) {
						alert.setContentText("Task must have a name.");
						alert.show();

					} else if (category.getSelectionModel().getSelectedItem() == null
							|| category.getSelectionModel().getSelectedItem().trim().isEmpty()) {
						alert.setContentText("Choose category.");
						alert.show();
					} else if (deadline.getValue() == null) {
						alert.setContentText("Choose deadline.");
						alert.show();
					} else if (hour.getText() == null || hour.getText().trim().isEmpty() || min.getText() == null
							|| min.getText().trim().isEmpty()) {
						alert.setContentText("Choose hour deadline.");
						alert.show();
					} else if (h > 23 || h < 0 || m > 59 || m < 0) {
						alert.setContentText("Format of hour deadline is not valid.");
						alert.show();
					}

					// Validated- adding task
					else {

						String prep = currentUser.getLogin();
						String name = task_name.getText();
						String cat = category.getSelectionModel().getSelectedItem();
						int task_duration = (int) minutes.getValue();
						LocalDate localDate = deadline.getValue();
						java.sql.Date myDate = java.sql.Date.valueOf(localDate);
						java.sql.Date task_deadline = myDate;
						int hh = Integer.parseInt(hour.getText());
						int mm = Integer.parseInt(min.getText());
						LocalTime time = LocalTime.of(hh, mm, 0, 0);
						String comm = comment.getText();

						AddTaskEvent ev = new AddTaskEvent(name, prep, task_deadline, time, task_duration, cat, comm);
						formListener.MyTaskAdded(ev);

						alert.setContentText("Task has been added!");
						alert.show();

					}
				} catch (Exception e) {
					alert.setContentText("Format of hour deadline is not valid.");
					alert.show();
					return;
				}

			}

		});

		setLayout();

	}

	private void setLayout() {

		int w = 30;

		hour.setMaxWidth(w);
		min.setMaxWidth(w);

		hour_deadline.getChildren().addAll(hour, new Label(" : "), min);

		minutes.setEditable(true);
		row = 1;
		setPadding(new Insets(10, 0, 0, 0));

		// setStyle("-fx-border-color: grey");

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));

		grid.add(new Label("Task Name"), 1, row);
		grid.add(task_name, 2, row);
		row+=2;
		grid.add(new Label("Task Type"), 1, row);
		grid.add(category, 2, row);
		row+=2;
		grid.add(new Label("Task Duration (min)"), 1, row);
		grid.add(minutes, 2, row);
		row+=2;
		grid.add(new Label("Deadline"), 1, row);
		grid.add(deadline, 2, row);
		row+=2;
		grid.add(new Label("Time (HH:MM)"), 1, row);
		grid.add(hour_deadline, 2, row);
		row+=2;
		grid.add(new Label("Comment"), 1, row);
		grid.add(comment, 2, row);
		row+=2;
		grid.add(addTask, 2, row);

		getChildren().add(grid);
	}

}
