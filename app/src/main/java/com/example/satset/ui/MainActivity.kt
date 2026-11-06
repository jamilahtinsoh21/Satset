package com.example.satset.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.example.satset.R
import com.example.satset.data.AiTool
import com.example.satset.data.ToolRepository
import com.example.satset.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip

class MainActivity : BaseActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var repo: ToolRepository
    private lateinit var adapter: ToolAdapter

    private var query = ""
    private var selectedTag = "Semua"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        repo = ToolRepository(this)

        bindUserBar(b.userBar)

        adapter = ToolAdapter(
            ratingText = { tool: AiTool ->
                val r = repo.ratingOf(tool, session.getUsername())
                "★ %.1f (%d)".format(r.average, r.count)
            },
            onClick = { tool ->
                startActivity(
                    Intent(this, DetailActivity::class.java)
                        .putExtra(DetailActivity.EXTRA_ID, tool.id)
                )
            }
        )
        b.rvTools.adapter = adapter

        listOf("Semua", "Nulis", "Riset", "Belajar", "Desain", "Tersimpan").forEach { tag ->
            val chip = Chip(this)
            chip.text = tag
            chip.isCheckable = true
            chip.isChecked = tag == selectedTag
            chip.isCheckedIconVisible = false
            chip.isChipIconVisible = false
            chip.setEnsureMinTouchTargetSize(false)
            chip.chipBackgroundColor = ContextCompat.getColorStateList(this, R.color.chip_bg)
            chip.setTextColor(ContextCompat.getColorStateList(this, R.color.chip_text))
            chip.chipStrokeColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.stroke))
            chip.chipStrokeWidth = 2f
            chip.setOnClickListener {
                selectedTag = tag
                refresh()
            }
            b.chipGroup.addView(chip)
        }

        b.etSearch.doAfterTextChanged {
            query = it?.toString().orEmpty()
            refresh()
        }
        refresh()
    }

    override fun onResume() {
        super.onResume()
        if (::adapter.isInitialized) refresh()
    }

    private fun refresh() {
        val result = repo.filter(query, selectedTag)
        adapter.submitList(result) { adapter.notifyDataSetChanged() }
        b.tvEmpty.visibility = if (result.isEmpty()) View.VISIBLE else View.GONE
        b.tvSection.text =
            if (selectedTag == "Semua" && query.isBlank()) "Rekomendasi gratis" else "Hasil filter"
    }
}