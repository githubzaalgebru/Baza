package com.algebra.baza.dao

import android.util.Log
import com.algebra.baza.model.Student
import java.lang.IllegalStateException

class StudentDAOList : StudentDAO {

    val TAG : String = "StudentDAOList"

    var maxId = 1
    private val students = mutableListOf< Student >( )

    override fun get( id: Int ) : Student {
        students.forEach {
            if( it.id==id )
                return it
        }
        throw IllegalStateException( "No student with id=$id found." )
    }

    override fun getAll( ): MutableList< Student > {
       return students
    }

    override fun insert( name : String, year : String, gender : String ) : Student {
        Log.i( TAG, "insert for student: {$name, $year, $gender}" )
        if( name=="" || gender=="" || year=="" )
            throw IllegalStateException( "You have to provide all the data" )
        val student = Student( maxId, name, year.toInt( ), gender.get( 0 ) )
        maxId = maxId+1
        students.add( student )
        return student
    }

    override fun delete( id : Int ) {
        val s = get( id )
        students.remove( s )
    }

    override fun update( student : Student ) {
        val s = get( student.id )
        s.name   = student.name
        s.year   = student.year
        s.gender = student.gender
    }
}