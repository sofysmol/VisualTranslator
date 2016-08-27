package com.example.sofysmo.visualtranslator.api.GettyImages;

import android.media.Image;

import java.io.IOException;

/**
 * Created by sofysmo on 21.08.16.
 */
public interface ImagerAPIWrapper {
    String getImage(String key) throws IOException;
}
