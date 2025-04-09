package com.example.numberpicker.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import com.example.numberpicker.R
import com.example.numberpicker.databinding.PageSelectorBinding
import androidx.core.graphics.toColorInt

class PageSelector(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val binding : PageSelectorBinding
    private var actualPage: Int = 0
    private var pageQuantity: Int = 3
        set(value) {
            field = if (value >= 1) value else 1
        }
    private var selectedColor: String = "#0F0F0F"
    private var normalColor: String = "#A7A7A7"
    init {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = PageSelectorBinding.inflate(inflater, this, true)
        readXmlAttr(attrs)
    }

    private fun readXmlAttr(attrs: AttributeSet?) {
        if (attrs != null) {
            context.theme.obtainStyledAttributes(attrs, R.styleable.PageSelector, 0, 0).apply {
                try {
                    pageQuantity = getInteger(R.styleable.PageSelector_page_quantity, 1)
                    actualPage = getInteger(R.styleable.PageSelector_initial_page, 0)
                    normalColor = getString(R.styleable.PageSelector_normal_color) ?: "#A7A7A7"
                    selectedColor = getString(R.styleable.PageSelector_selected_color) ?: "#0F0F0F"
                    reloadScreen()
                } finally {
                    recycle()
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun addButton(number: Int) {
        val button = Button(context)
        button.text = number.toString()

        val layoutParams = LayoutParams(
            0,
            LayoutParams.WRAP_CONTENT
        ).apply {
            weight = 1f
            setMargins(8, 8, 8, 8)
        }
        button.layoutParams = layoutParams

        //button.background = context.getDrawable(R.style.)

        val bgColor = if (number == actualPage)
            selectedColor.toColorInt()
        else
            normalColor.toColorInt()

        button.setBackgroundColor(bgColor)
        button.setTextColor(getContrastTextColor(bgColor))

        button.setOnClickListener {
            if (number != actualPage) {
                actualPage = number
                reloadScreen()
            }
        }

        addView(button)
    }

    private fun reloadScreen() {
        removeAllViews()
        for (i in 1..pageQuantity) {
            addButton(i)
        }
    }

    private fun getContrastTextColor(backgroundColor: Int): Int {
        val red = (backgroundColor shr 16) and 0xFF
        val green = (backgroundColor shr 8) and 0xFF
        val blue = backgroundColor and 0xFF

        val luminance = (0.299 * red + 0.587 * green + 0.114 * blue)

        return if (luminance > 186) {
            0xFF000000.toInt()
        } else {
            0xFFFFFFFF.toInt()
        }
    }

}