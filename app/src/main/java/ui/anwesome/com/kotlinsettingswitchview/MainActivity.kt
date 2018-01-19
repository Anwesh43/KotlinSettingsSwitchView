package ui.anwesome.com.kotlinsettingswitchview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.settingsswitchview.SettingsSwitchView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsSwitchView.create(this)
    }
}
