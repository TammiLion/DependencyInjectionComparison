package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw.utils

import android.graphics.Rect

class RowColumnCalculator(private val tileWidth: Int, private val tileHeight: Int, private val margin: Int = 0) {

    fun getYForRow(row: Int): Int = row * tileHeight + (margin * row)

    fun getXForColumn(column: Int): Int = column * tileWidth + (margin * column)

    fun getRect(column: Int, row: Int): Rect {
        val x = getXForColumn(column)
        val y = getYForRow(row)
        return Rect(x, y, x + tileWidth, y + tileHeight)
    }
}