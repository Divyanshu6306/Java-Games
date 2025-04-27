{\rtf1\ansi\ansicpg1252\cocoartf2822
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 package com.example.tappyball;\
\
import android.os.Bundle;\
import androidx.appcompat.app.AppCompatActivity;\
\
public class MainActivity extends AppCompatActivity \{\
\
    private GameView gameView;\
\
    @Override\
    protected void onCreate(Bundle savedInstanceState) \{\
        super.onCreate(savedInstanceState);\
        \
        gameView = new GameView(this);\
        setContentView(gameView);\
    \}\
\
    @Override\
    protected void onPause() \{\
        super.onPause();\
        gameView.pause();\
    \}\
\
    @Override\
    protected void onResume() \{\
        super.onResume();\
        gameView.resume();\
    \}\
\}\
}