package protect.budgetwatch.evaluate

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.evaluate5_layout.*
import protect.budgetwatch.R

class EvaluateSlide5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evaluate5_layout)
        btn_go_ahead.setOnClickListener {
            val intent = Intent(this, EvaluateSlide6::class.java)
            intent.putExtra("amount", et_desired_amount.text.toString())
            startActivity(intent)
            finish()
        }
    }
}