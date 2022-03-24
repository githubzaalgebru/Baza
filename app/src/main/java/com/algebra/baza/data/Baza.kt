package com.algebra.baza.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.algebra.baza.model.Student

val DB_NAME = "Studenti.db"
val DB_VERSION = 1
val TABLE_NAME = "STUDENT"
val COL_ID     = "_ID"
val COl_NAME   = "name"
val COL_YEAR   = "year"
val COL_GENDER = "gender"
val SQL_QUERY_STUDENTS = "SELECT _ID,name,year,gender FROM STUDENT"

class Baza private constructor( context: Context ) : SQLiteOpenHelper( context, DB_NAME, null, DB_VERSION ) {

    private val TAG = "Baza"

    override fun onCreate( db : SQLiteDatabase ) {
        val query = ( "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY, " +
                COl_NAME + " TEXT," +
                COL_YEAR + " INTEGER, " +
                COL_GENDER + " TEXT" +
        ")" )
        Log.i( TAG, "Kreiran query" )
        db.execSQL( query )
        Log.i( TAG, "Query izvršen" )
    }

    override fun onUpgrade( db : SQLiteDatabase, oldVersion : Int, newVersion : Int ) {
        if( oldVersion==1 && newVersion==2 ) {
            db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
            onCreate( db )
        }
    }

    override fun onDowngrade( db: SQLiteDatabase?, oldVersion: Int, newVersion: Int ) {
        super.onDowngrade( db, oldVersion, newVersion )
    }

    fun insert( name : String, year : Int, gender : Char ) : Student {
        val db = writableDatabase
        db.beginTransaction( )
        val values = ContentValues( )
        values.put( COl_NAME, name )
        values.put( COL_YEAR, year )
        values.put( COL_GENDER, gender.toString( ) )
        val id = db.insertOrThrow( TABLE_NAME, null, values ).toInt( )
        db.setTransactionSuccessful( )
        db.endTransaction( )

        val student = Student( id, name, year, gender )

        Log.i( TAG, "Student $student inserted with ID=$id" )

        return student
    }

    fun getAll( ) : MutableList< Student > {
        val lista = mutableListOf< Student >( )
        val db = readableDatabase
        var cursor : Cursor? = null
        try {
            cursor = db.rawQuery( SQL_QUERY_STUDENTS, null );
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(0)
                    val name = cursor.getString(1)
                    val year = cursor.getInt(2)
                    val gender = cursor.getString(3)[0]
                    lista.add(Student(id, name, year, gender))
                } while ( cursor.moveToNext( ) )
            }
        } finally {
            if( cursor!=null && !cursor.isClosed ) {
                cursor.close( )
            }
        }
        return lista
    }

    fun delete( id : Int ) {
        Log.i( TAG, "Deleting student with ID=$id" )
        val db = writableDatabase
        db.beginTransaction( )
        db.delete( TABLE_NAME, "$COL_ID=$id", null )
        db.setTransactionSuccessful( )
        db.endTransaction( )
    }

    companion object : SingletonHolder < Baza, Context >( ::Baza )
}