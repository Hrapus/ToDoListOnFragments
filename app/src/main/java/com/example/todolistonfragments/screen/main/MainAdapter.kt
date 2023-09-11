package com.example.todolistonfragments.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.todolistonfragments.R
import com.example.todolistonfragments.databinding.NoteItemBinding
import com.example.todolistonfragments.models.AppNote


class MainAdapter: RecyclerView.Adapter<MainAdapter.MainHolder>(){

    private var mListNotes = emptyList<AppNote>()
     class MainHolder(view: View): RecyclerView.ViewHolder(view) {
        val mBinding = NoteItemBinding.bind(view)
         val nameNote = mBinding.itemNoteName
         val textNote = mBinding.itemNoteText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int {
        return mListNotes.size
    }

    override fun onViewAttachedToWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener{
            MainFragment.click(mListNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.nameNote.text = mListNotes[position].name
        holder.textNote.text = mListNotes[position].text
    }

    fun setList(list: List<AppNote>){
        mListNotes = list
        notifyDataSetChanged()
    }
}