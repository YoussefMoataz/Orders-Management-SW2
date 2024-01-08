## Team Members:
* [Sama Ahmed](https://github.com/SamaAhmedS)
* [Hassan Magdi](https://github.com/hassan1876)
* [Mohamed Maged](https://github.com/Ibn-Maged)
* [Youssef Moataz](https://github.com/YoussefMoataz)

## Class Diagram:
![Class Diagram - ONMS-SW2 - 2](https://github.com/YoussefMoataz/Orders-Management-SW2/assets/50296580/fa719f3c-0a3b-4b3c-b872-ac29d69d4f6f)

## Endpoints:
| Exposed API | Description | Input |
| ----------- | ----------- | ----- |
| POST ```/customer/add``` |Creates a new account for that customer and adds it to customers repo, and return the customer object that was created | Customer object |
| PUT ```/customer/update/{email}``` | Updates a customer account if exists. | Takes the email of the account to be updated, and receives a customer object in the request body
| GET ```/product/search/available``` | Returns all the products that are currently in stock | NONE |
| POST ```orders/place_order``` | Create a new order add it to orders repo and complete payment and send notification to the customer | Simple Order object |
| POST ```orders/place_order``` | Create a new order add it to orders repo and complete payment for each customer and send notification to each customer | Compound Order object |
| GET ```list_details/{ID}``` | List all details of the order wether it's simple or compound | NONE |
| PUT ```orders/ship/{ID}``` | Change the order status int shipping and deduct the shipping fees from each customer in this order and send notification to each customer | NONE |
| PUT ```orders/cancel/{ID}``` | Check if the order can be canceled if it is it change it's status to canceled and return the money to the customers and return the products to the products repo | NONE |
| GET ```/notifications/getMostSentTemplates``` | Returns a string has the statistics for templates, sorted by their frequencies, representing the number of times each template has been sent. | NONE |
| GET ```/notifications/getMostNotifiedContactAddresses``` | Returns a string has the statistics for the most notified email addresses and phone numbers | NONE |
