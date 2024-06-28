package de.syntax_institut.funappsvorlage.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.RoundedCornersTransformation
import de.syntax_institut.funappsvorlage.R
import de.syntax_institut.funappsvorlage.data.datamodels.Meme
import de.syntax_institut.funappsvorlage.databinding.ListItemMemeBinding

/**
 * Der Adapter kümmert sich um das Erstellen der Listeneinträge
 */
class MemeAdapter : RecyclerView.Adapter<MemeAdapter.ItemViewHolder>() {

    // Die angezeigte Liste ist zu Beginn leer
    private var dataset: List<Meme> = emptyList()

    // Die Liste wird durch diese Funktion aktualisiert
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Meme>) {
        dataset = list
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(val binding: ListItemMemeBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ListItemMemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val meme = dataset[position]

        // Hier wird das Meme anhand der URL aus der API in die ImageView geladen
        holder.binding.ivMeme.load(meme.url) {
            error(R.drawable.ic_broken_image)
            transformations(RoundedCornersTransformation(10f))
        }

        // Hier werden die Infos in der UI angezeigt
        holder.binding.tvTitle.setText(meme.name)
        holder.binding.btnSave.setOnClickListener {
            meme.name = holder.binding.tvTitle.text.toString()
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
