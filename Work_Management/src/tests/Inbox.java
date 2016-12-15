package tests;

import java.util.List;

import addtaskpanels.AddTaskPanel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Task;



//Jak już to zbuduje to zmienię tę klasę na abstakcyjną i bedzie to klasa matka do inboxów
public class Inbox extends Stage {

	private Button tasksBtn;
	private Button addTaskBtn;
	private Image logo;
	private HBox logoBox;
	protected TableView<Task> table;
	private VBox tasksBox;
	private ObservableList<Task> tasks;
	private TabPane tabPane;
	private VBox addTaskBox;
	private BorderPane pane;
	protected AddTaskPanel addTask;
	private ObservableList<String> categories;

	public Inbox(List<Task> list, ObservableList<String> categories, String title) {
		this.tasks = (ObservableList<Task>) list;
		this.categories = categories;
		setLayout(title);

	}

	public void setLayout(String title) {
		setTitle(title);
		setTaskTable();
		setTabPane();
		addTaskBox = new VBox();
		addTaskBox.setPadding(new Insets(10, 0,0,0));
		
		
		//addTask = new AnalystAddTask(categories);

		pane = new BorderPane();
		tasksBtn = new Button("My Tasks");
		addTaskBtn = new Button("Add Task");
		logo = new Image("file:logo.jpg");
		logoBox = new HBox();
		VBox vBtn = new VBox();


		tasksBtn.setPrefWidth(100);
		addTaskBtn.setPrefWidth(100);

		pane.setPadding(new Insets(10, 10,10,10));
		Scene scene = new Scene(pane, 520, 650);

		vBtn.setPadding(new Insets(10, 0,0,0));
		vBtn.getChildren().add(tasksBtn);
		vBtn.getChildren().add(addTaskBtn);
		vBtn.setSpacing(10);
		vBtn.setMinWidth(120);

		logoBox.getChildren().add(new ImageView(logo));
		addTaskBox.getChildren().add(addTask);
		
		//Action Listeners
		addTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				pane.setCenter(addTaskBox);
				pane.getChildren().remove(tasksBox);
			}
		});

		tasksBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				pane.getChildren().remove(addTaskBox);
				pane.setCenter(tasksBox);
			}
		});

		pane.setLeft(vBtn);
		pane.setTop(logoBox);
		pane.setCenter(tasksBox);

		setScene(scene);
	}


	public void setTabPane() {
		// Tab Pane
		tasksBox = new VBox();
		tabPane = new TabPane();
		Tab tasksTab = new Tab();
		tasksTab.setText("Tasks");
		tasksTab.setContent(table);

		Tab projectsTab = new Tab();
		projectsTab.setText("Projects");

		tasksBox.setPadding(new Insets(10, 0, 0, 0));

		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.setStyle("-fx-border-color: grey");

		tabPane.getTabs().add(tasksTab);
		tabPane.getTabs().add(projectsTab);

		tasksBox.getChildren().add(tabPane);
	}

	
	
	public void setTaskTable() {
		table = new TableView<Task>();
		
		TableColumn taskName = new TableColumn<>("Task Name");
		TableColumn<Task, String> deadlineHour = new TableColumn<Task, String>("Deadline");
		TableColumn<Task, String> minutes = new TableColumn<Task, String>("Minutes");

		taskName.setCellValueFactory(new PropertyValueFactory<>("task_name"));
		deadlineHour.setCellValueFactory(new PropertyValueFactory<>("deadline_hour"));
		minutes.setCellValueFactory(new PropertyValueFactory<>("minutes"));

		taskName.setPrefWidth(180);
		deadlineHour.setPrefWidth(100);
		minutes.setPrefWidth(100);

		table.setItems(tasks);

		table.getColumns().addAll(taskName, deadlineHour, minutes);

	}
}
