package com.aman.userblinkit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aman.userblinkit.FilteringProducts
import com.aman.userblinkit.databinding.ItemViewProductBinding
import com.aman.userblinkit.models.Product
import com.denzcoskun.imageslider.models.SlideModel


class AdapterProduct(
    val onAddButtonClicked: (product: Product, productBinding: ItemViewProductBinding) -> Unit,
    val onIncrementButtonClicked: (product: Product, productBinding: ItemViewProductBinding) -> Unit,
    val onDecrementButtonClicked: (product: Product, productBinding: ItemViewProductBinding) -> Unit
) :
    RecyclerView.Adapter<AdapterProduct.ProductViewHolder>(), Filterable {

    class ProductViewHolder(val binding: ItemViewProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    val diffUtil = object : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productRandomId == newItem.productRandomId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemViewProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = differ.currentList[position]

        holder.binding.apply {

            val imageList = kotlin.collections.ArrayList<SlideModel>()
            val productImages = product.productImageUris

            if (!productImages.isNullOrEmpty()) {
                for (imageUri in productImages) {
                    imageList.add(SlideModel(imageUri))
                }
            }

            ivImageSlider.setImageList(imageList)

            tvProductName.text = product.productName
            tvProductQuantity.text = product.productQuantity.toString() + product.productUnit

            tvProductPrice.text = "₹" + product.productPrice


            if (product.itemCount!! > 0) {
                tvProductCount.text = product.itemCount.toString()
                tvAdd.visibility = View.GONE
                llProductCount.visibility = View.VISIBLE

            }

            tvAdd.setOnClickListener {
                onAddButtonClicked(product, this)
            }

            tvIncrementCount.setOnClickListener {
                onIncrementButtonClicked(product, this)

            }

            tvDecrementCount.setOnClickListener {
                onDecrementButtonClicked(product, this)
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private val filter: FilteringProducts? = null
    var originalList = ArrayList<Product>()

    override fun getFilter(): Filter? {
        if (filter == null) return FilteringProducts(this, originalList)
        return filter
    }
}
