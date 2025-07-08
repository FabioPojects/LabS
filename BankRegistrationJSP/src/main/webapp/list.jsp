<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Bank" %>
<html>
<head>
    <title>Lista de Bancos</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/listagem.css" />
</head>
<body>
<div class="container">
<h2>Lista de Bancos</h2>
<a href="<%= request.getContextPath() %>/BankServlet/new" class="btn-new">Adicionar Novo Banco</a>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Data de Nascimento</th>
            <th>Ações</th>
        </tr>
    </thead>
    <tbody>
    <%
        List<Bank> listBank = (List<Bank>) request.getAttribute("listBank");
        if (listBank != null) {
            for (Bank bank : listBank) {
    %>
        <tr>
            <td><%= bank.getId() %></td>
            <td><%= bank.getName() %></td>
            <td><%= bank.getBirthDate() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/BankServlet/edit?id=<%= bank.getId() %>" class="btn-edit">Editar</a>
                <a href="<%= request.getContextPath() %>/BankServlet/delete?id=<%= bank.getId() %>" class="btn-delete"
                   onclick="return confirm('Tem certeza que deseja deletar?');">Deletar</a>
            </td>
        </tr>
    <%      }
        }
    %>
    </tbody>
</table>
</div>
</body>
</html>
