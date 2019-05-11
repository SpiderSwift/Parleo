package com.leathersoft.parleo.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.jwang123.flagkit.FlagKit;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.model.Lang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageHolderUtil {


    private static final LanguageHolderUtil instance = new LanguageHolderUtil();

    private Map<String, Drawable> codeMap;

    private Map<String, String> nameMap;

    private Drawable defaultDrawable;


    private static final Map<String, String> langCodeToCountryCode = new HashMap<>();

    static {
        langCodeToCountryCode.put("ru", "ru");
        langCodeToCountryCode.put("gb", "gb");
        langCodeToCountryCode.put("de", "de");
        langCodeToCountryCode.put("zh", "cn");
        langCodeToCountryCode.put("uk", "ua");
        langCodeToCountryCode.put("sv", "se");
        langCodeToCountryCode.put("ko", "kr");
        langCodeToCountryCode.put("fr", "fr");
    }

    private LanguageHolderUtil() {
        codeMap = new HashMap<>();
    }

    public static LanguageHolderUtil getInstance() {
        return instance;
    }


    public void clearMap() {
        codeMap = new HashMap<>();
        nameMap = new HashMap<>();
    }

    public void fillMap(Context context, List<Lang> langList) {
        defaultDrawable = context.getDrawable(R.drawable.ic_languages);
        for (Lang language : langList) {

            Drawable drawable = null;
            try {
                drawable = FlagKit.drawableWithFlag(context, langCodeToCountryCode.get(language.getId()));
            } catch (Exception ignored) { }
            if (drawable != null) {
                codeMap.put(language.getId(), drawable);
            } else {
                codeMap.put(language.getId(), defaultDrawable);
            }
            nameMap.put(language.getId(), language.getName());
        }

    }

    public Drawable findById(String code) {
        Drawable drawable = codeMap.get(code);
        if (drawable != null) {
            return drawable;
        }
        return defaultDrawable;
    }

    public String findNameById(String code) {
        String name = nameMap.get(code);
        if (name != null) {
            return name;
        }
        return "Unknown";
    }

}
