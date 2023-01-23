package id.web.dedekurniawan.steamgameexplorer.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import id.web.dedekurniawan.steamgameexplorer.core.databinding.RvGameBinding
import id.web.dedekurniawan.steamgameexplorer.core.domain.model.Game

class GameAdapter: ListAdapter<Game, GameAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    var onClickListener: ((Game) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }

    class ViewHolder (private val binding: RvGameBinding): androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game, onClickListener: ((Game) -> Unit)?){
            binding.apply {
                rvName.text = game.name
                Glide.with(itemView)
                    .load(game.imageUrl)
                    .into(rvLogo)
            }
            itemView.setOnClickListener{
                onClickListener?.invoke(game)
            }

        }

        companion object{
            fun from(viewGroup: ViewGroup): ViewHolder{
                val binding = RvGameBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object{
        object DIFF_CALLBACK: DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game) = oldItem.appId == newItem.appId

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }
        }
    }
}