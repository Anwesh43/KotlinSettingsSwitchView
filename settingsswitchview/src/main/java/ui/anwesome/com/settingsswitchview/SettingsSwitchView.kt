package ui.anwesome.com.settingsswitchview

/**
 * Created by anweshmishra on 19/01/18.
 */
import android.app.Activity
import android.graphics.*
import android.view.*
import android.content.*
class SettingsSwitchView(ctx:Context):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val renderer = Renderer(this)
    override fun onDraw(canvas:Canvas) {
        renderer.render(canvas,paint)
    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                renderer.handleTap()
            }
        }
        return true
    }
    data class SettingsSwitch(var x:Float,var y:Float,var size:Float) {
        val state = SettingsSwitchState()
        fun draw(canvas:Canvas,paint:Paint) {
            canvas.save()
            canvas.translate(x,y)
            canvas.save()
            canvas.rotate(45f*state.scale)
            paint.color = Color.parseColor("#757575")
            val path = Path()
            path.moveTo(2*size/5,0f)
            for(i in 0..12) {
                val deg = i*30
                val deg1 = deg + 5
                val deg2 = deg1 + 20
                val deg3 = deg + 30
                val x1 = (size/2)*Math.cos(deg1*Math.PI/180).toFloat()
                val y1 = (size/2)*Math.sin(deg1*Math.PI/180).toFloat()
                val x2 = (size/2)*Math.cos(deg2*Math.PI/180).toFloat()
                val y2 = (size/2)*Math.sin(deg2*Math.PI/180).toFloat()
                val x3 = (2*size/5)*Math.cos(deg3*Math.PI/180).toFloat()
                val y3 = (2*size/5)*Math.sin(deg3*Math.PI/180).toFloat()
                path.lineTo(x1,y1)
                path.lineTo(x2,y2)
                path.lineTo(x3,y3)
            }
            canvas.drawPath(path,paint)
            canvas.restore()
            canvas.save()
            canvas.translate(-size/6,0f)
            canvas.save()
            paint.color = Color.parseColor("#EEEEEE")
            canvas.drawRoundRect(RectF(0f,-size/20,size/3,size/20),size/10,size/10,paint)
            paint.color = Color.parseColor("#01579B")
            canvas.drawRoundRect(RectF(0f,-size/20,size/3*state.scale,size/20),size/10,size/10,paint)
            canvas.drawCircle(-size/10+size/10+size/3*state.scale,0f,size/10,paint)
            canvas.restore()
            canvas.restore()
            canvas.restore()
        }
        fun update(stopcb:(Float)->Unit) {
            state.update(stopcb)
        }
        fun startUpdating(startcb:()->Unit) {
            state.startUpdating(startcb)
        }
    }
    data class SettingsSwitchState(var scale:Float = 0f,var dir:Float = 0f,var prevScale:Float = 0f) {
        fun update(stopcb:(Float)->Unit) {
            scale += 0.1f*dir
            if(Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                stopcb(scale)
            }
        }
        fun startUpdating(startcb:()->Unit) {
            if(dir == 0f) {
                dir = 1 - 2*scale
                startcb()
            }
        }
    }
    data class Animator(var view:SettingsSwitchView,var animated:Boolean = false) {
        fun animate(updatecb:()->Unit) {
            if(animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch(ex:Exception) {

                }
            }
        }
        fun start() {
            if(!animated) {
                animated = true
                view.postInvalidate()
            }
        }
        fun stop() {
            if(animated) {
                animated = false
            }
        }
    }
    data class Renderer(var view:SettingsSwitchView,var time:Int = 0) {
        val animator = Animator(view)
        var settingSwitch:SettingsSwitch?=null
        fun render(canvas:Canvas,paint:Paint) {
            if(time == 0) {
                val w = canvas.width.toFloat()
                val h = canvas.height.toFloat()
                settingSwitch = SettingsSwitch(w/2,h/2,Math.min(w,h)/2)
            }
            canvas.drawColor(Color.parseColor("#212121"))
            settingSwitch?.draw(canvas,paint)
            time++
            animator.animate {
                settingSwitch?.update {
                    animator.stop()
                }
            }
        }
        fun handleTap() {
            settingSwitch?.startUpdating {
                animator.start()
            }
        }
    }
    companion object {
        fun create(activity:Activity):SettingsSwitchView {
            val view = SettingsSwitchView(activity)
            activity.setContentView(view)
            return view
        }
    }
}