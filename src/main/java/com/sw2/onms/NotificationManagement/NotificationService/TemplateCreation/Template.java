package com.sw2.onms.NotificationManagement.NotificationService.TemplateCreation;

import java.util.HashMap;
import java.util.Map;

public class Template {
    private Map<Language, String> contents;
    private String contentWithActualVal;
    private String latestSentTemplate;

    Template(Map<Language, String> contents) {
        this.contents = contents;
    }

    Template(Language language, String newContent) {
        contents = new HashMap<>();
        contents.put(language, newContent);
        contentWithActualVal = newContent;
    }

    public String setPlaceholders(Map<Placeholder, String> placeholders, Language language) {
        if (contents.containsKey(language)) {
            String message = contents.get(language);
            latestSentTemplate = message;
            boolean flag = true;
            for (int i = 0; i < latestSentTemplate.length(); i++) {
                if (latestSentTemplate.charAt(i) == '{') {
                    String key = "";
                    i++;
                    for (; i < latestSentTemplate.length() && latestSentTemplate.charAt(i) != '}'; i++) {
                        key += latestSentTemplate.charAt(i);
                    }
                    Placeholder placeholderKey = Placeholder.found(key);
                    if (placeholderKey != null && placeholders.containsKey(placeholderKey)) {
                        String val = placeholders.get(placeholderKey);
                        if (placeholders.get(placeholderKey).contains(","))
                            val = "{" + val + "}";
                        message = message.replace("{" + key + "}", val);
                    }//this mean that there will be a variable in the content does not have a value as this
                    // variable is not found in the placeholder map
                    else
                        flag = false;
                    i++;
                }
            }
            if (!flag)
                return null;
            contentWithActualVal = message;
            return message;
        }
        return null;
    }

    public String getContentWithActualVal() {
        return contentWithActualVal;
    }

    public void setContentWithActualVal(String contentWithActualVal) {
        this.contentWithActualVal = contentWithActualVal;
    }

    public String getLatestSentTemplate() {
        return latestSentTemplate;
    }

    public void addNewTemplateLanguage(Language language, String content) {
        contents.put(language, content);
    }
}
