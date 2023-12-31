package com.sw2.onms.NotificationManagement.NotificationService.TemplateCreation;

import com.sw2.onms.NotificationManagement.NotificationService.Operation;

import java.util.*;

public class TemplateCreator {

    //@Autowired
    TemplateRepo templateRepo = new TemplateRepo();
    Template defualtTemplate;
    public TemplateCreator(){
        defualtTemplate = new Template(Language.English,"Welcome to you in our store :)");
    }
    public Template createTemplate(Operation operation, Map<Placeholder, String> placeholders, Language tempLanguage){
        List<Template> templets = templateRepo.get(operation);
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
        templateRepo.addNewTemplateType(operation, templateContent, tempLanguage);
    }
}