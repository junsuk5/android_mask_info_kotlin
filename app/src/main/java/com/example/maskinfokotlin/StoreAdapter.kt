package com.example.maskinfokotlin

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.maskinfokotlin.databinding.ItemStoreBinding
import com.example.maskinfokotlin.model.Store
import java.util.*

// 아이템 뷰 정보를 가지고 있는 클래스
class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding = ItemStoreBinding.bind(itemView)
}

class StoreAdapter : RecyclerView.Adapter<StoreViewHolder>() {
    private var mItems: List<Store> = ArrayList()

    fun updateItems(items: List<Store>) {
        mItems = items
        notifyDataSetChanged() // UI 갱신
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.binding.store = mItems[position]
    }

    override fun getItemCount() = mItems.size
}


@BindingAdapter("remainStat")
fun setRemainStat(view: TextView, remainStat: String) {
    view.text = when (remainStat) {
        "plenty" -> "충분"
        "some" -> "여유"
        "few" -> "매진 임박"
        else -> "재고 없음"
    }
}

@BindingAdapter("count")
fun setCount(view: TextView, remainStat: String) {
    view.text = when (remainStat) {
        "plenty" -> "100개 이상"
        "some" -> "30개 이상"
        "few" -> "2개 이상"
        else -> "1개 이하"
    }
}

@BindingAdapter("color")
fun setColor(view: TextView, remainStat: String) {
    when (remainStat) {
        "plenty" -> Color.GREEN
        "some" -> Color.YELLOW
        "few" -> Color.RED
        else -> Color.GRAY
    }.apply {
        view.setTextColor(this)
    }
}