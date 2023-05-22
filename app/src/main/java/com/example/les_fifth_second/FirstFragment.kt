package com.example.les_fifth_second

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.les_fifth_second.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


class FirstFragment : AppCompatActivity() {

    lateinit var binding: FragmentFirstBinding

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            initClicker()
            clearTextView()
        }
    }
    private fun initClicker() {
        with(binding) {
            btn.setOnClickListener {
                if (firstEd.text.isNotEmpty() && secondEd.text.isNotEmpty()) {
                    RetrofitService().api.getPercentage(
                        first = firstEd.text.toString(),
                        second = secondEd.text.toString()
                    ).enqueue(object : retrofit2.Callback<LoveModel> {
                        override fun onResponse(
                            call: Call<LoveModel>,
                            response: Response<LoveModel>
                        ) {
                            if (response.isSuccessful) {
                                findNavController.navigate(
                                    R.id.resultFragment,
                                    bundleOf("result" to response.body())
                                )
                            }
                        }

                        override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                            if (t is IOException) {
                                Toast.makeText(
                                    context,
                                    "Check Internet Connection",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                    })
                } else {
                    Toast.makeText(context, "Please Write Names", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun clearTextView() {
        val clearEditText = arguments?.getBoolean("clearEditText") ?: false
        if (clearEditText) {
            binding.firstEd.text.clear()
            binding.secondEd.text.clear()
        }
    }
}


