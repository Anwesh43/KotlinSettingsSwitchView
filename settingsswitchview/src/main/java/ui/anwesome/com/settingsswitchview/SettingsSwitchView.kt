package ui.anwesome.com.settingsswitchview

/**
 * Created by anweshmishra on 19/01/18.
 */
import android.graphics.*
import android.view.*
import android.content.*
class SettingsSwitchView(ctx:Context):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                
            }
        }
        return true
    }
}