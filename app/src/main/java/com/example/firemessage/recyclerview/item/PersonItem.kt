package com.example.firemessage.recyclerview.item

import android.content.Context
import com.example.firemessage.R
import com.example.firemessage.glide.GlideApp
import com.example.firemessage.model.User
import com.example.firemessage.util.StorageUtil
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_person.*

class PersonItem(val person: User,
                 val userId: String,
                 private val context: Context ): Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView_name.text = person.name
        viewHolder.textView_bio.text = person.bio
        if (person.profilePicturePath != null){
            GlideApp.with(context)
                .load(StorageUtil.pathToReference(person.profilePicturePath))
                .placeholder(R.drawable.ic_account_circle_black_24)
                .into(viewHolder.imageView_profile_picture)
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_person
    }

}