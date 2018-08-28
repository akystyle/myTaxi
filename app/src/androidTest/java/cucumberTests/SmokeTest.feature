Feature: Smoke Test Feature
    Scenario Outline: Smoke Test Scenario
        Given User checks "" "matches" with "withText" on view "<UserNameBox>" with "withId"
        When User performs "typeText" with "<UserName>" on view "<UserNameBox>" with "withId"
        And User performs "closeSoftKeyboard" with "" on view "<UserNameBox>" with "withId"
        Given User checks "" "matches" with "withText" on view "<PwdBox>" with "withId"
        When User performs "typeText" with "<Pwd>" on view "<PwdBox>" with "withId"
        And User performs "closeSoftKeyboard" with "" on view "<PwdBox>" with "withId"
        And User performs "click" with "" on view "<LgnBtn>" with "withId"
        And User waits for "2" seconds
        When User performs "typeText" with "<Srch>" on view "<SrchBox>" with "withId"
        And User performs "closeSoftKeyboard" with "" on view "<SrchBox>" with "withId"


    Examples:
        |UserNameBox|UserName|PwdBox|Pwd|LgnBtn|SrchBox|Srch|
        |R.id.edt_username|crazydog335|R.id.edt_password|venture|R.id.btn_login|R.id.textSearch|sa|