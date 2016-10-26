	
	<div style="background-image:url('img/madeira.jpg');border-bottom: 2px solid rgb(0, 0, 0);border-top: 1px solid rgb(0, 0, 0);">
		<div class="container">
			<div class="row">
				<div class="col-md-1">
				<img src="img/logo.png" alt="logo" height="60" width="60" style="margin-top: 2px;">
				</div>
				<div class="col-md-6">
					<h1 style="margin-top: 13px;color: rgba(255, 255, 255, 0.74);">
						<b>Chef Hotel.com</b>
							<small style="color: rgb(39, 39, 39);font-size: 11pt;margin-left: 10px;">
								<b>Administre seu hotel com um click</b>
							</small>
					</h1>
				</div>
				
				<div class="col-md-5">
					<div class="row" style="margin-top:5px">
						<form action="logar" method="post">
	                        <div class="input-group">
									<input type="text" name="cnpj" id="campocnpj" placeholder="CNPJ" required>
									<input type="password" id="sennha" name="sennha" placeholder="Senha" required>
										 
							<button class="btn-xs btn-primary" type="submit" name="opcao">Logar <span class="glyphicon glyphicon-log-in"></span></button>
										
							</div>
	                   </form>
	                   
	                   <div style="margin-top:7px">
		                   <b><a href="#" data-toggle="modal" data-target="#myModal" style="color:#fff;margin-left:35%">
		                   		<span class="glyphicon glyphicon-cog"></span>
		                   		RECUPERAR CONTA!
		                   		<span class="glyphicon glyphicon-refresh"></span>
		                   	</a></b>
	                   </div>
					</div>
					
                </div>   
			</div>
			
		</div>
		
	</div>
	
	<!-- SPAN ERRO DE LOGIN -->
		<c:if test="${not empty logERRO }">	
			<SCRIPT>
				<!--
					window.alert("CNPJ ou SENHA inválidos, caso o problema continue, tente recuperar sua conta")
				// -->
			</SCRIPT>
		</c:if>
		
		<c:if test="${not empty recuperaOK }">	
			<SCRIPT>
				<!--
					window.alert("Por favor, Verifique seu e-mail.")
				// -->
			</SCRIPT>
		</c:if>
		
		<c:if test="${not empty recuperaERRO }">	
			<SCRIPT>
				<!--
					window.alert("OPS! Não foi possível recuperar sua conta, problemas na rede, tente mais tarde.")
				// -->
			</SCRIPT>
		</c:if>
		
	<!-- MODAL RECUPERA CONTA -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">ChefHotel.com</h4>
      </div>
      
      <form action="recupera">
	      <div class="modal-body">
	      <h4 class="text-center">Recuperação de conta</h4>
	      	<div class="row">
	      		<div class="col-md-6">
	      			<input type="text" class="form-control" name="cnpj" id="campocnpjrecupera" placeholder="CNPJ" required>
	      		</div>
	      		<div class="col-md-6">
	      			<p class="text-justify">Insira o CNPJ do estabelecimento cadastrado, 
	      			você receberá um e-mail com suas informações no e-mail cadastrao no sistema!</p>
	      		</div>
	      	</div>
	
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">RECUPERAR</button>
	      </div>
      </form>
      
    </div>
  </div>
</div>
	