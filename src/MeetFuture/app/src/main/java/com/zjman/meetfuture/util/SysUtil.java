package com.zjman.meetfuture.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;

import java.util.HashSet;
import java.util.Set;

public class SysUtil {
	private static final String PREF_NAME = "xxg_palylet";

	private static Application sApp;
	private static Resources sRes;
	private static LayoutInflater sInflater;

	public static void init(Application app) {
		sApp = app;
		sRes = app.getResources();
		sInflater = LayoutInflater.from(app);
	}

	public static  boolean isTempPool(long id) {
		Set<String> group_array = getStringSetPref("group_array");

		return group_array != null && group_array.contains(id + "");
	}


	public static void savePref(String... kvs) {
		if (kvs == null || kvs.length < 2) {
			return;
		}
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		for (int i = 0; i < kvs.length; i = i + 2) {
			editor.putString(kvs[i], kvs[i + 1]);
		}
		editor.apply();
	}

	public static void savePref(String Key, String value) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putString(Key, value);
		editor.apply();
	}


	public static void savePref(String key, boolean value) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}

	public static void savePref(String key, int value) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putInt(key, value);
		editor.apply();
	}

	public static void savePref(String key, float value) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putFloat(key, value);
		editor.apply();
	}

	public static void savePref(String key, long value) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putLong(key, value);
		editor.apply();
	}

	public static void saveStringSet(String key, Set<String> set) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.remove(key);
		editor.apply();//须先remove，否则无效
		pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		editor = pref.edit();
		editor.putStringSet(key, set);
		editor.commit();
	}

	public static Set<String> getStringSetPref(String key) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return pref.getStringSet(key, new HashSet<String>());
	}

	public static String getPref(String key) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return pref.getString(key, null);
	}

	public static Boolean getBooleanPref(String key) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return pref.getBoolean(key, false);
	}

	public static Boolean getBooleanPref(String key, boolean defValue) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return pref.getBoolean(key, defValue);
	}

	public static int getIntPref(String key) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return pref.getInt(key, -1);
	}

	public static float getFloatPref(String key) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return pref.getFloat(key, -1);
	}

	public static long getLongPref(String key) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return pref.getLong(key, 0l);
	}

	public static void removePref(String key) {
		SharedPreferences pref = sApp.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.remove(key);
		editor.apply();
	}

	public static String getString(int res) {
		return sRes.getString(res);
	}

	public static String getString(int resId, Object... formatArgs) {
		return sRes.getString(resId, formatArgs);
	}

	public static DisplayMetrics getDisplayMetrics() {
		return sApp.getResources().getDisplayMetrics();
	}
}