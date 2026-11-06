package com.example.satset.data

import android.content.Context

data class RatingInfo(val average: Double, val count: Int)

class ToolRepository(context: Context) {
    private val prefs = context.getSharedPreferences("bookmarks", Context.MODE_PRIVATE)
    private val ratingPrefs = context.getSharedPreferences("ratings", Context.MODE_PRIVATE)

    fun getById(id: Int): AiTool? = ToolData.all.find { it.id == id }

    fun filter(query: String, tag: String): List<AiTool> = ToolData.all.filter { t ->
        val matchTag = when (tag) {
            "Semua" -> true
            "Tersimpan" -> isBookmarked(t.id)
            else -> tag in t.tags
        }
        val matchQuery = query.isBlank() ||
                t.name.contains(query, ignoreCase = true) ||
                t.shortDesc.contains(query, ignoreCase = true)
        matchTag && matchQuery
    }

    private fun bookmarkIds(): Set<String> =
        prefs.getStringSet("ids", emptySet()) ?: emptySet()

    fun isBookmarked(id: Int) = bookmarkIds().contains(id.toString())

    fun toggleBookmark(id: Int): Boolean {
        val ids = bookmarkIds().toMutableSet()
        val saved = if (ids.remove(id.toString())) false else { ids.add(id.toString()); true }
        prefs.edit().putStringSet("ids", ids).apply()
        return saved
    }

    // ---- Rating ----
    fun getUserRating(toolId: Int, username: String): Int =
        ratingPrefs.getInt("${username}_$toolId", 0)

    fun setUserRating(toolId: Int, username: String, value: Int) {
        ratingPrefs.edit().putInt("${username}_$toolId", value).apply()
    }

    fun ratingOf(tool: AiTool, username: String): RatingInfo {
        val mine = getUserRating(tool.id, username)
        return if (mine == 0) {
            RatingInfo(tool.baseRating, tool.ratingCount)
        } else {
            val total = tool.baseRating * tool.ratingCount + mine
            RatingInfo(total / (tool.ratingCount + 1), tool.ratingCount + 1)
        }
    }
}