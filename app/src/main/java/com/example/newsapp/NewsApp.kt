package com.example.newsapp

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

public class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}