package com.example.rickandmortykotlin.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_kotlin.databinding.EpisodeItemBinding
import com.example.rickandmortykotlin.common.base.BaseComparator
import com.example.rickandmortykotlin.data.network.dto.episode.EpisodeDto

class EpisodeAdapter(
    private val onItemClick: (id: Int) -> Unit,
) : PagingDataAdapter<EpisodeDto, EpisodeAdapter.EpisodeViewHolder>(
    BaseComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            EpisodeItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    inner class EpisodeViewHolder(
        private val binding: EpisodeItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }


        fun onBind(item: EpisodeDto) = with(binding) {
            episodeName.text = item.name
            airDate.text = item.air_date
            episode.text = item.episode
        }
    }
}