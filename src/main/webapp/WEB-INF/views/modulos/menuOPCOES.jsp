<!-- Menu logado lado-esquerdo -->
<div class="col-md-3"><br>
	<div class="btn-group-vertical" role="group" aria-label="...">
  		<button type="submit" form="home" class="btn btn-primary glyphicon glyphicon-home" style="text-align:left"> HOME</button>
  		<button type="submit" form="veraddhospedes" class="btn btn-primary glyphicon glyphicon-user" style="text-align:left"> VER / ADD HÓSPEDES</button>
  		<button type="submit" form="hospedar" class="btn btn-primary glyphicon glyphicon-edit" style="text-align:left"> HOSPEDAR</button>
  		<button type="submit" form="hospedados" class="btn btn-primary glyphicon glyphicon-check" style="text-align:left"> HOSPEDADOS <span class="badge">${numeros.numhospedados}</span></button>
  		<!-- <button type="submit" form="reservas" class="disabled btn btn-primary glyphicon glyphicon-list-alt" style="text-align:left"> RESERVAS <span class="badge">${numeros.reservas}</span></button> -->
  		<button type="submit" form="quartosdisponiveis" class="btn btn-primary glyphicon glyphicon-list-alt" style="text-align:left"> QUARTOS DISPON. <span class="badge">${numeros.quartosdisponiveis}</span></button>
  		<button type="submit" form="lembretes" class="btn btn-primary glyphicon glyphicon-tag" style="text-align:left"> LEMBRETES <span class="badge">${numeros.lembretes}</span> </button>
		
		
		
  		
  		<!-- <button type="submit" form="reservar" class="btn btn-primary glyphicon glyphicon-edit" style="text-align:left"> RESERVAR</button>  -->
	
	</div>
</div>
<form action="home" method="post" id="home"></form>
<form action="hospedados" method="post" id="hospedados"></form>
<form action="#" method="post" id="reservas"></form>
<form action="quartosdisponiveis" method="post" id="quartosdisponiveis"></form>
<form action="lembretes" method="post" id="lembretes"></form>
<form action="veraddhospedes" method="post" id="veraddhospedes"></form>
<form action="hospedar" method="post" id="hospedar"></form>
<form action="reservar" method="post" id="reservar"></form>

