package protect.budgetwatch;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TransactionViewActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.transaction_view_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();

        final Bundle b = getIntent().getExtras();
        final int transactionId = b.getInt("id");
        final int type = b.getInt("type");
        final boolean updateTransaction = b.getBoolean("update", false);
        final boolean viewTransaction = b.getBoolean("view", false);

        switch(type)
        {
            case DBHelper.TransactionDbIds.EXPENSE:
                setTitle(R.string.addExpenseTransactionTitle);
                break;

            case DBHelper.TransactionDbIds.REVENUE:
                setTitle(R.string.addRevenueTransactionTitle);
                break;
        }

        final Calendar date = new GregorianCalendar();
        final DateFormat dateFormatter = SimpleDateFormat.getDateInstance();
        final EditText dateField = (EditText) findViewById(R.id.date);
        dateField.setText(dateFormatter.format(date.getTime()));

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day)
            {
                date.set(year, month, day);
                dateField.setText(dateFormatter.format(date.getTime()));
            }
        };

        dateField.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    int year = date.get(Calendar.YEAR);
                    int month = date.get(Calendar.MONTH);
                    int day = date.get(Calendar.DATE);
                    DatePickerDialog datePicker = new DatePickerDialog(TransactionViewActivity.this,
                            dateSetListener, year, month, day);
                    datePicker.show();
                }
            }
        });

        final Spinner budgetSpinner = (Spinner) findViewById(R.id.budgetSpinner);
        DBHelper db = new DBHelper(TransactionViewActivity.this);
        List<String> budgetNames = db.getBudgetNames();
        ArrayAdapter<String> budgets = new ArrayAdapter<>(this, R.layout.spinner_textview, budgetNames);
        budgetSpinner.setAdapter(budgets);

        final EditText nameField = (EditText) findViewById(R.id.name);
        final EditText accountField = (EditText) findViewById(R.id.account);
        final EditText valueField = (EditText) findViewById(R.id.value);
        final EditText noteField = (EditText) findViewById(R.id.note);
        final Button cancelButton = (Button)findViewById(R.id.cancelButton);
        final Button saveButton = (Button)findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                final String name = nameField.getText().toString();
                if (name.isEmpty())
                {
                    Snackbar.make(v, "Name needed", Snackbar.LENGTH_LONG).show();
                    return;
                }

                final String budget = (String)budgetSpinner.getSelectedItem();
                if (budget == null)
                {
                    Snackbar.make(v, "No budget selected", Snackbar.LENGTH_LONG).show();
                    return;
                }

                final String account = accountField.getText().toString();
                // The account field is optional, so it is OK if it is empty

                final String valueStr = valueField.getText().toString();
                if (valueStr.isEmpty())
                {
                    Snackbar.make(v, "Value needed", Snackbar.LENGTH_LONG).show();
                    return;
                }

                double value;
                try
                {
                    value = Double.parseDouble(valueStr);
                }
                catch (NumberFormatException e)
                {
                    Snackbar.make(v, "Value invalid", Snackbar.LENGTH_LONG).show();
                    return;
                }

                final String note = noteField.getText().toString();
                // The note field is optional, so it is OK if it is empty

                EditText dateField = (EditText) findViewById(R.id.date);
                final String dateStr = dateField.getText().toString();
                final DateFormat dateFormatter = SimpleDateFormat.getDateInstance();
                long dateMs;
                try
                {
                    dateMs = dateFormatter.parse(dateStr).getTime();
                }
                catch (ParseException e)
                {
                    Snackbar.make(v, "Date invalid", Snackbar.LENGTH_LONG).show();
                    return;
                }

                DBHelper db = new DBHelper(TransactionViewActivity.this);

                db.insertTransaction(type, name, account, budget,
                        value, note, dateMs);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}
