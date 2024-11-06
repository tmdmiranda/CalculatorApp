package com.example.bangergame.ui.theme

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView : SurfaceView,Runnable {

    var playing = false
    var gameThread: Thread? = null
    lateinit var surfaceHolder:SurfaceHolder
    lateinit var canvas : android.graphics.Canvas

    private fun init(context:Context,width:Int,height:Int){
        surfaceHolder = holder
    }

    constructor(context: Context?) : super(context){init(context!!, width, height)}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){init(context!!, 0, 0)}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){init(context!!, 0, 0)}

    fun resume() {
        playing=true
        gameThread = Thread(this)
        gameThread?.start()
    }
    fun pause() {
        playing = false
        gameThread?.join()

    }
    override fun run(){
        while(playing){
            update()
            draw()
            control()
        }
    }

    fun update(){

    }

    fun draw(){
        if (surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()


            
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control(){
        Thread.sleep(17)
    }
}