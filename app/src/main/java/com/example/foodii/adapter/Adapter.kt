package com.example.foodii.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.properties.Delegates


class Adapter<T>(
    private var context: Context
) : RecyclerView.Adapter<Adapter<T>.ViewHolder>(),
    AdapterInterface<T>,
    Filterable {

    // utils
    var listData = mutableListOf<T>()
    var currentList = mutableListOf<T>()
    private var filterable: Boolean = false
    private var layout by Delegates.notNull<Int>()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapterCallback: AdapterCallback<T>
    private var isGridLayout: Boolean = false
    private lateinit var gridLayoutManager: GridLayoutManager

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return if (filterable) currentList.size
        else listData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(this.layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (filterable) {
            adapterCallback.initComponent(holder.itemView, currentList[position], position)
            holder.itemView.setOnClickListener {
                adapterCallback.onItemClicked(it, currentList[position], position)
            }
        } else {
            adapterCallback.initComponent(holder.itemView, listData[position], position)
            holder.itemView.setOnClickListener {
                adapterCallback.onItemClicked(it, listData[position], position)
            }
        }
    }

    override fun setLayout(layout: Int): Adapter<T> {
        this.layout = layout
        return this
    }

//    override fun filterable(): Adapter<T> {
//        this.filterable = true
//        return this
//    }

    override fun addData(items: List<T>): Adapter<T> {
        listData = items as MutableList<T>
        currentList = listData
        notifyDataSetChanged()
        return this
    }

    override fun updateData(item: T): Adapter<T> {
        if (!listData.contains(item)) {
            listData.add(item)
        } else {
            val index = listData.indexOf(item)
            listData[index] = item
        }

        notifyDataSetChanged()
        return this
    }

    override fun adapterCallback(adapterCallback: AdapterCallback<T>): Adapter<T> {
        this.adapterCallback = adapterCallback
        return this
    }

    override fun isVerticalView(): Adapter<T> {
        layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
        return this
    }

    override fun isHorizontalView(): Adapter<T> {
        layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        return this
    }

    override fun build(recyclerView: RecyclerView): Adapter<T> {
        recyclerView.apply {
            this.adapter = this@Adapter
            this.layoutManager = if (!isGridLayout) this@Adapter.layoutManager else this@Adapter.gridLayoutManager
        }
        return this
    }


    override fun setGridLayout(count: Int): Adapter<T> {
        isGridLayout = true;
        gridLayoutManager = GridLayoutManager(context,count)
        return this
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }


}