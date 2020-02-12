package com.example.countries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.networking.models.Language
import kotlinx.android.synthetic.main.item_language.view.language
import kotlinx.android.synthetic.main.item_language.view.languageNative

class LanguageAdapter(private var languageList: List<Language>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LanguageViewHolder) {
            holder.itemView.language.text = languageList[position - 1].name
            holder.itemView.languageNative.text = languageList[position - 1].nativeName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        LANGUAGE -> LanguageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_language, parent, false
            )

        )
        TITLE -> SubTitleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_sub_language,
                parent,
                false
            )
        )
        else -> throw IllegalAccessException()
    }

    override fun getItemCount() = languageList.size + 1

    fun updateList(newLanguage: List<Language>) {
        languageList = newLanguage
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> TITLE
            else -> LANGUAGE
        }
    }

    companion object {
        private const val LANGUAGE = 0
        private const val TITLE = 1
    }
}

class LanguageViewHolder(view: View) : RecyclerView.ViewHolder(view)
class SubTitleViewHolder(view: View) : RecyclerView.ViewHolder(view)