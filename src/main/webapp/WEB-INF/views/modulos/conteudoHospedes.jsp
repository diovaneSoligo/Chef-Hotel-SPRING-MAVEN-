<div class="col-md-9"><br>
	<div class="row">
		<div class="col-md-12 text-center">
			<h1 style="font-family: 'Yanone Kaffeesatz', sans-serif;">Hóspedes</h1>
		</div>
	</div>
<c:if test="${not empty ver}">
	<div class="row">
		<div class="col-md-12">
			<h3 style="font-family: 'Yanone Kaffeesatz', sans-serif;"><span class="glyphicon glyphicon-cog"></span> Dados do hóspede  </h3>
		</div>
	</div>	<br>
	<div class="row">
		<div class="col-md-11">
	        <form action="atualizahospede?codigo=${ver.codigo}" method="post" id="hospede">
				<div class="input-group" style="width:100%">
					Nome:
					<input type="text" class="form-control" value="${ver.nome}" name="nome" placeholder="Nome Completo" required><br><br><br>
					CPF:
					<input type="text" class="form-control" value="${ver.cpf}" name="cpf" id="campocpf" placeholder="CPF" required><br><br><br>
					RG:
					<input type="text" class="form-control" value="${ver.rg}" name="rg" id="camporg" placeholder="RG" required><br><br><br>
					Data de nascimento:
					<input type="text" class="form-control" value="${ver.nascimento}" name="nascimento" id="camponascimento2" placeholder="Data de nascimento" required><br><br><br>
					E-mail:
					<input type="email" class="form-control" value="${ver.email}" name="email" placeholder="E-mail" required><br><br><br>
					Telefone:
					<input type="text" class="form-control" value="${ver.telefone}" name="telefone" placeholder="Telefone para contato XX XXXX-XXXX" required><br><br><br>
					Telefone extra:
					<input type="text" class="form-control" value="${ver.telefoneextra}" name="telefoneextra" placeholder="Telefone extra para contato (OPCIONAL)" ><br><br><br>
				</div>
			</form>
			
	      <div class="modal-footer">
	        <button type="submit" form="hospede" class="btn btn-primary">SALVAR ALTERAÇÕES <span class="glyphicon glyphicon-refresh"></span></button>
	        <button type="submit" form="volta" class="btn btn-primary" data-dismiss="modal">CANCELAR E VOLTAR <span class="glyphicon glyphicon-arrow-left"></span></button>
	        <button type="submit" form="exclui" class="btn btn-danger" data-dismiss="modal">DELETAR HÓSPEDE <span class="glyphicon glyphicon-trash"></span> </button>
	      
	      	<form action="veraddhospedes" method="post" id="volta"></form>
	      	<form action="exluirhospede?codigo=${ver.codigo}" method="post" id="exclui"></form>
	      </div>
	   </div>
	</div>
</c:if>	
	<br><br>
<c:if test="${empty ver}">	
	<div class="row">
		<div class="col-md-5">
			<div class="input-group">
				<form action="buscahospede" method="post">
					<span class="input-group-btn">
			      		<input type="text" name="nome" class="form-control" placeholder="Buscar por NOME">
			      		<button class="btn btn-primary" type="submit">BUSCAR!</button>
			      	</span>
		    	</form>
		    </div>
		</div>
		<div class="col-md-1">
			
		</div>
		<div class="col-md-2">
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
			ADICIONAR HÓSPEDE</button>
		</div>
	</div>
	
	<br><br>
	
	<div class="row">
		<div class="col-md-12">
			<div style="overflow: auto; width: 100%; height: 300px;"> 
 				<table class="table table-hover "> 
					<thead>
						<tr>
							<th class="text-center">Nome</th>
							<th class="text-center">CPF</th>
							<th class="text-center">Nascimento</th>
							<th class="text-center">E-mail</th>
							<th class="text-center">Fone</th>
							<th class="text-center">Ver/Editar</th>
						</tr>
					</thead>
		
					<tbody>
						<c:forEach var="hospede" items="${hospedes}">
							<tr class="text-center">
								<td class="text-center">${hospede.nome}</td>
								<td class="text-center">${hospede.cpf}</td>
								<td class="text-center">${hospede.nascimento}</td>
								<td class="text-center">${hospede.email}</td>
								<td class="text-center">${hospede.telefone}</td>
								
								<td>
									<a class="btn btn-primary"
										href="verhospede?codigo=${hospede.codigo}">
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
	        <h4 class="modal-title">Adicionar um novo Hóspede <span class="glyphicon glyphicon-user"></span> </h4>
	      </div>
      
	      <div class="modal-body">
	        <form action="cadastrohospede" method="post" id="modaladicionahospede">
				<div class="input-group" style="width:100%">
					<input type="text" class="form-control" name="nome" placeholder="Nome Completo" required><br><br><br>
					<input type="text" class="form-control" name="cpf" id="campocpf" placeholder="CPF" required><br><br><br>
					<input type="text" class="form-control" name="rg" id="camporg" placeholder="RG" required><br><br><br>
					<input type="text" class="form-control" name="nascimento" id="camponascimento" placeholder="Data de nascimento" required><br><br><br>
					<input type="email" class="form-control" name="email" placeholder="E-mail" required><br><br><br>
					<input type="text" class="form-control" name="telefone" placeholder="Telefone para contato XX XXXX-XXXX" required><br><br><br>
					<input type="text" class="form-control" name="telefoneextra" placeholder="Telefone extra para contato (OPCIONAL)" ><br><br><br>
				</div>
			</form>
	      </div>
      
	      <div class="modal-footer">
	        <button type="submit" form="modaladicionahospede" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> ADICIONAR</button>
	        <button type="button" class="btn btn-danger" data-dismiss="modal">CANCELAR</button>
	      </div>
    </div>

  </div>
</div>

