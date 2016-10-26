<div class="col-md-9">
	<div class="row">
		<div class="col-md-10 text-center">
			<h2 style="font-family: 'Yanone Kaffeesatz', sans-serif;">CHECK-IN a ser feito hoje ${numeros.data} - (${numeros.entrada} Reservas)</h2>
		</div>
	</div>
	<br><br>
	<div class="row">
		<div class="col-md-5 col-md-offset-5">
			<div class="input-group">
				<form action="spancheckinbusca" method="post">
					<span class="input-group-btn">
			      		<input type="text" name="nomehospede" class="form-control" placeholder="Buscar por NOME">
			      		<button class="btn btn-primary" type="submit">BUSCAR!</button>
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
							<th class="text-center">Check-In</th>
						</tr>
					</thead>
		
					<tbody>
					
						<c:forEach var="x" items="${xin}">
							<tr class="text-center">
								<td class="text-center">${x.nomehospede}</td>
								<td class="text-center">${x.cpfhospede}</td>
								<td class="text-center">${x.nomequarto}</td>
								<td class="text-center">${x.numeroacompanhanteshospedagem}</td>
								<td class="text-center">${x.entradahospedagem}</td>
								<td class="text-center">${x.saidahospedagem}</td>
								<td>
									<a class="btn btn-success" title="REALIZAR CHECK-IN"
										href="fazercheckin?codhospedagem=${x.codhospedagem}&codquarto=${x.codquarto}">
										<span class="glyphicon glyphicon-check"></span>
									</a>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>