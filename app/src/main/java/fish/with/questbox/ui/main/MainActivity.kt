package fish.with.questbox.ui.main

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import fish.with.questbox.App
import fish.with.questbox.databinding.ActivityMainBinding
import fish.with.questbox.util.LogUtil


class MainActivity : Activity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.keyboardButton.setOnClickListener {
            LogUtil.d(App.inputMethodManager.enabledInputMethodList)
            startActivity(Intent().apply {
                action = Intent.ACTION_MAIN
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                component = ComponentName(
                    "com.android.settings",
                    "com.android.settings.Settings\$AvailableVirtualKeyboardActivity"
                )
            })
        }
    }
}