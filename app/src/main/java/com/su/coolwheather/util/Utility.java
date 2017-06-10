package com.su.coolwheather.util;

import android.text.TextUtils;

import com.su.coolwheather.bean.City;
import com.su.coolwheather.bean.County;
import com.su.coolwheather.bean.Province;
import com.su.coolwheather.db.CoolWheatherDB;

/**
 * Created by su on 2017/6/10.
 */

public class Utility {
    public synchronized static boolean handleProvinceResponse(CoolWheatherDB coolWheatherDB,String response){
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces!=null&&allProvinces.length>0){
                for (String p:allProvinces
                     ) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWheatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }
    public synchronized static boolean handleCityResponse(CoolWheatherDB coolWheatherDB,String response,int provinceID){
        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities!=null&&allCities.length>0){
                for (String c:allCities
                     ) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceID(provinceID);
                    coolWheatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }
    public synchronized static boolean handleCountyResponse(CoolWheatherDB coolWheatherDB,String response,int cityID){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties!=null&&allCounties.length>0){
                for (String c:allCounties
                     ) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityID(cityID);
                    coolWheatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
