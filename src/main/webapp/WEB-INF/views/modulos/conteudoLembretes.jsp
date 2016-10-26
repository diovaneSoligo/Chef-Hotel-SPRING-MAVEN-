
<div class="col-md-9" style="margin-top: 20px;margin-bottom: 40px;">
<h1 style="font-family: 'Yanone Kaffeesatz', sans-serif;" class="text-center">Mural de Lembretes!</h1>

<c:if test="${not empty lembretealterar }">
	<div class="row">
		<div class="col-md-12">
		<h3 style="font-family: 'Yanone Kaffeesatz', sans-serif;">Editar Lembrete do dia --> ${lembretealterar.criado}</h3>
		</div>
	</div>
	<div class="row">
		<form action="atualizalembrete?codigo=${lembretealterar.codigo}" method="post" id="atua">
				<div class="input-group" style="width:100%">
						<input type="text" class="form-control" value="${lembretealterar.titulo}" name="titulo" placeholder="TÍTULO (maximo de 49 caracteres)" maxlength="49" required><br><br><br>
						<input class="form-control" value="${lembretealterar.descricao}" name="descricao" placeholder="DESCRIÇÃO (maximo de 400 caracteres)" maxlength="400" required></textarea>
				</div>
		</form>
		<form action="lembretes" method="post" id="cancela"></form>
	<br><br>
		<button class="btn btn-primary" type="submit" form="atua">SALVAR <span class="glyphicon glyphicon-ok"></span></button>
		<button class="btn  btn-danger" type="submit" form="cancela">CANCELAR <span class="glyphicon glyphicon-remove"></span></button>
	</div>
</div>
</c:if>

<c:if test="${empty lembretealterar }">
<br>
	<div class="col-md-12">
		<h2>Seus Lembretes <span class="badge">${numeros.lembretes}</span> <span class="glyphicon glyphicon-hand-down"></span></h2>
	</div>
<div style="overflow: auto; width: 100%; height: 300px;"> 
 <table class="table table-hover "> 
				<thead>
					<tr>
						<th class="text-center">Título</th>
						<th class="text-center">Descrição</th>
						<th class="text-center">Criado em</th>
						<th class="text-center">Deletar</th>
						<th class="text-center">Editar</th>
					</tr>
		
				</thead>
		
				<tbody>
					<c:forEach var="lembrete" items="${lembretes}">
						<tr class="text-center">
							<td><b>${lembrete.titulo}</b></td>
							<td class="text-justify">${lembrete.descricao}</td>
							<td class="table-bordered">${lembrete.criado}</td>
							<td>
								<a class="btn btn-danger"
									href="apagarlembrete?codigo=${lembrete.codigo}">
									<span class="glyphicon glyphicon-trash"> Deletar</span>
								</a>
							</td>
							<td>
								<a class="btn btn-primary"
									href="editarlembrete?codigo=${lembrete.codigo}">
									<span class="glyphicon glyphicon-edit "> Editar</span>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row" style="border-top: 2px blue solid;">
	<div class="col-md-12">
	<h3 style="font-family: 'Yanone Kaffeesatz', sans-serif;">Adicionar um lembrete ao mural</h3>
	</div>
</div>
	<div class="row" style="border-bottom: 2px blue solid;">
		<div class="col-md-12">
			<form action="adicionalembrete" method="post" id="adiciona">
				<div class="input-group" style="width:100%">
						<input type="text" class="form-control" name="titulo" placeholder="TÍTULO (maximo de 49 caracteres)" maxlength="49" required><br><br><br>
						<textarea id="txtArea" rows="3" cols="70" class="form-control" name="descricao" placeholder="DESCRIÇÃO (maximo de 400 caracteres)"maxlength="400" required></textarea>
				</div>
			</form>
			<br>
			<button style="margin-bottom: 10px" class="btn btn-primary" form="adiciona" type="submit">ADICIONAR LEMBRETE AO MURAL <span class="glyphicon glyphicon-pencil"></span></button>
		</div>
	</div>
</c:if>
