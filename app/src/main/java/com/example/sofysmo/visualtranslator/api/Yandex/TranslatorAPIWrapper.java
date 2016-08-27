package com.example.sofysmo.visualtranslator.api.Yandex;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by sofysmo on 06.08.16.
 */
public interface TranslatorAPIWrapper {
    String translate(String text, Locale localeFrom, Locale localeTo)  throws IOException;
}
