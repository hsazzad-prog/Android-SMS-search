LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := com.example.smstest
LOCAL_SRC_FILES := com.example.smstest.cpp

include $(BUILD_SHARED_LIBRARY)
