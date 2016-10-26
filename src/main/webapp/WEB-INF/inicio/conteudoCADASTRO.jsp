<div class="container">
	<div class="row">
		<div class="col-md-1" style="border-rigth:2px blue solid">
			
		</div>
		<div class="col-md-8">
			<h3>CADASTRO</h3>
			
				<c:if test="${not empty cadastroOK }">
					<div class="alert alert-success">
  						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  						<strong><span class="glyphicon glyphicon-check"></span> OK!</strong> Cadastro Realizado com sucesso!
					</div>
				</c:if>
				<c:if test="${not empty cadastroERRO }">
					<div class="alert alert-danger">
  						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  						<strong>OPS! :(</strong> Desculpe, mas algo de errado occoreu, por favor tente novamente! ;) 
					</div>
				</c:if>	
			
				<p>* Preencha todos os campos obrigatórios, faça o cadastro e desfrute deste sistema</p>
				
		<form action="cadastro" method="post">
			<div class="input-group" style="width:100%">
					<input type="text" class="form-control" name="nome" placeholder="(*OBRIGATÓRIO) Nome Fantasia" required><br><br><br>
					<input type="text" class="form-control" name="cnpj" id="campocnpjcadastro" placeholder="(*OBRIGATÓRIO) CNPJ" required><br><br><br>
					<input type="text" class="form-control" name="endereço" placeholder="(*OBRIGATÓRIO) Endereço (Ex.: Rua Marechal nº9999)" required><br><br><br>
					<input type="text" class="form-control" name="cidade" placeholder="(*OBRIGATÓRIO) Cidade (Ex.: Sata Maria - RS)" required><br><br><br>
					<input type="email" class="form-control" name="email" placeholder="(*OBRIGATÓRIO) E-mail" required>
					(obs.: O e-mail informado servirá para recuperação de senha caso a esqueça!)<br><br>
					<input type="text" class="form-control" name="responsave" placeholder="(*OBRIGATÓRIO) Responsável (Letras de A - Z)" required ><br><br><br>
					<input type="password" class="form-control" name="sennha" placeholder="(*OBRIGATÓRIO) SENHA" required><br><br><br>
					<p> >> Após clicar em cadastrar, <b>AGUARDE</b> até que seus dados sejam cadastrados, você será avisado quando terminar!</p>
					
					<button class="btn btn-primary" type="submit">CADASTRAR <span class="glyphicon glyphicon-edit"></span></button>
			</div>
		
		</form>
		
				
			<br><br>	
				
		</div>
		<%@ include file="apoiadores.jsp" %>
	</div>
</div>




