package com.madudka.countries.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.madudka.countries.databinding.ListItemCountryBinding
import com.madudka.countries.model.CountryModel
import com.madudka.countries.utils.glide.loadImage

class CountriesListAdapter : BaseAdapter<CountryModel>(){

    lateinit var clickListener: OnItemClickListener<CountryModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerifyListHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =  ListItemCountryBinding.inflate(layoutInflater, parent, false)
        return VerifyListHolder(binding)
    }

    inner class VerifyListHolder(private val binding: ListItemCountryBinding) : BaseViewHolder(binding.root){
        override fun bindView(position: Int) {
            val item = listData[position]

            binding.ivFlag.loadImage(binding.root.context, item.flags.png)

            binding.tvName.text = item.name

            binding.frameItemVerify.setOnClickListener {
                clickListener.onItemClick(item, position)
            }
        }
    }
}