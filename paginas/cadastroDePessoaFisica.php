<?php 
include 'sistema/modelo/PessoaFisica.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	$pessoa = new PessoaFisica();
	$pessoa->fixarNome($_POST['nome']);
	$telefone1 = $_POST['telefone1'];
	$telefone2 = $_POST['telefone2'];
	$email1 = $_POST['email1'];
	$email2 = $_POST['email2'];
	$cpf = $_POST['cpf'];
	$tratamento = $_POST['tratamento'];
	$informacoesExtras = $_POST['informacoesExtras'];
	
	$pessoa = new PessoaFisica();
	$pessoa->cadastrarPessoaFisica($telefone1, $telefone2, $email1, $email2, $cpf, $tratamento, $informacoesExtras);
}
?>

<h1>Cadastro De Pessoa Física</h1>
<form action="" method="post">
	<div class="divisao">
		<label for="nome">Nome:
			<input type="text" name="nome" maxlength="50" placeholder="Nome Do Cliente (obrigatório)" tabindex="1" <?php if (isset($campos['email1'])) echo('value="'.$campos['nome'].'"'); ?> />
		</label>
		<label for="telefone1">Telefone 1:
			<input type="tel" name="telefone1" maxlength="10" placeholder="Telefone (obrigatório)" tabindex="2" <?php if (isset($campos['email1'])) echo('value="'.$campos['telefone1'].'"'); ?> />
		</label>
		<label for="telefone2">Telefone 2:
			<input type="tel" name="telefone2" maxlength="10" placeholder="Telefone (opcional)" tabindex="3" <?php if (isset($campos['email1'])) echo('value="'.$campos['telefone2'].'"'); ?> />
		</label>
		<label for="email1">E-mail 1:
			<input type="email" name="email1" maxlength="64" placeholder="E-mail (opcional)" tabindex="4" <?php if (isset($campos['email1'])) echo('value="'.$campos['email1'].'"'); ?> />
		</label>
		<label for="email2">E-mail 2:
			<input type="email" name="email2" maxlength="64" placeholder="E-mail (opcional)" tabindex="5" <?php if (isset($campos['email2'])) echo('value="'.$campos['email2'].'"'); ?> />
		</label>
		<label for="cpf">CPF:
			<input type="text" name="cpf" maxlength="11" placeholder="CPF (opcional)" tabindex="6" <?php if (isset($campos['cpf'])) echo('value="'.$campos['cpf'].'"'); ?> />
		</label>
		<label for="tratamento">Tratamento:
			<select name="tratamento" tabindex="7">
				<option>Selecione o tratamento (obrigatório)</option>
				<option value="M" <?php if (isset($campos['tratamento']) && $campos['tratamento'] == 'M') echo('selected'); ?>>Sr.</option>
				<option value="F" <?php if (isset($campos['tratamento']) && $campos['tratamento'] == 'F') echo('selected'); ?>>Sra.</option>
			</select>
		</label>
		<label for="informacoesExtras">Informações Extras:
			<textarea name="informacoesExtras" maxlenght="1000" placeholder="Informações Extras (opcional)" tabindex="8" <?php if (isset($campos['email1'])) echo('value="'.$campos['informacoesExtras'].'"'); ?>></textarea>
		</label>
	</div>
	<div class="divisao">
		<input type="submit" value="Cadastrar" tabindex="9" />
	</div> 
</form>
