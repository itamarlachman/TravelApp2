package il.co.examplefinalproject2.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;

import il.co.examplefinalproject2.models.Travel;


public class SharedPrefUtils {

   private Context _context;
   private SharedPreferences _pref;

    public SharedPrefUtils(Context _context) {
        this._context = _context;
        _pref = PreferenceManager.getDefaultSharedPreferences(this._context);
    }

    public void saveTravel(Travel travel) {
        _pref.edit()
             .putString("travel", new Gson().toJson(travel))
                .apply();
    }

    public void removeTravel() {
        _pref.edit()
                .remove("travel")
                .apply();
    }

    public Travel loadTravel() {
        Travel travel = null;
        String json = _pref.getString("travel", null);
        if (json!=null) {
            travel =  new Gson().fromJson(json, Travel.class);
        }
        return  travel;
    }

}
