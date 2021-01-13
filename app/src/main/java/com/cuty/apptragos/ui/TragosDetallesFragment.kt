package com.cuty.apptragos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.cuty.apptragos.AppDatabase
import com.cuty.apptragos.R
import com.cuty.apptragos.data.data.DataSource
import com.cuty.apptragos.data.data.Drink
import com.cuty.apptragos.data.data.DrinkEntity
import com.cuty.apptragos.domain.RepoImpl
import com.cuty.apptragos.ui.viewmodel.MainViewModel
import com.cuty.apptragos.ui.viewmodel.VMFactory
import kotlinx.android.synthetic.main.fragment_tragos_detalles.*
import kotlinx.android.synthetic.main.fragment_tragos_detalles.view.*


class TragosDetallesFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>{VMFactory(
        RepoImpl(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext)))
    )}


    private lateinit var drink : Drink
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable<Drink>("drink")!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tragos_detalles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.imagen).into(img_trago)
        trago_title.text = drink.nombre
        trago_desc.text = drink.descripcion
        if (drink.hasAlcohol == "Non_Alcoholic"){
            txt_alcohol.text = "Bebida sin Alcohol"
        }else{
            txt_alcohol.text = "Bebida con Alcohol"
        }

        bu_save_trago.setOnClickListener {
            viewModel.guardarTrago(DrinkEntity(drink.tragoId, drink.imagen,drink.nombre,drink.descripcion,drink.hasAlcohol))
            Toast.makeText(requireContext(),"Se gurado el trago a favoritos",Toast.LENGTH_SHORT).show()
        }
    }
}