import android.content.Context;
import android.content.res.Resources;

import com.example.sofysmo.visualtranslator.R;
import com.example.sofysmo.visualtranslator.api.Yandex.YandexRESTAPIWrapper;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by sofysmo on 06.08.16.
 */
public class App {
    public static void main(String[] args)
    {
        try {
            YandexRESTAPIWrapper yandex
                    = new YandexRESTAPIWrapper("trnsl.1.1.20160806T131704Z.ef4f73873fcfe8b6.c2596fef633e4d4156afc7bedb702622d9e4a10c");
            System.out.println(yandex.translate("hello", new Locale("en"), new Locale("ru")));
        }catch (IOException e)
        {

        }
    }
}
