package com.example.bangergame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import kotlin.time.TimeSource
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

class Player {

    var x = 0
    var y = 0
    var speed = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var bitmap : Bitmap


    private val MAX_SPEED = 100
    private val MIN_SPEED = 0

    var detectCollision : Rect

    constructor(context: Context, width: Int, height: Int){
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)

        minX = 0
        maxX = width

        maxY = height - bitmap.height
        minY = 0

        x = 75
        y = 50

        speed = 1

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(){


        if (speed > MAX_SPEED) speed = MAX_SPEED
        if (speed < MIN_SPEED) speed = MIN_SPEED


        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height


    }

    fun updatePosition(newY : Float){
        y = newY.toInt().coerceIn(minY, maxY)
        update()
    }

}