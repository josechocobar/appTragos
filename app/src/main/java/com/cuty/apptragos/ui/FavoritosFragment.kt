package com.cuty.apptragos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.cuty.apptragos.AppDatabase
import com.cuty.apptragos.R
import com.cuty.apptragos.data.data.DataSource
import com.cuty.apptragos.domain.RepoImpl
import com.cuty.apptragos.ui.viewmodel.MainViewModel
import com.cuty.apptragos.ui.viewmodel.VMFactory
import com.cuty.apptragos.vo.Resource

class FavoritosFragment : Fragment() {

    private val viewModel by  activityViewModels<MainViewModel> { VMFactory(RepoImpl(DataSource(
        AppDatabase.getDatabase(requireActivity().applicationContext)))) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTragosFavoritos().observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Resource.Loading->{

                }
                is Resource.Success ->{
                    Log.d("LISTA FAVORITOS", "${result.data}")
                }
                is Resource.Failure -> {

                }
            }
        })
    }

}