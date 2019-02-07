package protect.budgetwatch.evaluate

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.evaluate3_layout.*
import protect.budgetwatch.R

class EvaluateSlide3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evaluate3_layout)
        btn_thnx.setOnClickListener {
            finish()
        }
    }
}

