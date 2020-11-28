package ru.evgeniy.aaacourse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class FragmentMoviesDetails : Fragment() {
    private var backButtonClickListener: BackButtonClickListener? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        view?.findViewById<AppCompatButton>(R.id.backButton)
                ?.setOnClickListener{
                    backButtonClickListener?.onBackButtonPressed()
                }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BackButtonClickListener) {
            backButtonClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        backButtonClickListener = null
    }
}