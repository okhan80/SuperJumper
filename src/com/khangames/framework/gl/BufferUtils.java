package com.khangames.framework.gl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;


/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/31/12
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class BufferUtils {
    private native static void copyJni(float[] src, Buffer dst, int numFloats, int offset);
    static {
        System.loadLibrary("gl");
    }
    public static void copy(float[] src, Buffer dst, int numFloats, int offset) {
        copyJni(src, dst, numFloats, offset);
        dst.position(0);

        if(dst instanceof ByteBuffer) {
            dst.limit(numFloats << 2);
        } else if(dst instanceof FloatBuffer) {
            dst.limit(numFloats);
        }
    }
}
