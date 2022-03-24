package com.algebra.baza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.algebra.baza.data.Baza

class BazaActivity : AppCompatActivity( ) {

    private val TAG = "BazaActivity"
    private lateinit var baza : Baza

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_baza )

        Log.i( TAG, "Kreiram bazu podataka" )
        baza = Baza.getInstance( this )
        Log.i( TAG, "Baza je kreirana" )
    }
}