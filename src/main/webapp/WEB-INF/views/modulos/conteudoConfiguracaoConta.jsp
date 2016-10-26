
    <div class="col-md-7" style="margin-bottom: 75px;">  
      <form action="atualizaConta" method="post">
	      <h4 class="text-center">Configuração de conta</h4>
	      	<div class="row">
	      		<div class="col-md-12">
	      			<input type="text" class="form-control" name="nome" placeholder="Nome Fantasia" value="${usuarioLogado.nome}" required><br>
	      			<input type="text" class="form-control" name="endereço" placeholder="Endereço" value="${usuarioLogado.endereço}" required><br>
	      			<input type="text" class="form-control" name="cidade" placeholder="Cidade" value="${usuarioLogado.cidade}" required><br>
	      			<input type="text" class="form-control" name="email" placeholder="E-mail (para recuperação de conta)" value="${usuarioLogado.email}" required>
	      			<p>* Caso esqueça sua senha, você poderá recupera-la atravez do e-mail informado!</p><br>
	      			<input type="text" class="form-control" name="responsave" placeholder="Responsável" value="${usuarioLogado.responsave}" required><br>
	      			<input type="password" class="form-control" name="sennha" placeholder="Senha" value="${usuarioLogado.sennha}" required>
	      		</div>
	      	</div>
			<div class="row">
				<div class="col-md-12">
					<br><p>*Qualquer alteração realizada, implicará em realizar um novo login!</p><br>
				</div>
			</div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-refresh"></span> SALVAR / ATUALIZAR</button>
	      
	      </div>
      </form>
</div>
