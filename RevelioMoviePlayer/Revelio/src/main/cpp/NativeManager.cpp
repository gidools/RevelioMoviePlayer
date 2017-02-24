//
// Created by Charles on 11/24/16.
//
#include <jni.h>
#include <android/log.h>
#include "ViewMode.h"

extern "C"
{
	jint Java_com_maxst_revelio_NativeManager_setViewMode(JNIEnv *env, jobject obj, jint type)
	{
		__android_log_print(ANDROID_LOG_ERROR, "NativeManager", "setViewMode:%d", type);
		return gpio_set_value(type);

	}
}