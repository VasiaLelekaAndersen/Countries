package com.example.countries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.extencions.setUrlImage
import com.example.countries.networking.models.Country
import kotlinx.android.synthetic.main.item_country.view.capital
import kotlinx.android.synthetic.main.item_country.view.countryName
import kotlinx.android.synthetic.main.item_country.view.flag
import kotlinx.android.synthetic.main.item_country.view.language
import kotlinx.android.synthetic.main.item_country.view.region

class CountriesAdapter(
    private var countriesList: List<Country>,
    private val onItemClicked: (countryName: String) -> Unit
) : RecyclerView.Adapter<CountriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountriesViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_country, parent, false
        )
    )

    override fun getItemCount() = countriesList.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        with(countriesList[position]) {
            holder.view.countryName.text = name
            holder.view.capital.text = capital
            holder.view.region.text = region
            holder.view.language.text = languages.first().name
            holder.view.flag.setUrlImage(flag)
            holder.view.setOnClickListener { onItemClicked(name) }
        }
    }


    fun updateList(newList: List<Country>) {
        countriesList = newList
        notifyDataSetChanged()
    }
}

class CountriesViewHolder(val view: View) : RecyclerView.ViewHolder(view)