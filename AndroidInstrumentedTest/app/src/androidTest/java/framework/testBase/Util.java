package framework.testBase;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by vivek on 26/12/18.
 */

public class Util {
    public static String getProperty(String key,Context context) throws IOException {
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("config.properties");
//        InputStream inputStream = properties.getClass().getResourceAsStream("/home/vivek/androidapp/app/src/java/framework/Properties/config.properties");
        properties.load(inputStream);
        return properties.getProperty(key);

    }
}
