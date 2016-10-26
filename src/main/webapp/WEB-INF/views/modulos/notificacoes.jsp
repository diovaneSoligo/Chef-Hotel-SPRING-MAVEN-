				<c:if test="${not empty atualizaERRO }">
					<div class="alert alert-danger">
  						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  						<strong><span class="glyphicon glyphicon-alert"></span> OPS!</strong> ERRO AO ATUALIZAR AS INFORMAÇÕES!
					</div>
				</c:if>
				
				<c:if test="${not empty notificacaoOK }">
					<div class="alert alert-success">
  						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  						<strong><span class="glyphicon glyphicon-thumbs-up"></span></strong> ${notificacaoOK}
					</div>
				</c:if>
				<c:if test="${not empty notificacaoERRO }">
					<div class="alert alert-danger">
  						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  						<strong><span class="glyphicon glyphicon-alert"></span> OPS!</strong> ${notificacaoERRO}
					</div>
				</c:if>