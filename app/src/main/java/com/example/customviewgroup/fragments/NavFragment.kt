package com.example.customviewgroup.fragments

import android.content.Context
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customviewgroup.R
import com.example.customviewgroup.adapters.GridAdapter
import com.example.customviewgroup.adapters.RecyclerAdapter
import com.example.customviewgroup.model.Time
import com.example.customviewgroup.model.User
import com.example.customviewgroup.utils.textChanges
import com.example.customviewgroup.views.CustomEmojiView
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

class NavFragment : Fragment() {

    private lateinit var thisContext: Context
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerAdapter
    private var message: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (container != null) {
            thisContext = container.context
        }
        return inflater.inflate(R.layout.fragment_nav, container, false)
    }

    @DelicateCoroutinesApi
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        val gridView = view.findViewById(R.id.gridViewEmoji) as GridView
        val buttonSend = view.findViewById(R.id.buttonSend) as ImageButton
        val editTextMessage = view.findViewById(R.id.editTextMessage) as EditText
        val gridViewEmoji = view.findViewById(R.id.gridViewEmoji) as GridView
        val bottomSheetRoot = view.findViewById(R.id.bottom_sheet_root) as ConstraintLayout

        gridView.adapter = GridAdapter(thisContext)
        var dataEmoji: MutableList<CustomEmojiView> = mutableListOf()
        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            val customEmojiView = CustomEmojiView(thisContext).apply {
                text = (0 until 100).random().toString() + "\t" + gridViewEmoji.adapter.getItem(position).toString()
                val scale = resources.displayMetrics.density
                val padding9dp = (9 * scale + 0.5f).toInt()
                setPadding(padding9dp, padding9dp, padding9dp, padding9dp)
                setBackgroundResource(R.drawable.bg_custom_emoji_view)
                val marginLayoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                marginLayoutParams.setMargins(0,7   ,15,0)
                layoutParams = marginLayoutParams
            }
            dataEmoji.add(customEmojiView)
            adapter.data[message] =
                User(
                    (adapter.data[message] as User).userNameData,
                    (adapter.data[message] as User).userMessageData,
                    (adapter.data[message] as User).userImage,
                    dataEmoji
                )
            adapter.notifyItemChanged(message)
        }

        adapter = RecyclerAdapter() {
            bottomSheetRoot.visibility = View.VISIBLE
            val mBottomBehavior =
                BottomSheetBehavior.from(bottomSheetRoot)
            mBottomBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            message = it
        }
        layoutManager = LinearLayoutManager(thisContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter



        buttonSend.setOnClickListener {
            sendMessage(editTextMessage)
            editTextMessage.text.clear()
        }

        (buttonSend.background as TransitionDrawable).reverseTransition(200)
        buttonSend.isEnabled = false
        editTextMessage.textChanges()
            .debounce(300)
            .onEach {
                if (!editTextMessage.text.isNullOrEmpty()) {
                    (buttonSend.background as TransitionDrawable).startTransition(200)
                    buttonSend.isEnabled = true
                } else {
                    (buttonSend.background as TransitionDrawable).reverseTransition(200)
                    buttonSend.isEnabled = false
                }
            }
            .launchIn(GlobalScope)
    }

    private fun sendMessage(editTextMessage: EditText){
        val customEmojiView = CustomEmojiView(thisContext).apply {
            text = (0 until 100).random().toString() + "\t" + "H"
            val scale = resources.displayMetrics.density
            val padding9dp = (9 * scale + 0.5f).toInt()
            setPadding(padding9dp, padding9dp, padding9dp, padding9dp)
            setBackgroundResource(R.drawable.bg_custom_emoji_view)
            val marginLayoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            marginLayoutParams.setMargins(0,7   ,15,0)
            layoutParams = marginLayoutParams
        }
        val tmp = adapter.data
        val sdf = SimpleDateFormat("dd MMM")
        val currentDate = sdf.format(Date())
        tmp.add(Time(currentDate))
        tmp.add(
            User(
                "Kirill",
                editTextMessage.text.toString(),
                R.drawable.ic_launcher_background,
                mutableListOf()
            )
        )
        adapter.data = tmp
//        val view = thisContext.currentFocus
//        if (view != null) {
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(view.windowToken, 0)
//        }
    }

}