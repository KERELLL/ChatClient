package com.example.customviewgroup.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import com.example.customviewgroup.R
import com.example.customviewgroup.adapters.ExpandableListAdapter


class SubscribedFragment : Fragment() {

    val header: MutableList<String> = mutableListOf()
    val body: MutableList<MutableList<String>> = mutableListOf()
    private lateinit var thisContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (container != null) {
            thisContext = container.context
        }
        return inflater.inflate(R.layout.fragment_subscribed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firstHeader : MutableList<String> = mutableListOf()
        firstHeader.add("testing 1220 mes")
        firstHeader.add("bruh 34 mes")
        val secondHeader : MutableList<String> = mutableListOf()
        val thirdHeader : MutableList<String> = mutableListOf()
        header.add("#general")
        header.add("#Development")
        secondHeader.add("testing 1 mes")
        secondHeader.add("test 34 mes")
        header.add("#Design")
        body.add(firstHeader)
        body.add(secondHeader)
        body.add(thirdHeader)
        val expendableListView = view.findViewById<ExpandableListView>(R.id.expandableStreamsList)
        expendableListView.setAdapter(ExpandableListAdapter(thisContext, header, body))
    }

}