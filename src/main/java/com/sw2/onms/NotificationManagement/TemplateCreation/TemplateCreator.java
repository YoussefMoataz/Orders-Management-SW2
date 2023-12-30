package com.sw2.onms.NotificationManagement.TemplateCreation;

import com.sw2.onms.NotificationManagement.Operation;

import java.util.*;

public class TemplateCreator {
    Map<Operation,List<Template>> templates;
    //is this must be @Component
    TemplateDB templateDB = new TemplateDB();
    public TemplateCreator(){
        templates = new HashMap<>();
    }
    public String createTemplate(Operation operation, Map<Placeholder, String> placeholders, Language tempLanguage){
        List<Template> templets = templateDB.get(operation);
        List<String> templatesContents = new ArrayList<>();
        for(Template t : templets){
            String message = t.setPlaceholders(placeholders,tempLanguage);
            if(message != null)
                templatesContents.add(message);
        }
        Random random = new Random();
        return templatesContents.get(random.nextInt(0,templatesContents.size()));
    }/*
    public void addNewTemplateType(Operation operation, String templateContent, Language tempLanguage){

    }*/
}