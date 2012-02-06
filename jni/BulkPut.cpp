#include "BulkPut.h"
#include <stdio.h>
#include <string.h>

JNIEXPORT void JNICALL Java_com_khangames_framework_gl_BufferUtils_copyJni
    (JNIEnv *env, jclass clazz, jfloatArray src, jobject dst, jint numFloats, jint offset)
{
    float* pDst = (float*)env->GetDirectBufferAddress(dst);
    float* pSrc = (float*)env->GetPrimitiveArrayCritical(src, 0);

    memcpy(pDst, pSrc + (offset << 2), numFloats << 2 );

    env->ReleasePrimitiveArrayCritical(src, pSrc, 0);
}