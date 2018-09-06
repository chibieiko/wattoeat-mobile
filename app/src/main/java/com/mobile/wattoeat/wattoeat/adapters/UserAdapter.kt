package com.mobile.wattoeat.wattoeat.adapters

import android.support.v7.widget.RecyclerView
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.wattoeat.wattoeat.R
import com.mobile.wattoeat.wattoeat.models.UserModel
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserAdapter(
        val items: List<UserModel>,
        val context: Context
) : RecyclerView.Adapter<ViewHolder>() {

    // Returns the number of users
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates user_list_item that holds each user
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_list_item, p0,
                false))
    }

    // Binds users to buttons
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.userOption.text = items.get(p1).name
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val userOption = view.user_option
}