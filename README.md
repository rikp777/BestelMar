# BestelMar
> This project is developed as a school project

## concept

The application will serve as an ordering system for cinemas and 
cafés. The application makes it possible to order for example a drink, 
when a costumer orders something the barista will get a live feed of what to
prepare. the barista can then send the status of the order back to the costumer.
When a customer orders something this will be shared live with the baristas. 
This provides an even finer ordering experience and contributes to an 
innovative way of hospitality.

The live communication between the backend (API & Web Sockets) and
front-end (Vue) runs via web sockets, other frequent communication
go through the REST API.

### Development Setup Database 
 - Import the SQL script in your mysql database 

###  Development Setup Backend
 - Open the pom.xml as project in your Intelij 
 - Install all dependencies 
 - Rebuild project 
 - Run localhost:8085 (port can be changed in the resources application.properties)

### Development Setup Frontend
``` bash
# install deps
npm install

# build dist files
npm run build

# serve examples at localhost:8086 (port can be changed in the config index)
npm run dev
```
