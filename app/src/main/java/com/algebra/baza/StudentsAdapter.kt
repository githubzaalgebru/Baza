package com.algebra.baza


import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.algebra.baza.model.Student

class StudentsAdapter( val items : MutableList< Student >, val mainActivity : MainActivity ) : RecyclerView.Adapter< StudentsViewHolder >( ) {

    val TAG = "StudentsAdapter"

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ) : StudentsViewHolder {
        return StudentsViewHolder(
            LayoutInflater
                .from( mainActivity )
                .inflate( R.layout.student, parent, false )
        )
    }

    override fun onBindViewHolder( holder : StudentsViewHolder, position : Int ) {
        val s = items[ position ]
        holder.tvName.text   = s.name
        holder.tvYear.text   = s.year.toString( )
        holder.tvGender.text = s.gender.toString( )
        holder
            .itemView
            .setBackgroundColor(
                Color.parseColor( if( position%2==0 ) "#FFCCCC" else "#CCCCFF" )
            )

        holder.itemView.setOnClickListener {
            mainActivity.deleteStudent( s.id )
        }
    }

    override fun getItemCount( ) = items.size

    fun refresh( noviStudenti : List< Student > ) {
        items.clear( )
        items.addAll( noviStudenti )
        notifyDataSetChanged( )
    }
}

class StudentsViewHolder( view : View ) : RecyclerView.ViewHolder( view ) {
    val tvName   : TextView = view.findViewById( R.id.tvName )
    val tvYear   : TextView = view.findViewById( R.id.tvYear )
    val tvGender : TextView = view.findViewById( R.id.tvGender )
}