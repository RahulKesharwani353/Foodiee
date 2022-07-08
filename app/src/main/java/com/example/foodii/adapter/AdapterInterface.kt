package com.example.foodii.adapter

import androidx.recyclerview.widget.RecyclerView


interface AdapterInterface<T> {

    // set layout
    fun setLayout(layout: Int): Adapter<T>

    // filterable
//    fun filterable(): Adapter<T>

    // append data
    fun addData(items: List<T>): Adapter<T>

    // realtime change
    fun updateData(item: T): Adapter<T>

    // adapter callback
    fun adapterCallback(adapterCallback: AdapterCallback<T>): Adapter<T>

    // layout orientation
    fun isVerticalView(): Adapter<T>
    fun isHorizontalView(): Adapter<T>

    // layout orientation
    fun setGridLayout(count:Int ): Adapter<T>

    // build view
    fun build(recyclerView: RecyclerView): Adapter<T>

}