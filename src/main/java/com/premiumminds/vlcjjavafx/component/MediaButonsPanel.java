package com.premiumminds.vlcjjavafx.component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

public class MediaButonsPanel extends HBox {
	
	private Button play, pause, volUp, volDown;

	public MediaButonsPanel(DirectMediaPlayer mediaPlayer) {
		super();
		play = new Button(">");
		pause = new Button("||");
		volUp = new Button("+");
		volDown = new Button("-");
		
		play.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent event) {
				mediaPlayer.prepareMedia("c:\\one-punch.mp4");
				if(!mediaPlayer.isPlaying()) {
					mediaPlayer.play();
				}
			}
		});
		
		pause.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent event) {
				if(mediaPlayer.canPause()) {
					mediaPlayer.pause();
				}
			}
		});
		
		volUp.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent event) {
					int curVol = mediaPlayer.getVolume();
					mediaPlayer.setVolume(curVol <= 190 ? curVol + 10 : 200);
			}
		});
		
		volDown.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent event) {
				int curVol = mediaPlayer.getVolume();
				mediaPlayer.setVolume(curVol >=10 ? curVol - 10 : 0);
			}
		});
		
		this.getChildren().addAll(play, pause, volUp, volDown);
		
	}

}
