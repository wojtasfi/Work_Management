package gui;

import java.time.LocalDate;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Task;
import model.FixedTask;
import model.Person;
import model.Task;

//To musi być uniwersalna klasa wyświetlająca tabele z taskami, tak żeby można je było wyświetlać we wszystkich inboxach
public class TaskTable extends Pane {

	private TableView<Task> table;
	private TaskCard taskCard;
	private Controller controller;
	private ObservableList<Task> tasks;
	private GridPane grid;
	private Person currentUser;
	private Button addDay;
	private Button minusDay;
	private DatePicker datePicker;
	private HBox tableControls;
	private VBox tableBox;
	private ObservableList<String> preps;
	private ObservableList<String> revs;
	private FixedTaskCard fixedTaskCard;

	public void setDatePickerVisible(boolean datePickerVisible) {
		/*
		 * addDay.setVisible(datePickerVisible);
		 * minusDay.setVisible(datePickerVisible);
		 * datePicker.setVisible(datePickerVisible);
		 * 
		 * int width = 1;
		 * 
		 * addDay.setMaxWidth(width); minusDay.setMaxWidth(width);
		 * datePicker.setMaxWidth(width); tableControls.setMaxWidth(width);
		 */
		int width = 1;
		tableControls.getChildren().remove(addDay);
		tableControls.getChildren().remove(minusDay);
		tableControls.getChildren().remove(datePicker);

		grid.getChildren().remove(tableControls);
		/*
		 * tableControls.setMaxWidth(width); tableControls.setPadding(new
		 * Insets(0, 0, 0, 0));
		 */
	}

	public TaskTable(Controller controller, ObservableList<Task> tasks, Person currentUser) {
		this.controller = controller;
		this.tasks = tasks;
		this.currentUser = currentUser;

		// setStyle("-fx-border-color: grey");
		table = new TableView<Task>();
		tableControls = new HBox();
		grid = new GridPane();
		addDay = new Button(">");
		minusDay = new Button("<");
		datePicker = new DatePicker();
		tableBox = new VBox();
		datePicker.setValue(LocalDate.now());

		tableBox.setPadding(new Insets(10, 0, 0, 0));
		tableControls.setPadding(new Insets(10, 10, 10, 10));
		tableControls.getChildren().add(minusDay);
		tableControls.getChildren().add(datePicker);
		tableControls.getChildren().add(addDay);

		TableColumn<Task, String> taskName = new TableColumn<>("Task Name");
		TableColumn<Task, String> deadlineHour = new TableColumn<Task, String>("Time");
		TableColumn<Task, String> deadline = new TableColumn<Task, String>("Deadline");
		TableColumn<Task, String> minutes = new TableColumn<Task, String>("Duration");
		TableColumn<Task, String> reviewer = new TableColumn<Task, String>("Reviewer");
		TableColumn<Task, String> status = new TableColumn<Task, String>("Status");

		taskName.setCellValueFactory(new PropertyValueFactory<>("task_name"));
		deadlineHour.setCellValueFactory(new PropertyValueFactory<>("deadline_hour"));
		deadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		minutes.setCellValueFactory(new PropertyValueFactory<>("minutes"));
		reviewer.setCellValueFactory(new PropertyValueFactory<>("Reviewer"));
		status.setCellValueFactory(new PropertyValueFactory<>("prc_status"));

		taskName.setPrefWidth(180);
		deadlineHour.setPrefWidth(100);
		minutes.setPrefWidth(100);

		table.setItems(tasks);

		table.getColumns().addAll(taskName, deadlineHour, deadline, minutes, reviewer, status);

		tableBox.getChildren().add(table);

		grid.add(tableControls, 1, 1);
		grid.add(tableBox, 1, 2);
		getChildren().add(grid);

		// Action Listeners
		addDay.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				datePicker.setValue(datePicker.getValue().plusDays(1));
				refreshTable();
			}
		});
		minusDay.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				datePicker.setValue(datePicker.getValue().minusDays(1));
				refreshTable();
			}
		});
		datePicker.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				refreshTable();
			}
		});

		table.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				try {
					if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
						int id = table.getSelectionModel().getSelectedItem().getId();
						String type;
						
						
						if (table.getSelectionModel().getSelectedItem() instanceof FixedTask) {
							
							type = "Fixed";
							//System.out.println("1" + currentUser.getLogin());
							FixedTask task = (FixedTask) controller.getTaskById(id, type);
							taskCard = new FixedTaskCard(task, currentUser);

						} else if (table.getSelectionModel().getSelectedItem().getPreparer() == null) {
							type = "Basic";
							preps = controller.loadPreparers(currentUser);
							revs = controller.loadReviewers(currentUser);
							taskCard = new PassTaskCard(controller.getTaskById(id, type), currentUser, preps, revs);
						} else {
							type = "Basic";
							taskCard = new BasicTaskCard(controller.getTaskById(id, type), currentUser);
							
						}

						taskCard.setCardListener(new TaskCardListener() {

							@Override
							public void taskStatusUpdated(int id, int procent) {
								controller.updateTaskStatus(id, procent);
							}

							@Override
							public void taskReviewed(int id, boolean reviewed) {
								controller.taskReviewed(id, reviewed);
							}

							@Override
							public void refreshTable() {

								controller.loadTasks(currentUser, datePicker.getValue());
								table.refresh();
							}

							@Override
							public void taskPassed(int taskID, String choosenPrep, String choosenRev) {
								controller.passTask(taskID, choosenPrep, choosenRev);

							}

							@Override
							public void refreshPassTable() {
								controller.loadTasksToPass(currentUser);
								table.refresh();

							}

							@Override
							public void fixedTaskDeleted(int taskID) {
								controller.fixedTaskDeleted(taskID);
								table.refresh();

							}

						});

					}
				} catch (NullPointerException e) {

				}
			}

		});

	}

	public void refreshTable() {
		controller.loadTasks(currentUser, datePicker.getValue());
		table.refresh();
	}

	public void loadTasks() {
		controller.loadTasks(currentUser, datePicker.getValue());
	}

	public void loadTasksToReview() {
		controller.loadTasksToReview(currentUser, datePicker.getValue());
	}
}
