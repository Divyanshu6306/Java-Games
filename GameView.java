{\rtf1\ansi\ansicpg1252\cocoartf2822
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 package com.example.tappyball;\
\
import android.content.Context;\
import android.graphics.Canvas;\
import android.graphics.Color;\
import android.graphics.Paint;\
import android.view.MotionEvent;\
import android.view.SurfaceHolder;\
import android.view.SurfaceView;\
\
public class GameView extends SurfaceView implements Runnable \{\
\
    private Thread gameThread;\
    private boolean isPlaying;\
    private Paint paint;\
    private SurfaceHolder surfaceHolder;\
\
    private float ballX, ballY;\
    private float ballRadius = 60;\
    private float ballSpeed = 10;\
    private int score = 0;\
\
    public GameView(Context context) \{\
        super(context);\
        surfaceHolder = getHolder();\
        paint = new Paint();\
        resetBall();\
    \}\
\
    @Override\
    public void run() \{\
        while (isPlaying) \{\
            update();\
            draw();\
            control();\
        \}\
    \}\
\
    private void update() \{\
        ballY += ballSpeed;\
\
        if (ballY > getHeight()) \{\
            isPlaying = false; // Game Over\
        \}\
    \}\
\
    private void draw() \{\
        if (surfaceHolder.getSurface().isValid()) \{\
            Canvas canvas = surfaceHolder.lockCanvas();\
            canvas.drawColor(Color.WHITE);\
\
            paint.setColor(Color.BLUE);\
            canvas.drawCircle(ballX, ballY, ballRadius, paint);\
\
            paint.setColor(Color.BLACK);\
            paint.setTextSize(60);\
            canvas.drawText("Score: " + score, 50, 100, paint);\
\
            if (!isPlaying) \{\
                paint.setTextSize(100);\
                canvas.drawText("Game Over", getWidth() / 4, getHeight() / 2, paint);\
            \}\
\
            surfaceHolder.unlockCanvasAndPost(canvas);\
        \}\
    \}\
\
    private void control() \{\
        try \{\
            Thread.sleep(17); // ~60fps\
        \} catch (InterruptedException e) \{\
            e.printStackTrace();\
        \}\
    \}\
\
    public void pause() \{\
        isPlaying = false;\
        try \{\
            gameThread.join();\
        \} catch (InterruptedException e) \{\
            e.printStackTrace();\
        \}\
    \}\
\
    public void resume() \{\
        isPlaying = true;\
        gameThread = new Thread(this);\
        gameThread.start();\
    \}\
\
    @Override\
    public boolean onTouchEvent(MotionEvent event) \{\
        if (event.getAction() == MotionEvent.ACTION_DOWN) \{\
            float touchX = event.getX();\
            float touchY = event.getY();\
\
            if (Math.sqrt(Math.pow(touchX - ballX, 2) + Math.pow(touchY - ballY, 2)) < ballRadius) \{\
                score++;\
                resetBall();\
            \}\
        \}\
        return true;\
    \}\
\
    private void resetBall() \{\
        ballX = (float) (Math.random() * (getWidth() - 2 * ballRadius) + ballRadius);\
        ballY = 0;\
    \}\
\}\
}