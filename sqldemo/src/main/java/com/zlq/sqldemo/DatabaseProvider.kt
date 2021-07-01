package com.zlq.sqldemo

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.zlq.sqldemo.utils.MyDatabaseHelper

class DatabaseProvider : ContentProvider() {
    private val booksDir = 0
    private val booksItem = 1
    private val categotyDir = 2
    private val categoryItem = 3
    private val authority = "com.zlq.sqldemo.app.provider"
    private var dbHelper: MyDatabaseHelper? = null

    private val uriMarker by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority, "book", booksDir)
        matcher.addURI(authority, "book/#", booksItem)
        matcher.addURI(authority, "category", categotyDir)
        matcher.addURI(authority, "category/#", categoryItem)
        matcher
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = dbHelper?.let {
        val db = it.writableDatabase
        val delRows = when(uriMarker.match(uri)){
            booksDir ->db.delete("Books",selection,selectionArgs)
            booksItem ->{
                val booksId = uri.pathSegments[1]
                db.delete("Books","id = ?", arrayOf(booksId))
            }
            categoryItem->{
                val categoryId = uri.pathSegments[1]
                db.delete("Category","id = ?", arrayOf(categoryId))

            }
            categotyDir->db.delete("Category",selection,selectionArgs)
            else ->0
        }
        return delRows
    }?:0

    override fun getType(uri: Uri): String? =when(uriMarker.match(uri)){
        booksItem->"vnd.android.cursor.dir/vnd/com.zlq.sqldemo.app.provider.book"
        booksDir ->"vnd.android.cursor.item/vnd/com.zlq.sqldemo.app.provider.book"
        categotyDir ->"vnd.android.cursor.dir/vnd/com.zlq.sqldemo.app.provider.category"
        categoryItem ->"vnd.android.cursor.item/vnd/com.zlq.sqldemo.app.provider.category"
        else -> null

    }
    override fun insert(uri: Uri, values: ContentValues?): Uri? = dbHelper?.let {
        val db = it.writableDatabase
        val uriReturn = when (uriMarker.match(uri)) {
            booksItem, booksDir -> {
                val booksId = db.insert("Books", null, values)
                Uri.parse("content://$authority/book/$booksId")
            }
            categoryItem, categotyDir -> {

                val categoryId = db.insert("categoty", null, values)
                Uri.parse("content://$authority/category/$categoryId")
            }
            else -> null
        }
        uriReturn
    }

    override fun onCreate(): Boolean = context?.let {
        dbHelper = MyDatabaseHelper(it, "BooksStore.db", 4)
        true
    } ?: false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = dbHelper?.let {
        val db = it.readableDatabase
        val corsor = when (uriMarker.match(uri)) {
            booksDir -> db.query(
                "Books",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )
            booksItem -> {
                val booksId = uri.pathSegments[1]
                db.query("Books", projection, "id = ?", arrayOf(booksId), null, null, sortOrder)
            }
            categotyDir -> db.query(
                "Category",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )
            categoryItem -> {
                val newCategoryId = uri.pathSegments[1]
                db.query(
                    "Category",
                    projection,
                    "id = ?",
                    arrayOf(newCategoryId),
                    null,
                    null,
                    sortOrder
                )
            }
            else -> null
        }
        corsor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int = dbHelper?.let {
        val db = it.writableDatabase
        val updateRows = when (uriMarker.match(uri)) {
            booksDir -> db.update("Books", values, selection, selectionArgs)
            booksItem -> {
                val booksId = uri.pathSegments[1]
                db.update("Books", values, "id = ?", arrayOf(booksId))
            }
            categotyDir -> db.update("Category", values, selection, selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.update("Category", values, "id = ?", arrayOf(categoryId))

            }
            else ->0
        }
        updateRows
    }?:0
}