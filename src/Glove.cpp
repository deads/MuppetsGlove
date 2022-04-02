/**
 * Name:   Glove.cpp
 * Author: Damian Eads, James Tranovich
 * Id:     $Id: Glove.cpp,v 1.4 2004/03/13 00:32:19 dre9227 Exp $
 *
 * RITGlove: A Java interface to the 5DT glove.
 * Copyright (C) Damian Eads and James Tranovich, 2004-2005
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

#include "Glove.h"
#include "fglove.h"
#include <string.h>
#define MAX_GLOVES 128

static fdGlove* __glove_gloves[MAX_GLOVES];
static int __glove_ngloves = 0;

/** Initialize the glove array. */
JNIEXPORT void JNICALL Java_gnu_dtools_ritglove_NativeGlove_initGloveArray
(JNIEnv *env, jclass cls){
	for ( int i = 0; i < MAX_GLOVES; i++ ) {
		__glove_gloves[i] = 0;
	}
}

jint grabFileDescriptorId( JNIEnv *env, jobject o ) {
	jclass cls = env->FindClass("muppets/ext/glove/NativeGlove");
	jfieldID fieldId = env->GetFieldID(cls, "gloveId", "I");
	jint fid = env->GetIntField(o, fieldId);
	return fid;
}

int checkSensorNumber(JNIEnv *env, fdGlove *glove, jint sensorNumber) {
	if ( sensorNumber > fdGetNumSensors(glove) || sensorNumber < 0) {
		jclass newExcCls;
        env->ExceptionDescribe();
        env->ExceptionClear();
		newExcCls = env->FindClass("muppets/ext/glove/NoSuchSensorException");
		if (newExcCls == 0) { /** Exception class couldn't be found. */
			return -1;
		}
		env->ThrowNew(newExcCls,
					  "Sensor values must be nonnegative and may not exceed the total number of sensors available.");
	}
	return 0;
}

int checkFileDescriptorId(JNIEnv *env, jint fid) {
	if ( fid < 0 || fid >= MAX_GLOVES ) {
		jclass newExcCls;
        env->ExceptionDescribe();
        env->ExceptionClear();
		newExcCls = env->FindClass("muppets/ext/glove/GloveConnectionException");
		if (newExcCls == 0) { /** Exception class couldn't be found. */
			return -1;
		}
		env->ThrowNew(newExcCls, "Invalid file descriptor.");
	}
	return 0;
}

JNIEXPORT jint JNICALL Java_gnu_dtools_ritglove_NativeGlove_nativeOpen
(JNIEnv *env, jobject o, jstring s) {
	int id = 0;
	fdGlove * fid;
	/** If the number of gloves open has been exceeded, throw an exception. */
	if ( __glove_ngloves == MAX_GLOVES ) {
		jclass newExcCls;
        env->ExceptionDescribe();
        env->ExceptionClear();
		newExcCls = env->FindClass("muppets/ext/glove/GloveConnectionException");
		if (newExcCls == 0) { /** Exception class couldn't be found. */
			return -1;
		}
		env->ThrowNew(newExcCls, "Too many gloves open!");
	}

	/** Look for a free slot in the gloves array. */
	int free_slot = -1;
	for ( int i = 0; i < MAX_GLOVES; i++ ) {
		if ( __glove_gloves[ i ] == 0 ) {
			free_slot = i;
			break;
		}
	}

	/** If the free slot couldn't be found, throw an exception. */
	if ( free_slot == -1 ) {
		jclass newExcCls;
        env->ExceptionDescribe();
        env->ExceptionClear();
		newExcCls = env->FindClass("muppets/ext/glove/GloveConnectionException");
		if (newExcCls == 0) { /** Exception class couldn't be found. */
			return -1;
		}
		env->ThrowNew(newExcCls, "Too many gloves open!");
	}

	/** Let's attempt to make the connection. */
	const char *str = env->GetStringUTFChars(s, 0);

	char *str2 = strdup(str);

	fid = fdOpen( str2 );

	delete [] str2;
	env->ReleaseStringUTFChars(s, str);
	__glove_ngloves++;
	__glove_gloves[ free_slot ] = fid;
	return free_slot;
}

JNIEXPORT jint JNICALL Java_gnu_dtools_ritglove_NativeGlove_nativeClose
(JNIEnv *env, jobject o, jint fid) {
	int status = 0;
	if ( fid < 0 || fid >= MAX_GLOVES ) {
		jclass newExcCls;
        env->ExceptionDescribe();
        env->ExceptionClear();
		newExcCls = env->FindClass("muppets/ext/glove/GloveConnectionException");
		if (newExcCls == 0) { /** Exception class couldn't be found. */
			return -1;
		}
		env->ThrowNew(newExcCls, "Invalid file descriptor.");
	}
	status = fdClose(__glove_gloves[fid]);
	__glove_gloves[fid] = 0;
	__glove_ngloves--;
	return status;
}

JNIEXPORT jint JNICALL Java_gnu_dtools_ritglove_NativeGlove_getNumberOfSensors
(JNIEnv *env, jobject o) {
    jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	return fdGetNumSensors(__glove_gloves[fid]);
}

JNIEXPORT jint JNICALL Java_gnu_dtools_ritglove_NativeGlove_getGloveType
(JNIEnv *env, jobject o) {
    jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	return fdGetGloveType(__glove_gloves[fid]);
}

JNIEXPORT jint JNICALL Java_gnu_dtools_ritglove_NativeGlove_getHandedness
(JNIEnv *env, jobject o) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	return fdGetGloveHand(__glove_gloves[fid]);
}

JNIEXPORT jshort JNICALL Java_gnu_dtools_ritglove_NativeGlove_getRawSensorData__I
(JNIEnv *env, jobject o, jint sensorNumber) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	checkSensorNumber(env, __glove_gloves[fid], sensorNumber);
	return fdGetSensorRaw(__glove_gloves[fid], sensorNumber);
}

JNIEXPORT jshortArray JNICALL Java_gnu_dtools_ritglove_NativeGlove_getRawSensorData__
(JNIEnv *env, jobject o) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	int num_sensors = fdGetNumSensors(__glove_gloves[fid]);
	jshortArray retval = env->NewShortArray(num_sensors);;
	unsigned short *data = new unsigned short[ num_sensors ];
	fdGetSensorRawAll(__glove_gloves[fid], data);
	env->SetShortArrayRegion(retval, 0, num_sensors, (jshort*)data);
	delete [] data;
	return retval;
}

JNIEXPORT void JNICALL Java_gnu_dtools_ritglove_NativeGlove_setRawSensorData__IS
(JNIEnv *env, jobject o, jint sensorNumber, jshort value) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	checkSensorNumber(env, __glove_gloves[fid], sensorNumber);
	fdSetSensorRaw(__glove_gloves[fid], sensorNumber, value);
}

JNIEXPORT void JNICALL Java_gnu_dtools_ritglove_NativeGlove_setRawSensorData___3S
(JNIEnv *env, jobject o, jshortArray values) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	jshort *sh = env->GetShortArrayElements(values, 0);
	fdSetSensorRawAll(__glove_gloves[fid], (unsigned short*)sh);
}

JNIEXPORT jfloat JNICALL Java_gnu_dtools_ritglove_NativeGlove_getScaledSensorData__I
  (JNIEnv *env, jobject o, jint sensorNumber) {
    jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	checkSensorNumber(env, __glove_gloves[fid], sensorNumber);
	return fdGetSensorScaled(__glove_gloves[fid], sensorNumber);
}

JNIEXPORT jfloatArray JNICALL Java_gnu_dtools_ritglove_NativeGlove_getScaledSensorData__
(JNIEnv *env, jobject o) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	int num_sensors = fdGetNumSensors(__glove_gloves[fid]);
	jfloatArray array = env->NewFloatArray(num_sensors);
	jfloat* elements = env->GetFloatArrayElements(array, 0);
	fdGetSensorScaledAll(__glove_gloves[fid], (float*)elements);
	return array;
}

JNIEXPORT jint JNICALL Java_gnu_dtools_ritglove_NativeGlove_getNumberOfGestures
(JNIEnv *env, jobject o) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	return fdGetNumGestures(__glove_gloves[fid]);
}

JNIEXPORT jint JNICALL Java_gnu_dtools_ritglove_NativeGlove_getGesture
(JNIEnv *env, jobject o) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	jint gesture = fdGetGesture(__glove_gloves[fid]);
	return gesture;
}

JNIEXPORT jshortArray JNICALL Java_gnu_dtools_ritglove_NativeGlove_getCalibration
(JNIEnv *env, jobject o, jint sensorNumber) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	checkSensorNumber(env, __glove_gloves[fid], sensorNumber);
	jshortArray retval = env->NewShortArray(2);
	jshort *values = env->GetShortArrayElements(retval, 0);
	unsigned short lower = 0, upper = 0;
	fdGetCalibration(__glove_gloves[fid], sensorNumber, &upper, &lower);
	values[ 1 ] = upper;
	values[ 0 ] = lower;
	return retval;
}

JNIEXPORT void JNICALL Java_gnu_dtools_ritglove_NativeGlove_setCalibration
(JNIEnv *env, jobject o, jint sensorNumber, jshort lower, jshort upper) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	checkSensorNumber(env, __glove_gloves[fid], sensorNumber);
	fdSetCalibration(__glove_gloves[fid], sensorNumber, upper, lower);
}

JNIEXPORT void JNICALL Java_gnu_dtools_ritglove_NativeGlove_resetCalibration
(JNIEnv *env, jobject o) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	fdResetCalibration(__glove_gloves[fid]);
}

JNIEXPORT jfloatArray JNICALL Java_gnu_dtools_ritglove_NativeGlove_getSensorMaximums
(JNIEnv *env, jobject o) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	int num_sensors = fdGetNumSensors(__glove_gloves[fid]);
	jfloatArray array = env->NewFloatArray(num_sensors);
	jfloat *elements = env->GetFloatArrayElements(array, 0);
	fdGetSensorMaxAll(__glove_gloves[fid], (float*)elements);
	return array;
}

JNIEXPORT jfloat JNICALL Java_gnu_dtools_ritglove_NativeGlove_getSensorMaximum
(JNIEnv *env, jobject o, jint sensorNumber) {
    jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	checkSensorNumber(env, __glove_gloves[fid], sensorNumber);
	jfloat retval = fdGetSensorMax(__glove_gloves[fid], sensorNumber);
	return retval;
}

JNIEXPORT void JNICALL Java_gnu_dtools_ritglove_NativeGlove_setSensorMaximums
(JNIEnv *env, jobject o, jfloatArray maximums) {
	jint fid = grabFileDescriptorId(env, o);
	checkFileDescriptorId(env, fid);
	int num_sensors = fdGetNumSensors(__glove_gloves[fid]);
	jfloat *elements = env->GetFloatArrayElements(maximums, 0);
	fdSetSensorMaxAll(__glove_gloves[fid], (float*)elements);
}
