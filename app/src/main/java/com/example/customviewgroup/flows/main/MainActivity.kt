package com.example.customviewgroup.flows.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customviewgroup.R
import com.example.customviewgroup.adapters.GridAdapter
import com.example.customviewgroup.adapters.ListUserMessageItem
import com.example.customviewgroup.adapters.RecyclerAdapter
import com.example.customviewgroup.databinding.ActivityMainBinding
import com.example.customviewgroup.model.Time
import com.example.customviewgroup.model.User
import com.example.customviewgroup.utils.textChanges
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerAdapter

    private var message: Int = 0
//    private lateinit var binding: ActivityMainBinding

    @DelicateCoroutinesApi
    @FlowPreview
    @ExperimentalCoroutinesApi
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
//        val gridView = binding.bottomSheet.gridViewEmoji
//        gridView.adapter = GridAdapter(this)
//        gridView.onItemClickListener = gridviewOnItemClickListener
//
//        val userData = mutableListOf<ListUserMessageItem>()
//        adapter = RecyclerAdapter(){
//            binding.bottomSheet.bottomSheetRoot.visibility = View.VISIBLE
//            val mBottomBehavior =
//                BottomSheetBehavior.from(binding.bottomSheet.bottomSheetRoot)
//            mBottomBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
//            message = it
//        }
//        layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.layoutManager = layoutManager
//        binding.recyclerView.adapter = adapter
////        binding.recyclerView.addItemDecoration(
////
////        )
//
//
//
//        binding.buttonSend.setOnClickListener {
//            sendMessage()
//            binding.editTextMessage.text.clear()
//        }
//
//        (binding.buttonSend.background as TransitionDrawable).reverseTransition(200)
//        binding.buttonSend.isEnabled = false
//        binding.editTextMessage.textChanges()
//            .debounce(300)
//            .onEach {
//                if (!binding.editTextMessage.text.isNullOrEmpty()) {
//                    (binding.buttonSend.background as TransitionDrawable).startTransition(200)
//                    binding.buttonSend.isEnabled = true
//                } else {
//                    (binding.buttonSend.background as TransitionDrawable).reverseTransition(200)
//                    binding.buttonSend.isEnabled = false
//                }
//            }
//            .launchIn(GlobalScope)
    }
//    private val gridviewOnItemClickListener =
//        OnItemClickListener { parent, v, position, id ->
//            adapter.data[message] =
//                User(
//                    (adapter.data[message] as User).userNameData,
//                    (adapter.data[message] as User).userMessageData,
//                    (adapter.data[message] as User).userImage,
//                    "4" + "\t" + binding.bottomSheet.gridViewEmoji.adapter.getItem(position).toString()
//                    )
//            adapter.notifyItemChanged(message)
//        }
//    private fun sendMessage(){
//        val tmp = adapter.data
//        val sdf = SimpleDateFormat("dd MMM")
//        val currentDate = sdf.format(Date())
//        tmp.add(Time(currentDate))
//        tmp.add(
//            User(
//                "Kirill",
//                binding.editTextMessage.text.toString(),
//                R.drawable.ic_launcher_background,
//                "1" + "\t" + "\uD83D\uDE00"
//            )
//        )
//        adapter.data = tmp
//       val view = this.currentFocus
//        if (view != null) {
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(view.windowToken, 0)
//        }
    }