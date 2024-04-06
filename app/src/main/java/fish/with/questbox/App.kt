package fish.with.questbox

import android.app.Application
import android.app.NotificationManager
import android.content.ClipboardManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.preference.PreferenceManager
import fish.with.questbox.util.LogUtil

class App : Application() {
    companion object {
        lateinit var context: Context
        lateinit var sp: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        lateinit var spSettings: SharedPreferences
        const val TAG = "QuestBox"

        lateinit var inputMethodManager: InputMethodManager
    }

    override fun onCreate() {
        super.onCreate()
        LogUtil._d("App started")

        context = applicationContext
        sp = context.getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE)
        spSettings = PreferenceManager.getDefaultSharedPreferences(context)
        editor = sp.edit()

        inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    }
}