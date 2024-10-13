<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.vehicle.model.Vehicle" %>
<html>
<head>
    <title>Vehicle Catalogue</title>
</head>
<body>
    <h1>Vehicle Catalogue</h1>
    <%List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehiclesList");%><br>
    <%=vehicles%>
        <br>
    <% for (Vehicle vehicle : vehicles) {%>
        <%= vehicle.getVehicle_plat() %>
    <% } %>
    
    
</body>
</html>
