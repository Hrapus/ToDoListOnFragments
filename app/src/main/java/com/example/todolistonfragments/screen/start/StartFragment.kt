package com.example.todolistonfragments.screen.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolistonfragments.R
import com.example.todolistonfragments.databinding.FragmentStartBinding
import com.example.todolistonfragments.utilities.APP_ACTIVITY
import com.example.todolistonfragments.utilities.TYPE_ROOM


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this)[StartFragmentViewModel::class.java]
        binding.btnRoom.setOnClickListener{
            mViewModel.initDatabase(TYPE_ROOM){
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
    }
}