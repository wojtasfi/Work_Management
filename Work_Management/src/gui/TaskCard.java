package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Person;
import model.Task;

public abstract class TaskCard extends Stage {

	protected GridPane grid;
	protected Label task_name;
	protected Label preparer;
	protected Label reviewer;
	protected Label deadline;
	protected Label deadline_hour;
	protected Label minutes;
	protected Label task_type;
	protected Label frequency;
	protected Label comment;
	protected Label attachment;
	protected Label prc_status;
	protected Label review_required;
	protected Label done;
	protected Label orderer;
	protected Label category_1;
	protected Button saveBtn;
	protected Slider slider;

	protected Label nameLbl;
	protected Label prepLbl;
	protected Label revLbl;
	protected Label deadlineLbl;
	protected Label hourLbl;
	protected Label minLbl;
	protected Label typeLbl;
	protected Label freqLbl;
	protected Label commentLbl;
	protected Label statusLbl;
	protected Label ordererLbl;
	protected Label catLbl;
	protected Label reviewedLbl;
	protected CheckBox reviewed;
	protected int taskID;
	protected Person currentUser;
	protected int row;
	protected String lblStyle;
	protected TaskCardListener cardListener;
	protected Insets insets;
	

	public void setCardListener(TaskCardListener listener) {
		this.cardListener = listener;
	}

	public TaskCard(Task task, Person currentUser) {
		this.currentUser = currentUser;
		
		// Controlls
		taskID = task.getId();
		task_name = new Label(task.getTask_name());
		
		deadline_hour = new Label(task.getDeadline_hour().toString());
		minutes = new Label(Integer.toString(task.getMinutes()) + " minutes");
		task_type = new Label(task.getTask_type());
		frequency = new Label(task.getFrequency());
		comment = new Label(task.getComment());
		
		
		
		category_1 = new Label(task.getCategory_1());
		saveBtn = new Button("Save status");
		reviewed = new CheckBox();

		nameLbl = new Label("Task name");
		prepLbl = new Label("Preparer");
		revLbl = new Label("Reviewer");
		deadlineLbl = new Label("Deadline");
		hourLbl = new Label("Deadline time");
		minLbl = new Label("Duration");
		typeLbl = new Label("Task type");
		freqLbl = new Label("Frequency");
		commentLbl = new Label("Comment");
		statusLbl = new Label("Status (%)");
		ordererLbl = new Label("Orderer");
		catLbl = new Label("Category");
		reviewedLbl = new Label("Reviewed");
		
		grid = new GridPane();
		slider = new Slider();
		

		
		setLayout();
		
	}

	public void setLayout() {

		setTitle("Task Card");
		// controlls paddings
		int top = 2;
		int bottom = 20;
		int right = 25;

		insets = new Insets(top, right, bottom, 0);

		lblStyle = "-fx-font-weight: bold; -fx-font-size: 12";
		nameLbl.setStyle(lblStyle);
		prepLbl.setStyle(lblStyle);
		revLbl.setStyle(lblStyle);
		deadlineLbl.setStyle(lblStyle);
		hourLbl.setStyle(lblStyle);
		minLbl.setStyle(lblStyle);
		typeLbl.setStyle(lblStyle);
		freqLbl.setStyle(lblStyle);
		commentLbl.setStyle(lblStyle);
		statusLbl.setStyle(lblStyle);
		ordererLbl.setStyle(lblStyle);
		catLbl.setStyle(lblStyle);
		reviewedLbl.setStyle(lblStyle);

		task_name.setPadding(insets);
		
		deadline_hour.setPadding(insets);
		minutes.setPadding(insets);
		task_type.setPadding(insets);
		frequency.setPadding(insets);
		comment.setPadding(insets);
		
		category_1.setPadding(insets);
		reviewed.setPadding(insets);

		// Slider
		slider.setMin(0);
		slider.setMax(100);

		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(5);

		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				prc_status.setText(Integer.toString(Math.round((int) slider.getValue())) + "%");

			}
		});

		reviewed.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				cardListener.taskReviewed(taskID, reviewed.isSelected());
				cardListener.refreshTable();
			}

		});

		// Adding controlls
		grid.setPadding(new Insets(10, 10, 10, 10));

		row = 1;

		grid.setStyle(
				"-fx-border-color:black; -fx-border-width: 1; -fx-border-style: solid; -fx-border-insets: 10 10 10 10; -fx-border-title: Task Card");

		

	}
}
