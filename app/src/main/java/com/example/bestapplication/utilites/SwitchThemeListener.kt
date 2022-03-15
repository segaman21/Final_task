package com.example.bestapplication.utilites

import androidx.appcompat.app.AppCompatDelegate

class SwitchThemeListener: ThemeListener {
    override fun switch(switch: Boolean) {
        if (switch){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}