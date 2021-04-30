package com.meetup.ap.view.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.meetup.ap.R
import com.meetup.ap.databinding.ComponentItemBinding

@EpoxyModelClass(layout = R.layout.component_item)
abstract class TextModel : EpoxyModelWithHolder<TextModel.TextViewHolder>() {

    @field:EpoxyAttribute
    var text: String? = null

    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var clickListener: View.OnClickListener? = null

    override fun bind(holder: TextViewHolder) {
        super.bind(holder)
        holder.binding.textView.text = text
        holder.binding.textView.setOnClickListener(clickListener)
    }

    class TextViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentItemBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentItemBinding.bind(itemView)
        }
    }
}