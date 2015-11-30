package com.premiumminds.vlcjjavafx.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelFormat;
import javafx.scene.layout.BorderPane;

public class VLCJPlayerPane extends BorderPane{
	/**
	 * Lightweight JavaFX canvas, the video is rendered here.
	 */
	private final Canvas canvas;

	
	/**
	 * The vlcj direct rendering media player component.
	 */
	private final JavaFXVLCJMediaPlayerComponent mediaPlayerComponent;
	
	private final AnimationTimer timer;
	 
	private static final Logger log = LoggerFactory.getLogger(VLCJPlayerPane.class);

	public VLCJPlayerPane() {
		super();
		timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mediaPlayerComponent.renderFrame();
            }
        };
        
		canvas = new Canvas();
		this.setCenter(canvas);
		canvas.setWidth(1280);
		canvas.setHeight(720);
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		gc.setFill(Color.BLACK);
//		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		mediaPlayerComponent = new JavaFXVLCJMediaPlayerComponent(canvas.getGraphicsContext2D().getPixelWriter(), PixelFormat.getByteBgraInstance());
		mediaPlayerComponent.getMediaPlayer().playMedia("c:\\one-punch.mp4");
        //mediaPlayerComponent.getMediaPlayer().setPosition(0.7f);

		//mediaPlayerComponent.renderFrame();
		timer.start();
	}

}
