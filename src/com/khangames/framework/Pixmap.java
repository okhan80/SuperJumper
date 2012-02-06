package com.khangames.framework;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/10/12
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Pixmap {
    public int getWidth();
    
    public int getHeight();

    public Graphics.PixmapFormat getFormat();

    public void dispose();

}
