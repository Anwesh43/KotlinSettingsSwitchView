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
    data class SettingsSwitch(var x:Float,var y:Float,var size:Float) {
        fun draw(canvas:Canvas,paint:Paint) {
            canvas.save()
            canvas.translate(x,y)
            canvas.save()
            canvas.rotate(90f)
            canvas.restore()
            canvas.save()
            canvas.translate(-size/6,0f)
            canvas.save()
            paint.color = Color.parseColor("#EEEEEE")
            canvas.drawRoundRect(RectF(0f,-size/10,size/3,size/10),size/10,size/10,paint)
            canvas.drawCircle(-size/10+size/7+size/3,0f,size/7,paint)
            paint.color = Color.parseColor("#01579B")
            canvas.drawRoundRect(RectF(0f,-size/10,size/3,size/10),size/10,size/10,paint)
            canvas.restore()
            canvas.restore()
            canvas.restore()
        }
        fun update(stopcb:(Float)->Unit) {

        }
        fun startUpdating(startcb:()->Unit) {

        }
    }
}