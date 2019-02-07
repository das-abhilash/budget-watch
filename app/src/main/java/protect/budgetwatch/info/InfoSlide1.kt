package protect.budgetwatch.info

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.info1_layout.*

import protect.budgetwatch.R

class InfoSlide1 : Fragment() {

    var editor: SharedPreferences.Editor? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val sharedpreferences = activity?.getSharedPreferences("yenom", Context.MODE_PRIVATE);
        editor = sharedpreferences?.edit()
        return inflater.inflate(R.layout.info1_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        et_salary.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editor?.putInt("salary", s.toString().toInt())?.commit()
            }
        })

    }
}
