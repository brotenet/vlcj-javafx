package com.premiumminds.vlcjjavafx.component;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelFormat;
import javafx.scene.layout.BorderPane;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;

public class VLCJPlayerPane extends BorderPane {
	/**
	 * Lightweight JavaFX canvas, the video is rendered here.
	 */
	private final Canvas canvas;

	
	/**
	 * The vlcj direct rendering media player component.
	 */
	private final DirectMediaPlayerComponent mediaPlayerComponent;


	public VLCJPlayerPane() {
		super();
		canvas = new Canvas();
		this.setCenter(canvas);

		mediaPlayerComponent = new JavaFXVLCJMediaPlayerComponent(canvas.getGraphicsContext2D().getPixelWriter(), PixelFormat.getByteBgraInstance());
	}

}
