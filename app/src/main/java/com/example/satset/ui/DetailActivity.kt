package com.example.satset.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.satset.R
import com.example.satset.data.AiTool
import com.example.satset.data.ToolRepository
import com.example.satset.databinding.ActivityDetailBinding
import com.google.android.material.chip.Chip

class DetailActivity : BaseActivity() {
    private lateinit var b: ActivityDetailBinding
    private lateinit var repo: ToolRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(b.root)

        repo = ToolRepository(this)
        val tool = repo.getById(intent.getIntExtra(EXTRA_ID, -1))
        if (tool == null) { finish(); return }

        bindUserBar(b.userBar)

        b.tvAvatar.text = tool.name.take(1).uppercase()
        b.tvAvatar.backgroundTintList = ColorStateList.valueOf(Color.parseColor(tool.color))
        b.tvName.text = tool.name
        b.tvCategory.text = tool.category
        b.tvAbout.text = tool.about
        b.tvPros.text = tool.pros.joinToString("\n") { "✓  $it" }
        b.tvLimit.text = tool.limit

        tool.bestFor.forEach { label ->
            val chip = Chip(this)
            chip.text = label
            chip.isCheckable = false
            chip.setEnsureMinTouchTargetSize(false)
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.surface))
            chip.chipStrokeColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.stroke))
            chip.chipStrokeWidth = 2f
            b.cgBestFor.addView(chip)
        }

        // Rating
        val username = session.getUsername()
        b.rbUser.rating = repo.getUserRating(tool.id, username).toFloat()
        updateRatingSummary(tool, username)
        b.rbUser.setOnRatingBarChangeListener { _, value, fromUser ->
            if (fromUser) {
                repo.setUserRating(tool.id, username, value.toInt())
                updateRatingSummary(tool, username)
                Toast.makeText(this, "Terima kasih! Rating kamu: ${value.toInt()}", Toast.LENGTH_SHORT).show()
            }
        }

        // Bookmark
        fun updateBookmark() = b.btnBookmark.setImageResource(
            if (repo.isBookmarked(tool.id)) R.drawable.ic_bookmark else R.drawable.ic_bookmark_border
        )
        updateBookmark()

        b.btnBack.setOnClickListener { finish() }
        b.btnBookmark.setOnClickListener {
            val saved = repo.toggleBookmark(tool.id)
            updateBookmark()
            Toast.makeText(this, if (saved) "Disimpan" else "Dihapus dari simpanan", Toast.LENGTH_SHORT).show()
        }
        b.btnTry.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(tool.url)))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Tidak ada browser terpasang", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateRatingSummary(tool: AiTool, username: String) {
        val r = repo.ratingOf(tool, username)
        b.tvRatingSummary.text = "★ %.1f  •  %d penilaian".format(r.average, r.count)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}