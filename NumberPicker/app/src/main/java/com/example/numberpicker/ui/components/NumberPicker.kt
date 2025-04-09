package com.example.numberpicker.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.numberpicker.R
import com.example.numberpicker.databinding.NumberPickerLayoutBinding

class NumberPicker(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val binding : NumberPickerLayoutBinding
    private var onValueChangedListener: ((value: Int) -> Unit)? = null
    val allowNegativeNumbers: Boolean = false
    var counter : Int = 0
    init {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = NumberPickerLayoutBinding.inflate(inflater, this, true)
        setUpEventListeners()
        readXmlAttrs(attrs)
    }

    private fun readXmlAttrs(attrs: AttributeSet?) {
        if (attrs != null) {
            context.theme.obtainStyledAttributes(attrs, R.styleable.NumberPicker, 0, 0).apply {
                try {
                    counter = getInteger(R.styleable.NumberPicker_initial_value, 0)
                    reloadScreen()
                } finally {
                    recycle()
                }
            }
        }
    }

    private fun setUpEventListeners() {
        binding.btnAdd.setOnClickListener {
            counter++
            onValueChangedListener?.invoke(counter)
            reloadScreen()
        }

        binding.btnReduce.setOnClickListener {
            if (counter != 0) {
                onValueChangedListener?.invoke(counter)
                counter--
            }
            reloadScreen()
        }
    }

    private fun reloadScreen() {
        binding.lblNumber.text = counter.toString()
    }

    fun setOnValueChanged(listener: (value: Int) -> Unit) {
        onValueChangedListener = listener
    }
}