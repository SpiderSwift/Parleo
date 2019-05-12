package com.leathersoft.parleo.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.jwang123.flagkit.FlagKit;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.messaging.LanguageModel;
import com.leathersoft.parleo.network.model.Lang;
import com.leathersoft.parleo.network.model.Language;

import java.util.ArrayList;
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

    public Map<String, Drawable> getCodeMap() {
        return codeMap;
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


    public void fillMapModel(Context context, List<LanguageModel> langList) {
        defaultDrawable = context.getDrawable(R.drawable.ic_languages);
        for (LanguageModel language : langList) {

            Drawable drawable = null;
            try {
                drawable = FlagKit.drawableWithFlag(context, langCodeToCountryCode.get(language.getCode()));
            } catch (Exception ignored) { }
            if (drawable != null) {
                codeMap.put(language.getCode(), drawable);
            } else {
                codeMap.put(language.getCode(), defaultDrawable);
            }
            nameMap.put(language.getCode(), language.getName());
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


    public List<LanguageModel> createModelList(List<String> keys) {
        List<LanguageModel> languageModels = new ArrayList<>();
        for (String code : codeMap.keySet()) {
            LanguageModel model = new LanguageModel(code, findNameById(code), 0, 0);
            if (keys.contains(code)) {
                model.setChosen(1);
            }
            languageModels.add(model);
        }
        return languageModels;
    }


    public List<LanguageModel> createModelLists(List<Language> keys) {
        List<LanguageModel> languageModels = new ArrayList<>();
        for (String code : codeMap.keySet()) {
            LanguageModel model = null;
            for (Language language : keys) {
                if (language.getCode().equals(code)) {
                   model = new LanguageModel(code, findNameById(code), language.getLevel() - 1, 1);
                }
            }
            if (model == null) {
                model = new LanguageModel(code, findNameById(code), 0, 0);
            }

            languageModels.add(model);
        }
        return languageModels;
    }


    public List<String> createKeyList(List<LanguageModel> modelList) {
        List<String> stringList = new ArrayList<>();
        for (LanguageModel model : modelList) {
            if (model.isChosen() == 1) {
                stringList.add(model.getCode());
            }
        }
        return stringList;
    }


}
