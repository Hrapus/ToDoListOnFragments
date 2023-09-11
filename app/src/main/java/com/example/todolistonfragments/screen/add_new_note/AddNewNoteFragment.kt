package com.example.todolistonfragments.screen.add_new_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolistonfragments.R
import com.example.todolistonfragments.databinding.FragmentAddNewNoteBinding
import com.example.todolistonfragments.models.AppNote
import com.example.todolistonfragments.utilities.APP_ACTIVITY
import com.example.todolistonfragments.utilities.showToast


class AddNewNoteFragment : Fragment() {

    private var _binding:FragmentAddNewNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this)[AddNewNoteViewModel::class.java]
        mBinding.btnAddNote.setOnClickListener{
            val name = mBinding.inputNameNote.text.toString()
            val text = mBinding.inputTextNote.text.toString()
            if (name.isEmpty() || text.isEmpty()){
                showToast("Empty field")
            } else{
                mViewModel.insert(AppNote(name = name, text = text)){
                    APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}