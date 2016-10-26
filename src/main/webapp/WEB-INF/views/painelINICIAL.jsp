<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<%@ include file="modulos/head"%>
</head>
<body>
	<%@ include file="modulos/topo.jsp" %>
	
	<%@ include file="modulos/barra-notificacoes.jsp" %>
	
	<div class="container">
		<div class="row">
			
			<%@ include file="modulos/menuOPCOES.jsp" %>
			
			<%@ include file="modulos/conteudoInicial.jsp" %>
			
		</div>
	</div>
	
	<%@ include file="modulos/scripts"%>
</body>
</html>