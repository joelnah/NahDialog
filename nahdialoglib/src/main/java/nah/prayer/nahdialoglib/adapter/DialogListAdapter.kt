package nah.prayer.nahdialoglib.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nah.prayer.nahdialoglib.R

import java.util.ArrayList


/**
 * Created by S on 2019-04-02.
 */
class DialogListAdapter(private val context: Context, private val mItems: ArrayList<String>?) :
    RecyclerView.Adapter<DialogListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_radio_button, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = mItems!![position]
    }

    override fun getItemCount(): Int {
        return mItems?.size ?: 0
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv: TextView = view.findViewById(R.id.tv)

    }


}
