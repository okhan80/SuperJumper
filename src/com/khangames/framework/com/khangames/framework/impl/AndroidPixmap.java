package com.khangames.framework.com.khangames.framework.impl;

import android.graphics.Bitmap;
import com.khangames.framework.Graphics;
import com.khangames.framework.Pixmap;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/17/12
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    Graphics.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graphics.PixmapFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
