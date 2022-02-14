package com.algebra.baza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.algebra.baza.dao.StudentDAO
import com.algebra.baza.dao.StudentDAOList
import java.lang.Exception

class MainActivity : AppCompatActivity( ) {


    private val dao : StudentDAO = StudentDAOList( )

    private lateinit var etName     : EditText
    private lateinit var sYear      : Spinner
    private lateinit var rgGender   : RadioGroup
    private lateinit var rbM        : RadioButton
    private lateinit var rbF        : RadioButton
    private lateinit var bSave      : Button
    private lateinit var rvStudents : RecyclerView

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        initWidgets( )
        setupListeners( )
    }

    fun initWidgets( ) {
        etName     = findViewById( R.id.etName )
        sYear      = findViewById( R.id.sYear )
        rgGender   = findViewById( R.id.rgGender )
        rbM        = findViewById( R.id.rbM )
        rbF        = findViewById( R.id.rbF )
        bSave      = findViewById( R.id.bSave )
        rvStudents = findViewById( R.id.rvStudents )
        rvStudents.layoutManager = LinearLayoutManager( this )
        rvStudents.adapter = StudentsAdapter( dao.getAll( ), this )
    }

    fun setupListeners( ) {
        bSave.setOnClickListener {
            try {
                dao.insert(
                    etName.text.toString().trim(),
                    sYear.selectedItem.toString(),
                    if (rbM.isChecked) "M" else if (rbF.isChecked) "F" else ""
                )
                clearFields( )
                refreshStudentsList( )
            } catch ( e : Exception ) {
                Toast
                    .makeText( this, "You have to provide all the data", Toast.LENGTH_SHORT )
                    .show( )
            }
        }
    }

    private fun clearFields( ) {
        etName.setText( "" )
        sYear.setSelection( 0 )
        rgGender.clearCheck( )
    }

    private fun refreshStudentsList( ) {
        rvStudents.adapter?.notifyDataSetChanged( )
    }

    fun deleteStudent( id : Int ) {
        dao.delete( id )
        refreshStudentsList( )
    }


}