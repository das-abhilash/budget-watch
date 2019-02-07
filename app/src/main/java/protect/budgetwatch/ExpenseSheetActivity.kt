package protect.budgetwatch

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expense_sheet.*
import kotlinx.android.synthetic.main.content_expense_sheet.*

class ExpenseSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_sheet)
        setSupportActionBar(toolbar)
        btn_got_it.setOnClickListener {
            finish()
        }

        val sharedpreferences = getSharedPreferences("yenom", Context.MODE_PRIVATE)
        val rent = sharedpreferences.getInt("rent", 0)
        val bills = sharedpreferences.getInt("bills", 0)
        val loan = sharedpreferences.getInt("loan", 0)
        val msls = sharedpreferences.getInt("msls", 0)
        val salary = sharedpreferences.getInt("salary", 0)
        val fixedExpenses = rent + bills + loan + msls
        tv_income.text = salary.toString()
        tv_fixed_expenses.text = fixedExpenses.toString()
        val availableBalance = salary - fixedExpenses
        val saving = (availableBalance * 0.2).toInt()
        tv_savings.text = (saving).toString()
        val monthlyExpense = availableBalance - saving
        val sosExpense = (monthlyExpense * 0.2).toInt()
        tv_sos_saving.text = sosExpense.toString()
        tv_expense.text = monthlyExpense.toString()
        sharedpreferences.edit().putInt("monthly_expense", monthlyExpense)
            .putInt("fixed_expense", fixedExpenses).commit()
        val _db = DBHelper(this)

        _db.insertBudget("Grocery", (monthlyExpense * 0.25).toInt(), 1)
        _db.insertBudget("SosExpense", sosExpense, 1)
        _db.insertBudget("Dining Out", (monthlyExpense * 0.1).toInt(), 1)
        _db.insertBudget("Entertainment", (monthlyExpense * 0.1).toInt(), 1)
        _db.insertBudget("Travel/Transport", (monthlyExpense * 0.25).toInt(), 1)
        _db.insertBudget("Shopping", (monthlyExpense * 0.1).toInt(), 1)

        if (rent > 0) {
            _db.insertBudget("Home/Rent", rent, 0)
            _db.insertTransaction(DBHelper.TransactionDbIds.EXPENSE, "Home/Rent", "", "Home/Rent",
                rent.toDouble(), "", System.currentTimeMillis(), "")
        }
        if (bills > 0) {
            _db.insertBudget("Utilities And Bills", bills, 0)
            _db.insertTransaction(DBHelper.TransactionDbIds.EXPENSE,
                "Utilities And Bills",
                "",
                "Utilities And Bills",
                bills.toDouble(),
                "",
                System.currentTimeMillis(),
                "")
        }
        if (loan > 0) {
            _db.insertBudget("Loan/Insurence/Emi", loan, 0)
            _db.insertTransaction(DBHelper.TransactionDbIds.EXPENSE,
                "Loan/Insurence/Emi",
                "",
                "Loan/Insurence/Emi",
                loan.toDouble(),
                "",
                System.currentTimeMillis(),
                "")
        }
        if (msls > 0) {
            _db.insertBudget("Miscellaneous", msls, 0)
            _db.insertTransaction(DBHelper.TransactionDbIds.EXPENSE,
                "Miscellaneous",
                "",
                "Miscellaneous",
                msls.toDouble(),
                "",
                System.currentTimeMillis(),
                "")
        }
    }

}
