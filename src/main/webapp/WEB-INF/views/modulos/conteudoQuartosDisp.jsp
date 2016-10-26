<div class="col-md-9"><br>
	<div class="row">
		<div class="col-md-10 text-center">
			<h2 style="font-family: 'Yanone Kaffeesatz', sans-serif;">Quartos Disponíveis (${numeros.quartosdisponiveis})</h2>
		</div>
	</div>
	<br><br>
	<div class="row">
		<div class="col-md-12">
			<div style="overflow: auto; width: 100%; height: 300px;"> 
 				<table class="table table-hover "> 
					<thead>
						<tr>
							<th class="text-center">Quarto</th>
							<th class="text-center">Capacidade</th>
							<th class="text-center">Diária</th>
							<th class="text-center">Descrição</th>
							<th class="text-center">Hospedar</th>
						</tr>
					</thead>
		
					<tbody>
					
						<c:forEach var="x" items="${ver}">
							<tr class="text-center">
								<td class="text-center">${x.nome}</td>
								<td class="text-center">${x.capacidade}</td>
								<td class="text-center">${x.diaria}</td>
								<td class="text-center">${x.descricao}</td>
								<td>
									<a class="btn btn-success" title="IP PARA HOSPEDAR"
										href="hospedar">
										<span class="glyphicon glyphicon-share-alt"></span>
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