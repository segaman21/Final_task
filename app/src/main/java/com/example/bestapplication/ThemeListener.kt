package com.example.bestapplication

import androidx.appcompat.app.AppCompatDelegate

interface ThemeListener {
    fun switch(switch: Boolean)
}

class SwitchTheme:ThemeListener{
    override fun switch(switch: Boolean) {
        if (switch){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}