package com.sw2.onms.NotificationManagement.TemplateCreation;

import com.sw2.onms.NotificationManagement.Operation;

import java.util.*;

public class TemplateCreator {
    //is this must be @Component
    TemplateDB templateDB;
    Template defualtTemplate;
    public TemplateCreator(){
        templateDB = new TemplateDB();
        defualtTemplate = new Template(Language.English,"Welcome to you in our store :)");
    }
    public Template createTemplate(Operation operation, Map<Placeholder, String> placeholders, Language tempLanguage){
        List<Template> templets = templateDB.get(operation);
        List<Template> ActualPlaceholdersTemplate = new ArrayList<>();
        for(Template t : templets){
            String message = t.setPlaceholders(placeholders,tempLanguage);
            if(message != null)
                ActualPlaceholdersTemplate.add(t);
        }
        Random random = new Random();
        if(ActualPlaceholdersTemplate.size() < 1)
            return defualtTemplate;
        return ActualPlaceholdersTemplate.get(random.nextInt(0,ActualPlaceholdersTemplate.size()));
    }
    public void addNewTemplateType(Operation operation, String templateContent, Language tempLanguage){
        templateDB.addNewTemplateType(operation, templateContent, tempLanguage);
    }
}