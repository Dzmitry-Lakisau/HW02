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
import static org.hamcrest.Matchers.not;

public class CalculatorAndroidTest {

    private ActivityTestRule<CalculatorActivity> calculatorActivity = new ActivityTestRule<>(CalculatorActivity.class);

    @Test
    public void testActivityRun() {
        calculatorActivity.launchActivity(new Intent());

        ViewInteraction evaluateButton = onView(withId(R.id.evaluate_button));
        ViewInteraction multiplyButton = onView(withId(R.id.multiply_button));
        ViewInteraction divideButton = onView(withId(R.id.divide_button));

        evaluateButton.check(matches(isDisplayed()));
        evaluateButton.check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if(view.isEnabled()) {
                    throw new IllegalStateException("Button '=' is enabled");
                }
            }
        });

        onView(withId(R.id.input_field_editText)).perform(typeText("1.2"));
        multiplyButton.check(matches(isEnabled()));
        multiplyButton.perform(click());
        onView(withId(R.id.input_field_editText)).perform(typeText("1.2"));
        evaluateButton.perform(click());
        onView(withId(R.id.result_textView)).check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if(!((TextView)view).getText().toString().equals("1.44")) {
                    throw new IllegalStateException("Result is wrong. Expected 1.44");
                }
            }
        });

        onView(withId(R.id.input_field_editText)).perform(typeText("1.2"));
        divideButton.check(matches(isEnabled()));
        divideButton.perform(click());
        onView(withId(R.id.input_field_editText)).perform(typeText("0"));
        evaluateButton.check(matches(not(isEnabled())));
    }
}