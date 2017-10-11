package by.dzmitry_lakisau.hw02;

import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.TextView;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class CalculatorAndroidTest {

    private ActivityTestRule<CalculatorActivity> calculatorActivity = new ActivityTestRule<>(CalculatorActivity.class);

    @Test
    public void testActivityRun() {
        calculatorActivity.launchActivity(new Intent());

        ViewInteraction evaluateButton = onView(withId(R.id.evaluate_button));
        evaluateButton.check(matches(isDisplayed()));
        evaluateButton.check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if(view.isEnabled()) {
                    throw new IllegalStateException("button enabled");
                }
            }
        });

        onView(withId(R.id.input_field_editText)).perform(typeText("1+2"));

        evaluateButton.check(matches(isEnabled()));

        evaluateButton.perform(click());

        onView(withId(R.id.result_textView)).check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if(!((TextView)view).getText().toString().equals("3")) {
                    throw new IllegalStateException("result wrong. Expected 3");
                }
            }
        });

    }

}