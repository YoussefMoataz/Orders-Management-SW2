package com.sw2.onms.NotificationManagement.TemplateCreation;

import com.sw2.onms.NotificationManagement.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateCreator {
    Map<Operation,List<Template>> templates;
    public TemplateCreator(){
        templates = new HashMap<>();
        Map<Language, String> newTemplate = new HashMap<>();
        newTemplate.put(Language.English, "Dear {"+ Placeholder.Customer.name() +"}, your booking of the {" + Placeholder.Products + "} is confirmed. thanks for using our store :) ");
        //newTemplate.put(Language.Arabic, "Dear {"+ Placeholder.Products +"}, your booking of the {" + Placeholder.Customer.name() + "عزيزي ");

        ArrayList<Template> orderPlacement = new ArrayList<>();
        orderPlacement.add(new Template(newTemplate));
        newTemplate = new HashMap<>();
        orderPlacement.add(new Template(newTemplate));
        templates.put(Operation.OrderPlacement, orderPlacement);
    }
    /*public String createTemplate(Operation operation, Map<Placeholder, List<String>> placeholders, Language tempLanguage){

    }*/
    public void addNewTemplateType(Operation operation, String templateContent, Language tempLanguage){

    }
}
