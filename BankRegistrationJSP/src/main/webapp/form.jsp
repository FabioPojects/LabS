<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Bank" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <title>Cadastro de Banco</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/form.css" />
</head>
<body>
<div class="container">
<%
    Bank bank = (Bank) request.getAttribute("bank");
    boolean isEdit = bank != null;
    String action = isEdit ? "update" : "insert";
    String id = isEdit ? String.valueOf(bank.getId()) : "";
    String name = isEdit ? bank.getName() : "";
    String birthDateStr = "";
    if (isEdit && bank.getBirthDate() != null) {
        birthDateStr = bank.getBirthDate().toString();
    }
%>

<h2><%= isEdit ? "Editar Banco" : "Novo Banco" %></h2>

<form action="<%= request.getContextPath() %>/BankServlet/<%= action %>" method="post">
    <input type="hidden" name="id" value="<%= id %>" />
    <label>Nome:</label><br/>
    <input type="text" name="name" value="<%= name %>" required/><br/>
    
    <label>Data de Nascimento:</label><br/>
    <input type="date" name="birthDate" value="<%= birthDateStr %>" required />
    
    <input type="submit" value="<%= isEdit ? "Atualizar" : "Cadastrar" %>"/>
</form>

<a href="BankServlet" class="btn-new">Voltar para lista</a>
</div>
</body>
</html>
