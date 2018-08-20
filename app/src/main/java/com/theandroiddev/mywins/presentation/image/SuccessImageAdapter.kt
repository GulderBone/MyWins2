package com.theandroiddev.mywins.presentation.image

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.theandroiddev.mywins.R
import com.theandroiddev.mywins.data.entity.SuccessImageEntity
import com.theandroiddev.mywins.presentation.image.SuccessImageAdapter.ViewHolder
import java.io.File

class SuccessImageAdapter(
        private val listener: OnSuccessImageClickListener,
        private val successImageLayout: Int,
        private val context: Context?) : RecyclerView.Adapter<ViewHolder>() {

    var successImages: MutableList<SuccessImageEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(successImageLayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (successImages[position].imagePath.isEmpty()) {
            if (position == 0) {
                holder.successImageIv.setImageResource(R.drawable.ic_action_add)
                //holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.primary));
                holder.bind(successImages[position], position)

            }
        } else {

            Picasso.with(context).load(File(successImages[position].imagePath)).resize(256, 256).centerCrop().into(holder.successImageIv)
            holder.bind(successImages[position], position)

        }

    }

    override fun getItemCount(): Int {
        return successImages.size
    }

    interface OnSuccessImageClickListener {
        fun onSuccessImageClick(successImage: SuccessImageEntity, successImageIv: ImageView, position: Int, constraintLayout: ConstraintLayout,
                                cardView: CardView)

        fun onSuccessImageLongClick(successImage: SuccessImageEntity, successImageIv: ImageView, position: Int, constraintLayout: ConstraintLayout,
                                    cardView: CardView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var successImageIv: ImageView
        var constraintLayout: ConstraintLayout
        var cardView: CardView


        init {

            successImageIv = itemView.findViewById(R.id.success_image_iv)
            constraintLayout = itemView.findViewById(R.id.success_image_constraint_layout)
            cardView = itemView.findViewById(R.id.success_image_card_view)

        }

        fun bind(successImage: SuccessImageEntity, position: Int) {

            itemView.setOnClickListener { listener.onSuccessImageClick(successImage, successImageIv, position, constraintLayout, cardView) }
            itemView.setOnLongClickListener {
                listener.onSuccessImageLongClick(successImage, successImageIv, position, constraintLayout, cardView)
                true
            }
        }
    }

    fun addSuccessImage(position: Int, successImage: SuccessImageEntity) {
        this.successImages.add(position, successImage)
    }

    fun updateSuccessImage(position: Int, successImage: SuccessImageEntity) {
        this.successImages[position] = successImage
    }

    fun addSuccessImage(successImage: SuccessImageEntity) {
        this.successImages.add(successImage)
    }
}