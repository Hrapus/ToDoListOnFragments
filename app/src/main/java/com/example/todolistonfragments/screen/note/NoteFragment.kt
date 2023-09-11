package com.example.todolistonfragments.screen.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistonfragments.R
import com.example.todolistonfragments.databinding.FragmentMainBinding
import com.example.todolistonfragments.databinding.FragmentNoteBinding
import com.example.todolistonfragments.models.AppNote
import com.example.todolistonfragments.screen.main.MainAdapter
import com.example.todolistonfragments.screen.main.MainFragmentViewModel
import com.example.todolistonfragments.utilities.APP_ACTIVITY


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote: AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable("note") as AppNote
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialisation()
    }

    private fun initialisation() {
        setHasOptionsMenu(true)
        mBinding.noteName.text = mCurrentNote.name
        mBinding.noteText.text = mCurrentNote.text
        mViewModel = ViewModelProvider(this)[NoteFragmentViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_delete -> {
                mViewModel.delete(mCurrentNote){
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}