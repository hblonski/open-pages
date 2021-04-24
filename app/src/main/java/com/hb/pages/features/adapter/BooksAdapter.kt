package com.hb.pages.features.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hb.pages.R
import com.hb.pages.model.Book
import com.hb.pages.util.loadImageInto

class BooksAdapter(
    books: List<Book>
) : RecyclerView.Adapter<BookViewHolder>() {

    private val items: MutableList<Book> = books.toMutableList()

    fun updateItems(newItems: List<Book>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class BookViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.book_view_holder, parent, false)) {

    private var title: TextView = itemView.findViewById(R.id.viewHolderTitle)
    private var cover: ImageView = itemView.findViewById(R.id.viewHolderCoverImage)

    fun bind(book: Book) {
        title.text = book.title
        loadImageInto(
            url = book.imageURL,
            context = itemView.context,
            imageView = cover,
            placeholder = R.drawable.ic_placeholder_book
        )
    }
}