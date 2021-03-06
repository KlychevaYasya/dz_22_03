package application;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Main extends Application {
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage theStage)
	{
	
		theStage.setTitle ("Example");
		
		Group root = new Group ();
		Scene theScene = new Scene (root);
		theStage.setScene (theScene);
		
		Canvas canvas = new Canvas(500, 500);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		
		Image background = new Image("rectangle.jpg");
		Image car = new Image("Car.jpg");
		
		ArrayList<String> input = new ArrayList<String>();
		
		theScene.setOnKeyPressed (
			new EventHandler<KeyEvent> () {
				public void handle(KeyEvent e) {
					String code = e.getCode().toString();
					
					if (!input.contains(code))
						input.add(code);
				}
			});
		
		theScene.setOnKeyReleased (
				new EventHandler<KeyEvent>() {
					public void handle(KeyEvent e) {
						String code = e.getCode().toString();
						
						input.remove(code);
					}
				});
		
		new AnimationTimer() {
			long x = 250;
			double t = 0;
			double s = 10;
			public void handle(long currentNanoTime) {
				
				if (t>500) {
					t = 0;
				} else {
					t = Math.abs(t+s);
				}
				gc.drawImage(background, 0, t);
				gc.drawImage(background, 0, t-1024);
				
				if (input.contains("LEFT")) {
						x = x-10;
				}
				if (input.contains("RIGHT")) {
						x = x+10;
					}
					gc.drawImage (car,x, 250);
				}
				
			}.start();
			theStage.show();
			}
		}
	

