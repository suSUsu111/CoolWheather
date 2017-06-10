package com.su.coolwheather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.su.coolwheather.bean.City;
import com.su.coolwheather.bean.County;
import com.su.coolwheather.bean.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by su on 2017/6/9.
 */

public class CoolWheatherDB {
    public static final String DB_NAME = "cool_weather";
    public static final int DB_VERSION = 1;
    private static CoolWheatherDB coolWheatherDB;
    private SQLiteDatabase db;


    private CoolWheatherDB(Context context) {
        CoolWheatherOpenHelper dbHelper = new CoolWheatherOpenHelper(context, DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取coolwheatherDB实例
     *
     * @param context
     * @return
     */
    public synchronized static CoolWheatherDB getInstance(Context context) {
        if (coolWheatherDB == null) {
            coolWheatherDB = new CoolWheatherDB(context);
        }
        return coolWheatherDB;
    }

    /**
     * 将province实例存储到数据库
     *
     * @param province
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("province_name", province.getProvinceName());
            contentValues.put("province_code", province.getProvinceCode());
            db.insert("Province", null, contentValues);
        }
    }

    public List<Province> loadProvinces() {
        ArrayList<Province> provincesList = new ArrayList<>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                provincesList.add(province);
            } while (cursor.moveToNext());
        }
        return provincesList;
    }

    public void saveCity(City city) {
        if (city != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("city_name", city.getCityName());
            contentValues.put("city_code", city.getCityCode());
            contentValues.put("province_id", city.getProvinceID());
            db.insert("City", null, contentValues);
        }
    }

    public List<City> loadCities(int provinceID) {
        ArrayList<City> citiesLidt = new ArrayList<>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[]{String.valueOf(provinceID)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceID(provinceID);
                citiesLidt.add(city);
            } while (cursor.moveToNext());
        }
            return citiesLidt;
    }
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("county_name", county.getCountyName());
            contentValues.put("county_code", county.getCountyCode());
            contentValues.put("city_id", county.getCityID());
            db.insert("County", null, contentValues);
        }
    }
    public List<County> loadCounty(int cityID) {
        ArrayList<County> countyLidt = new ArrayList<>();
        Cursor cursor = db.query("County", null, "city_id = ?", new String[]{String.valueOf(cityID)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityID(cityID);
                countyLidt.add(county);
            } while (cursor.moveToNext());
        }
        return countyLidt;
    }
}