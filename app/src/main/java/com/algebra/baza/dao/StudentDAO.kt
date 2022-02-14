package com.algebra.baza.dao

import com.algebra.baza.model.Student

interface StudentDAO {

    fun get( id : Int ) : Student
    fun getAll( ) : MutableList< Student >
    fun insert( name : String, year : String, gender : String ) : Student
    fun update( student : Student )
    fun delete( id : Int )

}