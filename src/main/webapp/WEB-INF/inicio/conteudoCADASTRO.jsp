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
			
				<p>* Preencha todos os campos obrigat�rios, fa�a o cadastro e desfrute deste sistema</p>
				
		<form action="cadastro" method="post">
			<div class="input-group" style="width:100%">
					<input type="text" class="form-control" name="nome" placeholder="(*OBRIGAT�RIO) Nome Fantasia" required><br><br><br>
					<input type="text" class="form-control" name="cnpj" id="campocnpjcadastro" placeholder="(*OBRIGAT�RIO) CNPJ" required><br><br><br>
					<input type="text" class="form-control" name="endere�o" placeholder="(*OBRIGAT�RIO) Endere�o (Ex.: Rua Marechal n�9999)" required><br><br><br>
					<input type="text" class="form-control" name="cidade" placeholder="(*OBRIGAT�RIO) Cidade (Ex.: Sata Maria - RS)" required><br><br><br>
					<input type="email" class="form-control" name="email" placeholder="(*OBRIGAT�RIO) E-mail" required>
					(obs.: O e-mail informado servir� para recupera��o de senha caso a esque�a!)<br><br>
					<input type="text" class="form-control" name="responsave" placeholder="(*OBRIGAT�RIO) Respons�vel (Letras de A - Z)" required ><br><br><br>
					<input type="password" class="form-control" name="sennha" placeholder="(*OBRIGAT�RIO) SENHA" required><br><br><br>
					<p> >> Ap�s clicar em cadastrar, <b>AGUARDE</b> at� que seus dados sejam cadastrados, voc� ser� avisado quando terminar!</p>
					
					<button class="btn btn-primary" type="submit">CADASTRAR <span class="glyphicon glyphicon-edit"></span></button>
			</div>
		
		</form>
		
				
			<br><br>	
				
		</div>
		<%@ include file="apoiadores.jsp" %>
	</div>
</div>




