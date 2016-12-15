package inboxes;

import addtaskpanels.AddMyTaskPanel;
import addtaskpanels.AddTaskEvent;
import addtaskpanels.AddTaskPanel;
import controller.Controller;
import gui.FormListener;
import gui.TaskTable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Person;
import model.Task;
import model.Task;

//Jak już to zbuduje to zmienię tę klasę na abstakcyjną i bedzie to klasa matka do inboxów
public abstract class Inbox extends Stage {

	protected Button tasksBtn;
	protected Button addTaskBtn;
	protected Image logo;
	protected VBox logoBox;
	protected ObservableList<Task> tasks;
	protected TabPane tabPane;
	protected VBox addTaskBox;
	protected BorderPane pane;
	protected AddTaskPanel addTask;
	protected ObservableList<String> categories;
	protected VBox buttonsBox;
	protected Scene scene;
	protected Controller controller;
	protected Person currentUser;
	protected TaskTable taskTable;
	protected int buttonsWidth = 100;
	protected Button menuBtn;
	protected Stage parent;
	protected HBox upperBox;

	public Inbox(Person currentUser, Controller controller, Stage parent) {

		this.controller = controller;
		this.currentUser = currentUser;
		this.parent = parent;

		this.tasks = (ObservableList<Task>) controller.getTasks();
		this.categories = controller.loadCategories(currentUser);

		tabPane = new TabPane();
		addTaskBox = new VBox();
		upperBox = new HBox();

		addTask = new AddMyTaskPanel(categories, currentUser, controller);

		pane = new BorderPane();
		tasksBtn = new Button("My Tasks");
		addTaskBtn = new Button("Add Task");
		logo = new Image("file:logo.jpg");
		logoBox = new VBox();
		buttonsBox = new VBox();

		taskTable = new TaskTable(controller, tasks, currentUser);
		taskTable.loadTasks();

		scene = new Scene(pane);

		addTask.setFormListener(new FormListener() {

			@Override
			public void MyTaskAdded(AddTaskEvent ev) {
				controller.addTask(ev);
			}

		});
		setLayout();

	}

	public void setLayout() {
		setTitle("Inbox");
		setTabPane();

		buttonsBox.setPadding(new Insets(10, 0, 0, 0));
		addTaskBox.setPadding(new Insets(10, 0, 0, 0));
		pane.setPadding(new Insets(10, 10, 10, 10));
		tabPane.setPadding(new Insets(10, 0, 0, 0));

		tasksBtn.setPrefWidth(buttonsWidth);
		addTaskBtn.setPrefWidth(buttonsWidth);

		buttonsBox.getChildren().add(tasksBtn);
		buttonsBox.getChildren().add(addTaskBtn);
		// logoBox.getChildren().add(menuBtn);

		buttonsBox.setSpacing(10);
		buttonsBox.setMinWidth(120);

		logoBox.getChildren().add(new ImageView(logo));
		menuBtn = new Button("Main Menu");
		logoBox.setAlignment(Pos.BASELINE_RIGHT);
		logoBox.setSpacing(5);
		
		
		
		
		logoBox.getChildren().add(menuBtn);

		addTaskBox.getChildren().add(addTask);

		// Action Listeners
		addTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				pane.getChildren().removeAll();
				pane.setCenter(addTaskBox);
			}
		});

		menuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				close();
				parent.show();

			}

		});

		tasksBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// pane.getChildren().remove(addTaskBox);
				pane.getChildren().removeAll();
				pane.setCenter(tabPane);
				taskTable.refreshTable();
			}
		});

		pane.setLeft(buttonsBox);
		pane.setTop(logoBox);
		pane.setCenter(tabPane);

		setScene(scene);
	}

	public void setTabPane() {
		// Tab Pane

		Tab tasksTab = new Tab();
		tasksTab.setText("Tasks");
		tasksTab.setContent(taskTable);

		Tab projectsTab = new Tab();
		projectsTab.setText("Projects");

		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		// tabPane.setStyle("-fx-border-color: grey");

		tabPane.getTabs().add(tasksTab);
		tabPane.getTabs().add(projectsTab);

	}

}
