package com.example.bangergame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView : SurfaceView, Runnable {

    var playing = false
    var gameThread : Thread? = null
    lateinit var surfaceHolder : SurfaceHolder
    lateinit var canvas : Canvas
    var killCount = 0
    lateinit var paint :Paint
    var stars = arrayListOf<Star>()
    var enemies = arrayListOf<Enemy>()
    lateinit var player : Player
    lateinit var boom : Boom
    lateinit var collisionBox : Rect
    var failCount : Int = 0
    var lives : Int = 1
    var onGameOver : () -> Unit = {}
    private fun init(context: Context, width: Int, height: Int){

        surfaceHolder = holder
        paint = Paint()
        collisionBox = Rect(10, 0, 0, height)

        for (i in 0..100){
            stars.add(Star(width, height))
        }

        for (i in 0..2){
            enemies.add(Enemy(context,width, height))
        }

        player = Player(context, width, height)
        boom = Boom(context, width, height)

    }

    constructor(context: Context?, width: Int, height: Int) : super(context) {
        init(context!!, width, height)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context!!, 0, 0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context!!, 0, 0)
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread?.start()
    }

    //fun pause() {
    //    playing = false
    //    gameThread?.join()
    //}

    override fun run() {
        while (playing){
            update()
            draw()
            control()
        }
    }


    fun update(){

        boom.x = -300
        boom.y = -300

        for (s in stars){
            s.update(player.speed)
        }
        for (e in enemies){
            e.update(player.speed)
            if (Rect.intersects(player.detectCollision, e.detectCollision)) {
                killCount++

                boom.x = e.x
                boom.y = e.y

                e.x = -300
            }
            if (Rect.intersects(collisionBox, e.detectCollision))
            {
                failCount++
                if(failCount == 5)
                {
                    lives = 0
                }
                e.x = -300
            }

        }
        player.speed = 1 + killCount / 3
        player.update()
    }

    fun draw(){
        if (surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()

            // design code here

            canvas.drawColor(Color.BLACK)

            paint.color = Color.YELLOW

            for (star in stars) {
                paint.strokeWidth = star.starWidth.toFloat()
                canvas.drawPoint(star.x.toFloat(), star.y.toFloat(), paint)
            }

            canvas.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)

            for (e in enemies) {
                canvas.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
            }
            canvas.drawBitmap(boom.bitmap, boom.x.toFloat(), boom.y.toFloat(), paint)



            paint.textSize = 42f
            canvas.drawText("Lives: $lives", 10f, 100f, paint)

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }
    var callGameOverOnce = false
    fun control(){
        Thread.sleep(17)
        if (lives == 0 ){
            playing = false
            Handler(Looper.getMainLooper()).post {
                if (!callGameOverOnce) {
                    onGameOver()
                    callGameOverOnce = true
                }
                gameThread?.join()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val touchY = event.y - 100
                player.updatePosition(touchY)


            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return true
    }
}