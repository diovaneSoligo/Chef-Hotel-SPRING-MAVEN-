<div class="col-md-9"><br>

<c:if test="${not empty ver}">
	<div class="row">
		<div class="col-md-10 text-center">
			<h2 style="font-family: 'Yanone Kaffeesatz', sans-serif;">Hospedar <span class="glyphicon glyphicon-edit"></span> </h2>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-5">
			<p>Selecione o Hópede</p>
			<select name="codhospede" form="hospedarhoje" class="form-control">
					<c:forEach var="hospede" items="${ver2}">
			 			<option value="${hospede.codigo}">(${hospede.nome})-(CPF: ${hospede.cpf})</option>
			 		</c:forEach>
			</select>
		</div>
		<div class="col-md-7">
			<p>Selecione um dos quartos livres</p>
			
			<select name="codquarto" form="hospedarhoje" class="form-control">
					<c:forEach var="quarto" items="${ver}">
			 			<option value="${quarto.codigo}">(${quarto.nome})-(Diária R$ ${quarto.diaria})-(Cap:${quarto.capacidade})-(${quarto.descricao})</option>
			 		</c:forEach>
			</select>
		</div>
	</div><br>
	<div class="row">
		<div class="col-md-3">
			<p>Nº Acompanhantes</p>
			<input type="text" form="hospedarhoje" name="numeroacompanhanteshospedagem" class="form-control" placeholder="Numero de acompanhantes" pattern="[0-9]+$" required="required">
		</div>
		<div class="col-md-3">
			<p>Data Saída (Check-out):</p>
			<input type="text" id="saida" form="hospedarhoje" name="saidahospedagem" class="form-control" placeholder="Data de Saída" required="required">
		</div>
		<div class="col-md-3">
			<form action="hospedarok" id="hospedarhoje" method="post" style="margin-top:30px">
				<button class="btn btn-success" type="submit"><small>HOSPEDAR </small><span class="glyphicon glyphicon-edit"></span></button>
			</form>
		</div>
	</div>
	
	

</c:if>

<c:if test="${empty ver}">
	<div class="row">
		<div class="col-md-10 text-center">
			<h2 style="font-family: 'Yanone Kaffeesatz', sans-serif;">Hospedados (${numeros.numhospedados} pessoas)</h2>
		</div>
	</div>
	<br><br>
	<div class="row">
		<div class="col-md-5">
			<div class="input-group">
				<form action="hospedadosbusca" method="post">
					<span class="input-group-btn">
			      		<input type="text" name="nomehospede" class="form-control" placeholder="Buscar por NOME">
			      		<button class="btn btn-primary" type="submit">BUSCAR!</button>
			      	</span>
		    	</form>
		    </div>
		</div>
		<div class="col-md-5 col-md-offset-1">
			<div class="input-group">
				<form action="hospedar" method="post">
					<span class="input-group-btn">
			      		<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-log-in"></span> HOSPEDAR</button>
			      	</span>
		    	</form>
		    </div>
		</div>
	</div>
	<br><br>
	<div class="row">
		<div class="col-md-12">
			<div style="overflow: auto; width: 100%; height: 300px;"> 
 				<table class="table table-hover "> 
					<thead>
						<tr>
							<th class="text-center">Hóspede</th>
							<th class="text-center">CPF</th>
							<th class="text-center">Quarto</th>
							<th class="text-center">Acompanhantes</th>
							<th class="text-center">Entrada</th>
							<th class="text-center">Saída</th>
							<th class="text-center">Check-Out?</th>
						</tr>
					</thead>
		
					<tbody>
					
						<c:forEach var="x" items="${hospededados}">
							<tr class="text-center">
								<td class="text-center">${x.nomehospede}</td>
								<td class="text-center">${x.cpfhospede}</td>
								<td class="text-center">${x.nomequarto}</td>
								<td class="text-center">${x.numeroacompanhanteshospedagem}</td>
								<td class="text-center">${x.entradahospedagem}</td>
								<td class="text-center">${x.saidahospedagem}</td>
								<td>
									<a class="btn btn-danger" title="REALIZAR CHECK-OUT?"
										href="fazercheckoutanti?codhospedagem=${x.codhospedagem}&codquarto=${x.codquarto}">
										<span class="glyphicon glyphicon-warning-sign"></span>
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