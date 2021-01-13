package com.cuty.apptragos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuty.apptragos.AppDatabase
import com.cuty.apptragos.R
import com.cuty.apptragos.data.data.DataSource
import com.cuty.apptragos.data.data.Drink
import com.cuty.apptragos.data.data.DrinkList
import com.cuty.apptragos.domain.RepoImpl
import com.cuty.apptragos.ui.viewmodel.MainViewModel
import com.cuty.apptragos.ui.viewmodel.VMFactory
import com.cuty.apptragos.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*


class  MainFragment : Fragment(), MainAdapter.OnTragoClickListener {

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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setupObservers()
        btn_ir_favoritos.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritosFragment)
        }

    }

    private fun setupRecyclerView() {
        rv_tragos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
    private fun setupObservers(){
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE

                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rv_tragos.adapter = MainAdapter(requireContext(),
                        result.data as List<Drink>, this)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Error de datos : ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
    private fun setupSearchView(){
        search_view.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setTrago(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onTragoClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.tragosDetallesFragment, bundle)
    }
}