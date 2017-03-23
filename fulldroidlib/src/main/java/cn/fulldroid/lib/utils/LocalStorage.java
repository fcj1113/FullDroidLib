package cn.fulldroid.lib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 保存本地数据
 * @author 
 *
 */
public class LocalStorage {

	private SharedPreferences sp;
	private Context mContext;

	public LocalStorage(Context context) {
		try {
			this.mContext = context;
			if (sp == null) {
				sp = mContext.getSharedPreferences("config", Context.MODE_PRIVATE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void putString(String key, String value) {
		try {
			Editor editor = sp.edit();
			editor.putString(key, value);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getString(String key, String defValue) {
		String value = sp.getString(key, "");
		return value;
	}

	public void putBoolean(String key, boolean value) {
		try {
			Editor editor = sp.edit();
			editor.putBoolean(key, value);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean getBoolean(String key, boolean defValue) {
		boolean value = sp.getBoolean(key, defValue);
		return value;
	}

	public void putFloat(String key, float value) {
		try {
			Editor editor = sp.edit();
			editor.putFloat(key, value);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public float getFloat(String key, float defValue) {
		float value = sp.getFloat(key, defValue);
		return value;
	}

	public void putInt(String key, int value) {
		try {
			Editor editor = sp.edit();
			editor.putInt(key, value);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getInt(String key, int defValue) {
		int value = sp.getInt(key, defValue);
		return value;
	}

	public void putLong(String key, long value) {
		try {
			Editor editor = sp.edit();
			editor.putLong(key, value);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getLong(String key, long defValue) {
		long value = sp.getLong(key, defValue);
		return value;
	}
	
	@SuppressLint("CommitPrefEdits")
	public void clear(){
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();  
	}
}
