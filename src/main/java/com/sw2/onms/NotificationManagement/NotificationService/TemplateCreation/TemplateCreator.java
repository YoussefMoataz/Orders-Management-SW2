package com.sw2.onms.NotificationManagement.NotificationService.TemplateCreation;

import com.sw2.onms.NotificationManagement.NotificationService.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class TemplateCreator {

    @Autowired
    TemplateRepo templateRepo;
    Template defualtTemplate;

    public TemplateCreator(TemplateRepo templateRepo) {
        this.templateRepo = templateRepo;
        defualtTemplate = new Template(Language.English, "Welcome to you in our store :)");
    }

    public Template createTemplate(Operation operation, Map<Placeholder, String> placeholders, Language tempLanguage) {
        List<Template> templets = templateRepo.get(operation);
        List<Template> ActualPlaceholdersTemplate = new ArrayList<>();
        for (Template t : templets) {
            String message = t.setPlaceholders(placeholders, tempLanguage);
            if (message != null)
                ActualPlaceholdersTemplate.add(t);
        }
        Random random = new Random();
        if (ActualPlaceholdersTemplate.size() < 1)
            return defualtTemplate;
        return ActualPlaceholdersTemplate.get(random.nextInt(0, ActualPlaceholdersTemplate.size()));
    }

    public void addNewTemplateType(Operation operation, String templateContent, Language tempLanguage) {
        templateRepo.addNewTemplateType(operation, templateContent, tempLanguage);
    }
}