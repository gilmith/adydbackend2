package org.jacobo.adyd.service.helpers;

import org.springframework.http.MediaType;

import java.awt.*;

public class AdydUtils {

    public static String getContentTypeFromFileName(String fileName){
        return MediaType.valueOf(fileName.split("\\.")[1]).toString();
    }


}
