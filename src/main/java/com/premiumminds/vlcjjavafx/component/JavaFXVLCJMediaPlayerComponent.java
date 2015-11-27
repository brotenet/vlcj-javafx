package com.premiumminds.vlcjjavafx.component;

import java.nio.ByteBuffer;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritablePixelFormat;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.DefaultDirectMediaPlayer;

import com.sun.jna.Memory;

public class JavaFXVLCJMediaPlayerComponent extends DirectMediaPlayerComponent{
	/**
	 * Pixel writer to update the canvas.
	 */
	private final PixelWriter pixelWriter;

	/**
	 * Pixel format.
	 */
	private final WritablePixelFormat<ByteBuffer> pixelFormat;

	public JavaFXVLCJMediaPlayerComponent(PixelWriter pixelWriter, WritablePixelFormat<ByteBuffer> pixelFormat) {
		super(new BufferFormatCallbackImpl());
		this.pixelFormat = pixelFormat;
		this.pixelWriter = pixelWriter;
		 
	}

	 /**
    *
    */
   protected final void renderFrame() {
       Memory[] nativeBuffers = this.getMediaPlayer().lock();
       if (nativeBuffers != null) {
           // FIXME there may be more efficient ways to do this...
           // Since this is now being called by a specific rendering time, independent of the native video callbacks being
           // invoked, some more defensive conditional checks are needed
           Memory nativeBuffer = nativeBuffers[0];
           if (nativeBuffer != null) {
               ByteBuffer byteBuffer = nativeBuffer.getByteBuffer(0, nativeBuffer.size());
               BufferFormat bufferFormat = ((DefaultDirectMediaPlayer) this.getMediaPlayer()).getBufferFormat();
               if (bufferFormat.getWidth() > 0 && bufferFormat.getHeight() > 0) {
                   pixelWriter.setPixels(0, 0, bufferFormat.getWidth(), bufferFormat.getHeight(), pixelFormat, byteBuffer, bufferFormat.getPitches()[0]);
               }
           }
       }
       this.getMediaPlayer().unlock();
   }
}
