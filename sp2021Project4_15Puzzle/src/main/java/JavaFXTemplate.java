// Pranav Rathod and Parth Tawde

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;
import javafx.scene.paint.Color;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.geometry.Pos;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXTemplate extends Application {
	private Image img;
	private ImageView view;
	private Stage dummyStage;
	private int count = 0;
	private GridPane board;
	private MenuBar menu;
	private MenuItem AI_H1;
	private MenuItem AI_H2;
	private MenuItem exit;
	private MenuItem solution;
	private Menu options;
	private int column = 0;
	private int row = 0;
	private int tempArr[];
	private int array1[] = {9, 5, 7, 3, 13, 1, 2, 6, 14, 10, 4, 11, 0, 15, 8, 12};
	private int array2[] = {7, 6, 9, 4, 1, 5, 2, 3, 13, 10, 12, 15, 0, 8, 11, 14};
	private int array3[] = {5, 13, 4, 6, 2, 11, 12, 3, 1, 10, 0, 7, 9, 14, 15, 8};
	private int array4[] = {1, 6, 7, 0, 5, 3, 8, 4, 13, 2, 9, 12, 10, 14, 11, 15};
	private int array5[] = {1, 6, 7, 0, 5, 3, 8, 4, 13, 2, 9, 12, 10, 14, 11, 15};
	private int array6[] = {9, 1, 0, 7, 2, 14, 5, 3, 15, 12, 11, 10, 12, 8, 6, 4};
	private int array7[] = {1, 5, 4, 3, 7, 14, 15, 2, 13, 6, 8, 11, 9, 10, 0, 12};
	private int array8[] = {3, 1, 12, 6, 2, 8, 4, 14, 5, 15, 11, 0, 9, 13, 10, 7};
	private int array9[] = {1, 3, 7, 0, 11, 2, 8, 4, 10, 5, 14, 12, 9, 6, 13, 15};
	private int array10[] = {5, 1, 4, 8, 10, 6, 0, 3, 15, 7, 9, 11, 14, 2, 13, 12};
	private int solving_array[] = {1,0,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	private ArrayList<int[]> arrays = new ArrayList<int[]>(Arrays.asList(array1, array2, array3, array4, array5, array6, array7, array8, array9, array10));
	private ArrayList<GameButton> checkArray = new ArrayList<GameButton>();
	//private GameButton checkArray[][] = new GameButton[4][4];
	private GameButton empty, dummyButton;
	private boolean emptyFlag = false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		dummyStage = primaryStage;
		primaryStage.setTitle("Welcome to JavaFX");
		Scene scene = new Scene(new VBox(), 700,700);
		primaryStage.setScene(welcomeScene());
		PauseTransition halt = new PauseTransition(Duration.seconds(3));
		halt.setOnFinished(e -> {
			dummyStage.setScene(gameScene());
			dummyStage.show();
		});
		halt.play();
//		PauseTransition halt2 = new PauseTransition(Duration.seconds(3));
//		halt.setOnFinished(e -> {
//			dummyStage.setScene(winScene());
//			dummyStage.show();
//		});
//		halt2.play();
		primaryStage.show();
		
		Thread t = new Thread(()-> {A_IDS_A_15solver ids = new A_IDS_A_15solver();});
		t.start();
	}
	
	public Scene welcomeScene() {
		Label label = new Label("Welcome to 15 PUZZEL");
		label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		label.setBackground(new Background(new BackgroundFill(Color.WHITE,null, null)));
		label.setAlignment(Pos.CENTER);
		Image image = new Image("welcome.png");
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(label);
		borderPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
		return new Scene(borderPane, 400, 400);
	}
	public Scene gameScene() {
//		 Grid Pane
		board = new GridPane();
		board.setVgap(10);
		board.setHgap(10);
		SetConfigurations();
		board.setStyle("-fx-background-color: #00FFFF");
		board.setAlignment(Pos.CENTER);
		// Menu Bar
		menu = new MenuBar();
		options = new Menu("options");
		AI_H1 = new MenuItem("AI_H1");
		AI_H2 = new MenuItem("AI_H2");
		exit = new MenuItem("exit");
		solution = new MenuItem("solution");
		// setting up menu items
		options.getItems().addAll(AI_H1, AI_H2, solution, exit);
		// setting menu bar
		menu.getMenus().addAll(options);
		menu.setStyle("-fx-background-color: white");
		exit.setOnAction(e -> {
			System.exit(0);
		});
		BorderPane pane = new BorderPane();
		pane.setCenter(board);
		pane.setTop(menu);
		return new Scene(pane, 400, 400);
	}
	public void SetConfigurations() {
		Random randomNumber = new Random();
		int index = randomNumber.nextInt(arrays.size() - 1);
//		tempArr = arrays.get(index);
		tempArr = solving_array;
		int k = 0;
		int num = 0;
//		int emptyRow, emptyCol;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
					GameButton box = new GameButton(tempArr[k]);
					
					num = tempArr[k];
					k++;
					board.add(box, j, i);
					checkArray.add(box);
			}
		}
		for (int i = 0; i < 16; i++) {
			configure(checkArray.get(i));
		}
		
		
	}
	private void configure(GameButton tile) {
		int buttonPos = checkArray.indexOf(tile);
		tile.setOnAction(e -> {
			System.out.println(buttonPos);
			if((buttonPos % 4 != 0) && (checkArray.get(buttonPos - 1).tileNum == 0)) {  // left
				swapTile(tile, checkArray.get(buttonPos - 1));
			} else if (((buttonPos+1) % 4 != 0) && (checkArray.get(buttonPos + 1).tileNum == 0)) {  // right
				swapTile(tile, checkArray.get(buttonPos + 1));
			} else if (((buttonPos - 4) >= 0) && (checkArray.get(buttonPos - 4).tileNum == 0)) {  // up
				swapTile(tile, checkArray.get(buttonPos - 4));
			} else if (((buttonPos + 4) <= 15) && (checkArray.get(buttonPos + 4).tileNum == 0)) {  // down
				swapTile(tile, checkArray.get(buttonPos + 4));
			}
		});
		
	}
	private void swapTile(GameButton tile1, GameButton tile2) { 
//		System.out.println("Swapping " + tile1.tileNum + " with " + tile2.tileNum);
		String temp = tile1.getText();
		int tempNum = tile1.tileNum;
		tile1.setText(tile2.getText());
		tile1.tileNum = tile2.tileNum;
		tile2.setText(temp);
		tile2.tileNum = tempNum;
//		System.out.println("After Swapping " + tile1.tileNum + " with " + tile2.tileNum);
		checkWin();
	}
	private void checkWin() {
		boolean flag = true;
		for (int i = 0; i < 16; i++) {
			int var = checkArray.get(i).tileNum;
			if (var != i) {
				flag = false;
				break;
			}
		}
		if (flag) {
			System.out.println("WON!!");
			PauseTransition halt = new PauseTransition(Duration.seconds(3));
			halt.setOnFinished(e -> {
				dummyStage.setScene(winScene());
//				dummyStage.show();
			});
			halt.play();
		}
	}
	public Scene winScene() {
		Image image = new Image("tenor.gif");
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		BorderPane borderPane = new BorderPane();
		borderPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
		return new Scene(borderPane, 400, 400);
	}

}

