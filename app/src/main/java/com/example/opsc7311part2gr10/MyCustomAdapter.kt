package com.example.opsc7311part2gr10

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyCustomAdapter(val contxt: Context, val myDataList: List<MyData>): BaseAdapter() {
    override fun getCount(): Int {
        return  myDataList.size
    }

    override fun getItem(position: Int): MyData {
        return myDataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView = convertView
        if(myConvertView == null){
            myConvertView = LayoutInflater.from(contxt).inflate(R.layout.my_list_item, parent, false)
        }
        val currentItem = getItem(position)

        val imageItem = myConvertView?.findViewById<ImageView>(R.id.ivlistItemImage)
        val categoryItem = myConvertView?.findViewById<TextView>(R.id.tvListItemCategory)
        val dateItem = myConvertView?.findViewById<TextView>(R.id.tvListItemDate)
        val startItem = myConvertView?.findViewById<TextView>(R.id.tvListStartTime)
        val endItem = myConvertView?.findViewById<TextView>(R.id.tvListEndTime)
        val descriptionItem = myConvertView?.findViewById<TextView>(R.id.tvListDescription)

        imageItem?.setImageResource(currentItem.image)
        categoryItem?.text = currentItem.aCategory
        dateItem?.text = currentItem.theDate
        startItem?.text = currentItem.theStartTime
        endItem?.text = currentItem.theEndTime
        descriptionItem?.text = currentItem.theDescription

        return  myConvertView!!












    }
}