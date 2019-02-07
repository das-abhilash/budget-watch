package protect.budgetwatch.info

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment

import com.github.paolorotolo.appintro.AppIntro

import protect.budgetwatch.DBHelper
import protect.budgetwatch.ExpenseSheetActivity


class InfoActivity : AppIntro() {
    private var _db: DBHelper? = null

    override fun init(savedInstanceState: Bundle?) {
        addSlide(InfoSlide1())
        addSlide(InfoSlide2())
        //        addSlide(new InfoSlide3());
        //        addSlide(new InfoSlide4());
        //        addSlide(new InfoSlide5());
        //        addSlide(new InfoSlide6());
        //        addSlide(new InfoSlide7());
        setBarColor(Color.BLACK)
        setNextArrowColor(Color.BLACK)
        setSeparatorColor(Color.BLACK)
        setSkipText("")
        _db = DBHelper(this)
    }

    override fun onSkipPressed(fragment: Fragment?) {

    }

    override fun onDonePressed(fragment: Fragment?) {
        val sharedpreferences = getSharedPreferences("yenom", Context.MODE_PRIVATE)
        val rent = sharedpreferences.getInt("rent", 0)
        val bills = sharedpreferences.getInt("bills", 0)
        val loan = sharedpreferences.getInt("loan", 0)
        val msls = sharedpreferences.getInt("msls", 0)

        val intent = Intent(this, ExpenseSheetActivity::class.java)
        startActivity(intent)
        finish()
    }
}


