package com.algebra.baza.dao

import android.content.Context
import com.algebra.baza.data.Baza
import com.algebra.baza.model.Student
import java.lang.IllegalStateException

class StudentDaoBaza( context : Context ) : StudentDAO {

    val baza = Baza.getInstance( context )
//  val baza = Baza( context )

    override fun get( id: Int ): Student {
        TODO("Not yet implemented")
    }

    override fun getAll( ): MutableList< Student > {
        return baza.getAll( )
    }

    override fun insert( name : String, year : String, gender : String ): Student {
        if( name=="" ) throw IllegalStateException( "Name should not be empty." )
        if( year=="" ) throw IllegalStateException( "Year should not be empty." )
        if( gender=="" ) throw IllegalStateException( "Gender should not be empty." )
        return baza.insert( name, year.toInt( ), gender[0] )
    }

    override fun update( student: Student ) {
        TODO("Not yet implemented")
    }

    override fun delete( id : Int ) {

        baza.delete( id )
    }
}