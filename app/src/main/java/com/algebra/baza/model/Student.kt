package com.algebra.baza.model

class Student( var id : Int, var name : String, var year : Int, var gender : Char ) {

    override fun toString( ): String {
        return "$name - $year ($gender)"
    }

    fun copy( ) : Student {
        return Student( id, name, year, gender )
    }
}