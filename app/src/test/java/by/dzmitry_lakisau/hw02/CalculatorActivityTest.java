package by.dzmitry_lakisau.hw02;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(
//        manifest=Config.NONE,
    constants = BuildConfig.class
//    sdk = Constants.SDK_VERSION
)
public class CalculatorActivityTest {

    private ActivityController<CalculatorActivity> activityController;

    @Before
    public void init() {
        activityController = Robolectric.buildActivity(CalculatorActivity.class);
    }

    @Test
    public void testCalculatorActivity() {
        activityController.create(null);
        activityController.start();
        activityController.resume();


        CalculatorActivity calculatorActivity = activityController.get();
        boolean isPlusButtonEnabled = calculatorActivity.findViewById(R.id.plus_button).isEnabled();
        assertEquals(isPlusButtonEnabled, true);

        CalculatorActivity activity = activityController.get();
        Button divideButton = activity.findViewById(R.id.divide_button);
        Button evaluateButton = activity.findViewById(R.id.evaluate_button);
        EditText inputEditText = (EditText)activity.findViewById(R.id.input_field_editText);
        TextView resultTextView = (TextView)activity.findViewById(R.id.result_textView);

        float a = (float) 7.5;
        float b = (float) 2.5;

        inputEditText.setText(String.valueOf(a));
        divideButton.performClick();
        inputEditText.setText(String.valueOf(b));
        evaluateButton.performClick();
        assertEquals(resultTextView.getText(),"3");

        ICalculator spyCalculator = spy(new Calculator());
        doReturn(String.valueOf(a+b)).when(spyCalculator).add(a,b);

        ICalculator mockedCalculator = mock(Calculator.class);
        when(mockedCalculator.multiply(a, b)).thenReturn(String.valueOf(a*b));

        assertEquals(String.valueOf(a*b),mockedCalculator.multiply(a, b));
        assertEquals(String.valueOf(a+b),spyCalculator.add(a,b));
    }

    @After
    public void destroy() {
        activityController.destroy();
    }
}