package com.khangames.framework.gl;

import com.khangames.framework.com.khangames.framework.impl.GLGraphics;

import javax.microedition.khronos.opengles.GL10;
import java.lang.Short;import java.nio.ByteBuffer;import java.nio.ByteOrder;import java.nio.FloatBuffer;import java.nio.ShortBuffer;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/23/12
 * Time: 2:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vertices {
    final GLGraphics glGraphics;
    final boolean hasColor;
    final boolean hasTexCoords;
    final int vertexSize;
    final FloatBuffer vertices;
    final ShortBuffer indices;
    //final int[] tmpBuffer;
    
    public Vertices(GLGraphics glGraphics, int maxVertices, int maxIndices, boolean hasColor, boolean hasTexCoords) {
        this.glGraphics = glGraphics;
        this.hasColor = hasColor;
        this.hasTexCoords = hasTexCoords;
        this.vertexSize = (2 + (hasColor ? 4 : 0) + (hasTexCoords ? 2 : 0)) * 4;
        //this.tmpBuffer = new int[maxVertices * vertexSize / 4];

        ByteBuffer buffer = ByteBuffer.allocateDirect(maxVertices * vertexSize);
        buffer.order(ByteOrder.nativeOrder());
        this.vertices = buffer.asFloatBuffer();
        buffer.flip();
        this.vertices.flip();

        if(maxIndices > 0) {
            buffer = ByteBuffer.allocateDirect(maxIndices * Short.SIZE / 8);
            buffer.order(ByteOrder.nativeOrder());
            indices = buffer.asShortBuffer();
            
        } else {
            indices = null;
        }
    }
    
    public void setVertices(float[] vertices, int offset, int length) {
        this.vertices.clear();
//        for(int i = offset, j = 0; i < len; i++, j++) {
//            //tmpBuffer[j] = Float.floatToRawIntBits(vertices[i]);
//
//        }
        BufferUtils.copy(vertices, this.vertices, length, offset);
        //this.vertices.put(vertices, offset, length);
        //this.vertices.flip();
    }
    
    public void setIndices(short[] indices, int offset, int length) {
        this.indices.clear();
        this.indices.put(indices, offset, length);
        this.indices.flip();
    }
    public void bind() {
        GL10 gl = glGraphics.getGL();


        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        vertices.position(0);
        gl.glVertexPointer(2, GL10.GL_FLOAT, vertexSize, vertices);

        if(hasColor) {
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            vertices.position(2);
            gl.glColorPointer(4, GL10.GL_FLOAT, vertexSize, vertices);
        }

        if(hasTexCoords) {
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            vertices.position(hasColor ? 6 : 2);
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, vertexSize, vertices);
        }

    }
    
    public void draw(int primitiveType, int offset, int numVertices) {
        GL10 gl = glGraphics.getGL();

        
        if(indices != null) {
            indices.position(offset);
            gl.glDrawElements(primitiveType, numVertices, GL10.GL_UNSIGNED_SHORT, indices);
        } else {
            gl.glDrawArrays(primitiveType, offset, numVertices);
        }

    }
    public void unbind() {
        GL10 gl = glGraphics.getGL();

        if(hasTexCoords) {
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        }

        if(hasColor) {
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        }
    }
}
