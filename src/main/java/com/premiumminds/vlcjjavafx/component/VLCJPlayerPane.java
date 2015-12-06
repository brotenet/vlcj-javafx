package com.premiumminds.vlcjjavafx.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelFormat;
import javafx.scene.layout.BorderPane;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

public class VLCJPlayerPane extends BorderPane{
	/**
	 * Lightweight JavaFX canvas, the video is rendered here.
	 */
	private final Canvas playerCanvas;


	/**
	 * The vlcj direct rendering media player component.
	 */
	private final JavaFXVLCJMediaPlayerComponent mediaPlayerComponent;

	private final AnimationTimer timer;


	private MediaButonsPanel mediaButtonsPanel;

	private static final Logger log = LoggerFactory.getLogger(VLCJPlayerPane.class);

	public VLCJPlayerPane() {
		super();
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				mediaPlayerComponent.renderFrame();
			}
		};

		playerCanvas = new Canvas();
		this.setCenter(playerCanvas);
		mediaPlayerComponent = createMediaPlayerComponent(playerCanvas);
		
		mediaButtonsPanel = new MediaButonsPanel(mediaPlayerComponent.getMediaPlayer());
		this.setBottom(mediaButtonsPanel);

		timer.start();
	}
	private final JavaFXVLCJMediaPlayerComponent createMediaPlayerComponent(Canvas playerCanvas) {
		return new JavaFXVLCJMediaPlayerComponent(playerCanvas.getGraphicsContext2D().getPixelWriter(), PixelFormat.getByteBgraInstance(), new BufferFormatCallback() {
			@Override
			public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
				Platform.runLater(new Runnable () {
					@Override
					public void run() {
						playerCanvas.setWidth(sourceWidth);
						playerCanvas.setHeight(sourceHeight);
					}
				});
				return new RV32BufferFormat(sourceWidth, sourceHeight);
			}
		});
	}
}
