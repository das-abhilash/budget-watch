package protect.budgetwatch.info

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.evaluate2_layout.*
import protect.budgetwatch.R
import protect.budgetwatch.evaluate.EvaluateSlide4
import protect.budgetwatch.evaluate.EvaluateSlide9


class EvaluateSlide2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evaluate2_layout)

        btn_yes.setOnClickListener {
            val intent = Intent(this, EvaluateSlide4::class.java)
            startActivity(intent)
            finish()
        }

        btn_no.setOnClickListener {
            val intent = Intent(this, EvaluateSlide9::class.java)
            startActivity(intent)
            finish()
        }

    }
}
