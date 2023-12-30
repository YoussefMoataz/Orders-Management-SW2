package com.sw2.onms.NotificationManagement.TemplateCreation;

import com.sw2.onms.NotificationManagement.Operation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {
    Map<Language,String> contents;
    Operation corespondingOperation;
    Template(Map<Language,String> contents){
        this.contents = contents;
    }
    Template(Language language, String newContent){
        contents = new HashMap<>();
        contents.put(language,newContent);
    }
    public String setPlaceholders(Map<Placeholder, String> placeholders, Language language){
        if(contents.containsKey(language)){
            String message = contents.get(language),content = message;
            boolean flag = true;
            for(int i = 0;i<content.length();i++){
                if(content.charAt(i)=='{'){
                    String key = "";
                    i++;
                    for(;i < content.length() && content.charAt(i) != '}'; i++){
                        key += content.charAt(i);
                    }
                    Placeholder placeholderKey = Placeholder.found(key);
                    if (placeholderKey != null && placeholders.containsKey(placeholderKey)) {
                        String val =  placeholders.get(placeholderKey);
                        if(placeholders.get(placeholderKey).contains(","))
                            val = "{" + val + "}";
                        message = message.replace("{" + key + "}", val);
                    }//this mean that there will be a variable in the content does not have a value as this
                    // variable is not found in the placeholder map
                    else
                        flag = false;
                    i++;
                }
            }
            if(!flag)
                return null;
            return message;
        }
        return  null;
    }
    public void addNewTemplateLanguage(Language language, String content){
        contents.put(language, content);
    }
}
