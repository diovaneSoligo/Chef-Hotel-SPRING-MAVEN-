<div class="col-md-9"><br>
	<div class="row">
		<div class="col-md-12 text-center">
			<h1 style="font-family: 'Yanone Kaffeesatz', sans-serif;">Configuração de Quartos</h1>
		</div>
	</div>
<c:if test="${not empty ver}">

	<div class="row">
		<div class="col-md-12">
			<h3 style="font-family: 'Yanone Kaffeesatz', sans-serif;">Dados do Quarto</h3>
		</div>
	</div>	<br>
	<div class="row">
		<div class="col-md-11">
	        <form action="atualizaquarto?codigo=${ver.codigo}" method="post" id="quarto">
				<div class="input-group" style="width:100%">
					Nome do Quarto:
					<input type="text" class="form-control" value="${ver.nome}" name="nome" placeholder="Nome Completo" required><br><br><br>
					Capacidade:
					<input type="text" class="form-control" value="${ver.capacidade}" name="capacidade" placeholder="CPF" required pattern="[0-9]+$"><br><br><br>
					Diária:
					<input type="text" class="form-control" value="${ver.diaria}" name="diaria" placeholder="RG" required pattern="[0-9]+$"><br><br><br>
					Descrição:
					<input type="text" class="form-control" value="${ver.descricao}" name="descricao"  placeholder="Descrição (OPCIONAL)" ><br><br><br>
					
				</div>
			</form>
			
	      <div class="modal-footer">
	        <button type="submit" form="quarto" class="btn btn-primary">SALVAR ALTERAÇÕES <span class="glyphicon glyphicon-refresh"></span></button>
	        <button type="submit" form="volta" class="btn btn-primary" data-dismiss="modal">CANCELAR E VOLTAR <span class="glyphicon glyphicon-arrow-left"></span></button>
	        <button type="submit" form="exclui" class="btn btn-danger" data-dismiss="modal">DELETAR QUARTO <span class="glyphicon glyphicon-trash"></span> </button>
	      
	      	<form action="configuracaodequarto" method="post" id="volta"></form>
	      	<form action="exluirquarto?codigo=${ver.codigo}" method="post" id="exclui"></form>
	      </div>
	   </div>
	</div>
</c:if>	
	<br><br>
	
<c:if test="${empty ver}">	
	<div class="row">
		<div class="col-md-5">
			<div class="input-group">
				<form action="buscaquarto" method="post">
					<span class="input-group-btn">
			      		<input type="text" name="nome" class="form-control" placeholder="NOME DO QUARTO">
			      		<button class="btn btn-primary" type="submit">BUSCAR!</button>
			      	</span>
		    	</form>
		    </div>
		</div>
		<div class="col-md-1">
			
		</div>
		<div class="col-md-2">
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
			ADICIONAR UM NOVO QUARTO</button>
		</div>
	</div>
	
	<br><br>
	
	<div class="row">
		<div class="col-md-12">
			<div style="overflow: auto; width: 100%; height: 300px;"> 
 				<table class="table table-hover "> 
					<thead>
						<tr>
							<th class="text-center">Nome Quarto</th>
							<th class="text-center">Capacidade</th>
							<th class="text-center">Diária</th>
							<th class="text-center">Descrição</th>
							<th class="text-center">Editar</th>
						</tr>
					</thead>
		
					<tbody>
						<c:forEach var="quarto" items="${quartos}">
							<tr class="text-center">
								<td class="text-center">${quarto.nome}</td>
								<td class="text-center">${quarto.capacidade}</td>
								<td class="text-center">R$ ${quarto.diaria}</td>
								<td class="text-center">${quarto.descricao}</td>
								
								<td>
									<a class="btn btn-primary"
										href="verquarto?codigo=${quarto.codigo}">
										<span class="glyphicon glyphicon-eye-open"></span>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</c:if>

</div>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Adicionar um novo Quarto</h4>
	      </div>
      
	      <div class="modal-body">
	        <form action="cadastroquarto" method="post" id="modaladicionaquarto">
				<div class="input-group" style="width:100%">
					<input type="text" class="form-control" name="nome" placeholder="Nome do quarto (Ex.: quarto 103)" required><br><br><br>
					<input type="text" class="form-control" name="capacidade" placeholder="Capacidade do quarto (EX.: 4)" required pattern="[0-9]+$"><br><br><br>
					<input type="text" class="form-control" name="diaria" placeholder="Diaria (Ex.: 180) 'apenas numeros redondos'" required pattern="[0-9]+$"><br><br><br>
					<input type="text" class="form-control" name="descricao" placeholder="Breve descrição (OPCIONAL)"><br><br><br>
				</div>
			</form>
	      </div>
      
	      <div class="modal-footer">
	        <button type="submit" form="modaladicionaquarto" class="btn btn-primary">ADICIONAR</button>
	        <button type="button" class="btn btn-danger" data-dismiss="modal">CANCELAR</button>
	      </div>
    </div>

  </div>
</div>