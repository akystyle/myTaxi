package TestHelper;

        import android.content.Context;
        import android.content.res.Resources;
        import android.support.test.espresso.ViewAction;
        import android.support.test.espresso.ViewAssertion;
        import android.support.test.espresso.action.ViewActions;

        import com.mytaxi.android_demo.App;
        import com.mytaxi.android_demo.TestApproach2;
        import com.mytaxi.android_demo.test.BuildConfig;

        import org.hamcrest.Matcher;
        import TestHelper.Enums;

        import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
        import static android.support.test.espresso.assertion.ViewAssertions.matches;
        import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
        import static android.support.test.espresso.matcher.ViewMatchers.withId;
        import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
        import static android.support.test.espresso.Espresso.onView;
        import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class GenericHelperMethods {

    //TODO Convert into step definition
    //@When("^User checks \"(.*?)\" \"(.*?)\" with \"(.*?)\" on view \"(.*?)\" with \"(.*?)\"$")
    public void UserCheck(String match, String matchType, String matchWith, String ViewValue, String ViewFinder) throws Exception{
        Matcher matcher = viewMatcherHelper(matchWith, match);
        ViewAssertion asserter = matchTypeHelper(matchType, matcher);
        Matcher viewMatcher = viewMatcherHelper(ViewFinder, ViewValue);

        onView(viewMatcher).check(asserter);
    }

    //TODO Convert into step definition
    //@When("^User performs \"(.*?)\" with \"(.*?)\" on view \"(.*?)\" with \"(.*?)\"$")
    public void UserPerform(String action, String actionSupportInfo, String ViewValue, String ViewFinder) throws Exception{
        ViewAction actionToBePerfomed = viewActionHelper(action, actionSupportInfo);
        Matcher viewMatcher = viewMatcherHelper(ViewFinder, ViewValue);

        onView(viewMatcher).perform(actionToBePerfomed);
    }

    //TODO Convert into step definition
    //@Given("^User waits for \"(.*?)\" seconds$")
    public void UserWaits(String seconds) throws Exception{
        int sec = Integer.parseInt(seconds);
        sec = sec * 1000;
        Thread.sleep(sec);
    }

    //Helpers
    //TODO move to other logical class
    //TODO this one needs fixing
    public int byIdName(String name) {
        return Resources.getSystem().getIdentifier(name, "string", BuildConfig.APPLICATION_ID);
        /*
        Resources res = TestApproach2.myContext.getResources();
        return res.getIdentifier(name, "string", TestApproach2.myContext.getPackageName());
        */
    }

    public Matcher viewMatcherHelper(String matcher, String match) throws Exception{
        Matcher result = null;
        Enums.ViewMatchers viewMatch = null;
        try{
            viewMatch = Enums.ViewMatchsIdentifier(matcher);
        }catch(Exception e){
            throw new Exception("Could not find matcher as: " + matcher, e);
        }
        switch(viewMatch){
            case withid:
                result = withId(byIdName(match));
                break;
            case withtext:
                result = withText(match);
                break;
            case isdisplayed:
                result = isDisplayed();
                break;
            case withtagkey:
                result = withTagKey(byIdName(match));
                break;
            //TODO implementation for other ViewMatchers
        }

        return result;
    }

    public ViewAssertion matchTypeHelper(String matchType, Matcher matcher) throws Exception{
        ViewAssertion asserter = null;
        Enums.MatchTypes match = null;
        try {
            match = Enums.MatchTypesIdentifier(matchType);
        }catch(Exception e){
            throw new Exception("Could not find matcher as: " + matchType, e);
        }
        switch(match){
            case matches:
                asserter = matches(matcher);
                break;
            case doesnotexist:
                asserter = doesNotExist();
                break;
            default:
                //TODO add support for multiple matchers, to support heirarichal searches
                //asserter = selectedDescendantsMatch(matcher,matcher);

                throw new Exception("Match Type: " + matchType + " is not supported as of now.");
        }
        return asserter;
    }

    public ViewAction viewActionHelper(String action, String value) throws Exception{
        ViewAction result = null;
        Enums.ViewAction match = null;
        try {
            match = Enums.ViewActionIdentifier(action);
        }catch(Exception e){
            throw new Exception("Could not find Action as: " + action, e);
        }
        switch(match){
            case cleartext:
                result = ViewActions.clearText();
                break;
            case click:
                result = ViewActions.click();
                break;
            case closesoftkeyboard:
                result = ViewActions.closeSoftKeyboard();
                break;
            case typetext:
                result = ViewActions.typeText(value);
                break;
            default:
                //TODO implement remaining actions
                throw new Exception("Action Type: " + action + " is not supported as of now.");
        }
        return result;
    }
}