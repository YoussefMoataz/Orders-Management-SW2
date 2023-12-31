package com.sw2.onms.NotificationManagement.NotificationService.TemplateCreation;

import com.sw2.onms.NotificationManagement.NotificationService.Operation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@Component
public class TemplateDB {
    private Map<Operation, List<Template>> templates;
    TemplateDB(){
        templates = new HashMap<>();

        Template ordPlacementTemplate1 = new Template(Language.English, "Dear {"+ Placeholder.CustomerName.name() +"}, your booking of the {" + Placeholder.Products + "} is confirmed. thanks for using our store :) ");
        ordPlacementTemplate1.addNewTemplateLanguage(Language.French,"Cher {"+ Placeholder.CustomerName.name() +"}, votre réservation de {"+ Placeholder.Products + "} est confirmée. Merci d'utiliser notre magasin :)");
        Template ordPlacementTemplate2 = new Template(Language.English, "Hello {"+ Placeholder.CustomerName.name() +"}, your order {" + Placeholder.OrderID + "} with a price of {"+ Placeholder.OrderPrice + "} has been placed successfully. we hope to see you soon in our store :) ");
        ordPlacementTemplate2.addNewTemplateLanguage(Language.French,"Bonjour  {"+ Placeholder.CustomerName.name() +"}, votre commande  {" + Placeholder.OrderID + "} avec un prix de {"+ Placeholder.OrderPrice + "a été passée avec succès. Nous espérons vous voir bientôt dans notre magasin :)");
        List<Template> orderPlacement = new ArrayList<>();
        orderPlacement.add(ordPlacementTemplate1);  orderPlacement.add(ordPlacementTemplate2);
        templates.put(Operation.OrderPlacement,orderPlacement);

        Template ordShipmentTemplate1 = new Template(Language.English, "Welcome to our store, {" + Placeholder.CustomerName.name() + "}! Your shipment of {" + Placeholder.Products + "} has been confirmed. :)");
        ordShipmentTemplate1.addNewTemplateLanguage(Language.French,"Bienvenue dans notre magasin, {" + Placeholder.CustomerName.name() + "} ! Votre expédition de {" + Placeholder.Products + "} a été confirmée. :)");
        Template ordShipmentTemplate2 = new Template(Language.English, "Hi {" + Placeholder.CustomerName.name() + "}! we're delighted to inform you that your order {" + Placeholder.OrderID + "} has been shipped successfully. The total price is {" + Placeholder.OrderPrice + "}. Your purchase is on its way, and we look forward to welcoming you to our store :)");
        ordShipmentTemplate2.addNewTemplateLanguage(Language.French,"Salut {" + Placeholder.CustomerName.name() + "} ! Nous sommes ravis de vous informer que votre commande {" + Placeholder.OrderID + "} a été expédiée avec succès. Le prix total est de {" + Placeholder.OrderPrice + "}. Votre achat est en chemin, et nous avons hâte de vous accueillir dans notre magasin :)");
        List<Template> orderShipment = new ArrayList<>();
        orderShipment.add(ordShipmentTemplate1);  orderShipment.add(ordShipmentTemplate2);
        templates.put(Operation.OrderShipment,orderShipment);

    }
    public List<Template> get(Operation operation){
        if(templates.containsKey(operation)){
            return templates.get(operation);
        }
        return null;
    }
    public void addNewTemplateType(Operation operation, String templateContent, Language tempLanguage){
        if(templates.containsKey(operation)){
            templates.get(operation).add(new Template(tempLanguage,templateContent));
        }else{
            List<Template> newTemplateWithNewOper = new ArrayList<>();
            newTemplateWithNewOper.add(new Template(tempLanguage,templateContent));
            templates.put(operation,newTemplateWithNewOper);
        }
    }
}
