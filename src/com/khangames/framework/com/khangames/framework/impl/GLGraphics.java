package com.khangames.framework.com.khangames.framework.impl;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/23/12
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class GLGraphics {
    GLSurfaceView glView;
    GL10 gl;

    GLGraphics(GLSurfaceView glView) {
        this.glView = glView;
        
    }
    
    public GL10 getGL() {
        return gl;
    }
    
    void setGl(GL10 gl) {
        this.gl = gl;
    }
    
    public int getWidth() {
        return glView.getWidth();
    }
    
    public int getHeight() {
        return glView.getHeight();
    }
            
}
