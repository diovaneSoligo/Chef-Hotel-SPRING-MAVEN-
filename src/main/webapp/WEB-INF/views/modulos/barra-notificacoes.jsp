<div class="container">
	<div class="row">
		<div class="col-md-7">
			<%@ include file="notificacoes.jsp" %>
		</div>
		<div class="col-md-1"style="margin-top:20px; margin-bottom: 20px"title="DATA DE HOJE">
			<class="" style="color:green;">
				<span class="badge">${numeros.data}</span>
			</class>
		</div>
		<div class="col-md-1"style="margin-top:20px; margin-bottom: 20px"title="(CHECKOUT) HOSPEDES QUE SAEM HOJE">
			<a href="spancheckout" style="color:red">
				<span class="badge">${numeros.saida}</span>
				<span class="glyphicon glyphicon-flag"></span> 
			</a>
		</div>
		<div class="col-md-1"style="margin-top:20px; margin-bottom: 20px"title="(CHECKIN DE RESERVAS) ENTRAM HOJE" >
			<!-- <a href="spancheckin" style="color:blue;">
				<span class="badge">${numeros.entrada}</span> 
				<span class="glyphicon glyphicon-check"></span> 
			</a> -->
		</div>
		
		<div class="col-md-2" style="border-right: 2px solid blue;border-radius: 100px;">
		<ul class="nav navbar-nav navbar-right ">
        
        <li class="dropdown">
          	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="background-color: rgba(255, 255, 255, 0);">
          		<span class="glyphicon glyphicon-cog"></span>
          		Opções
          		<span class="caret"></span>
          	</a>
	          <ul class="dropdown-menu" style="background-color: rgba(26, 158, 201);">
	            <li><a href="configuracaodeconta"><span class="glyphicon glyphicon-wrench"></span> Config. de conta</a></li>
	            <li><a href="configuracaodequarto"><span class="glyphicon glyphicon-wrench"></span> Conf. Quartos</a></li>
	            <li><a href="contato"><span class="glyphicon glyphicon-envelope"></span> Contato</a></li>
	            <li><a href="sobre"><span class="glyphicon glyphicon-question-sign"></span> Sobre</a></li>
	            
	            <li role="separator" class="divider"></li>
	            <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Sair</a></li>
	          </ul>
        </li>
      	</ul>
		</div>
	</div>
</div>