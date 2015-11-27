package com.premiumminds.vlcjjavafx.component;

import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

public class BufferFormatCallbackImpl implements BufferFormatCallback {

	@Override
	public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
		final int width;
		final int height;
//		if (useSourceSize) {
			width = sourceWidth;
			height = sourceHeight;
//		}
//		else {
//			width = WIDTH;
//			height = HEIGHT;
//		}
//		Platform.runLater(new Runnable () {
//			@Override
//			public void run() {
//				canvas.setWidth(width);
//				canvas.setHeight(height);
//				stage.setWidth(width);
//				stage.setHeight(height);
//			}
//		});
		return new RV32BufferFormat(width, height);
	}
}
