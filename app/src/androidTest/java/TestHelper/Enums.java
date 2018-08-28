package TestHelper;

public class Enums {

    public static enum MatchTypes{matches,doesnotexist,selecteddescendantsmatch};
    public static enum ViewMatchers{withid,withtext,withtagkey,withtagvalue,isdisplayed};
    public static enum ViewAction{click,cleartext,typetext,closesoftkeyboard};

    public static MatchTypes MatchTypesIdentifier(String MatchType) throws Exception{
        return converter(MatchTypes.class, MatchType.toLowerCase());
    }

    public static ViewMatchers ViewMatchsIdentifier(String ViewMatcher) throws Exception{
        return converter(ViewMatchers.class, ViewMatcher.toLowerCase());
    }

    public static ViewAction ViewActionIdentifier(String ViewAction) throws Exception{
        return converter(ViewAction.class, ViewAction.toLowerCase());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T extends Enum> T converter(Class<T> type, String value) throws Exception{
        return (T) Enum.valueOf(type, value.toLowerCase());
    }
}