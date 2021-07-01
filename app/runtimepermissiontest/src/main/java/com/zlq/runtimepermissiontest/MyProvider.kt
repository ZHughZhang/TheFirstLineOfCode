package com.zlq.runtimepermissiontest

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

/**
 *   @Auther:  asad
 *   @Date:  2021/06/29 17:41
 *   @Description:  TODO
 *   @ClassName:  com.zlq.runtimepermissiontest
 *   @PackageName:  MyProvider
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
class MyProvider : ContentProvider() {

    private val table1Dir = 0
    private val table1Item = 1
    private val table2Dir = 2
    private val table2Item = 3
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI("com.zlq.runtimepermissiontest.app.provider", "table1", table1Dir)
        uriMatcher.addURI("com.zlq.runtimepermissiontest.app.provider", "table1/#", table1Item)
        uriMatcher.addURI("com.zlq.runtimepermissiontest.app.provider", "table2", table2Dir)
        uriMatcher.addURI("com.zlq.runtimepermissiontest.app.provider", "table2/#", table2Item)
    }


    //  初始化时调用，初始化成功 返回true 反之false
    override fun onCreate(): Boolean {
        return false
    }

    //  uri参数 确认查询哪一张表，projection 查询那些列 selection和selectionArgs 约束查询哪些行
//    sortOrder 对结果进行排序，查询结果存放在Cursor 对象中
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

        when (uriMatcher.match(uri)) {
            table1Dir -> {

            }
            table1Item -> {

            }
            table2Dir -> {

            }
            table2Item -> {

            }

        }


        return null
    }

    /**
     * 根据传入的uri 返回相应的 MIME 类型
     * */
    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        table1Dir ->"vnd.android.cursor.dir/vnd.com.zlq.runtimepermissiontest.app.provider.table1"
        table1Item ->"vnd.android.cursor.item/vnd.com.zlq.runtimepermissiontest.app.provider.table1"
        table2Dir ->"vnd.android.cursor.dir/vnd.com.zlq.runtimepermissiontest.app.provider.table2"
        table2Item ->"vnd.android.cursor.item/vnd.com.zlq.runtimepermissiontest.app.provider.table2"
        else -> null
    }

    //  插入数据 uri 确定插入那一张表 values 待添加的数据 返回一个 uri 地址
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    /**
     * 删除数据
     * uri 确定删除数据的一张表
     * values 新保存的数据
     * selection和selectionArgs 约束删除哪些行
     *
     * 返回删除的行数
     * */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    /**
     * 更新数据
     * uri 确定插入那一张表
     * values 新保存的数据
     * selection 约束条件
     * selectionArgs 约束参数
     * 返回受影响的行数
     * */
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}