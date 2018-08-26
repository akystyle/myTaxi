package TestHelper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import java.io.File;

public class SharedPrefUtil {

    public void RemovedCachedCredentials(){
        File root = InstrumentationRegistry.getTargetContext().getFilesDir().getParentFile();
        String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();
        if(!(sharedPreferencesFileNames==null)){
            for (String fileName : sharedPreferencesFileNames) {
                InstrumentationRegistry.getTargetContext().getSharedPreferences(fileName.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
            }
        }
    }

}
