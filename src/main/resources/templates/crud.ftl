<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Cidades</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>
        <nav class="navbar navbar-expand-sm bg-dark" >
            <ul class="navbar-nav ml-auto" border-right:2px>
                <li class="nav-item" border-right=2px>
                    <a 
                        href="/logout" 
                        class="nav-link btn btn-secondary"
                        >Sair da aplicação</a>
                </li>
            </ul>
        </nav>

<#-- ESTADO-->

    <div class="container-estado">
        <div class="container-fluid">    
            <div class="jumbotron mt-5">
                <h1>GERENCIAMENTO DE ESTADOS</h1>
                <p>UM CRUD PARA CRIAR, ALTERAR, EXCLUIR E LISTAR ESTADOS</p>
                <p>Adicione primeiro os Estados nesta tela e  após adicione as Cidades na tela abaixo.</p>
            </div>

                <#if estadoAtual??>
                    <form action="/alterarEstado" method="POST" class="needs-validation" novalidate>
                    <input type="hidden" name="nomeAtual" value="${(estadoAtual.nome)!}"/>
                    <input type="hidden" name="siglaAtual" value="${(estadoAtual.sigla)!}"/>
                <#else>
                    <form action="/criarEstado" method="POST" class="needs-validation" novalidate>
                </#if>
            
                <div class="form-group">
                    <label for="nome">Estado:</label>
                    <input 
                        value="${(estadoAtual.nome)!}${nomeInformado!}" 
                        name="nome" 
                        type="text" 
                        class="form-control ${(nome??)?then('is-invalid', '')}" 
                        placeholder="Informe o nome do estado" 
                        id="nome">
                    
                    <div class="invalid-feedback">
                        ${nome!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="estado">Estado:</label>
                    <input 
                        value="${(estadoAtual.sigla)!}${siglaInformado!}" 
                        name="sigla" 
                        type="text" 
                        class="form-control ${(sigla??)?then('is-invalid', '')}" 
                        placeholder="Informe o sigla ao qual a estado pertence"
                        id="sigla">
                    
                    <div class="invalid-feedback">
                        ${sigla!}
                    </div>
                </div>

                <#if estadoAtual??>
                    <button type="submit" class="btn btn-warning">CONCLUIR ALTERAÇÃO</button>
                <#else>
                    <button type="submit" class="btn btn-primary">CRIAR</button>
                </#if>
                
            </form>
            <table class="table table-striped table-hover mt-5">
                <thead class="thead-dark">
                    <tr>
                        <th>Nome</th>
                        <th>Sigla</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <#list listaEstados as estado >
                        <tr>
                            <td>${estado.nome}</td>
                            <td>${estado.sigla}</td>
                            <td>
                                <div class="d-flex d-justify-content-center">
                                <a href="/preparaAlterarEstado?nome=${estado.nome}&sigla=${estado.sigla}" class="btn btn-warning mr-3">ALTERAR</a>
                                <a href="/excluirEstado?nome=${estado.nome}&sigla=${estado.sigla}" class="btn btn-danger">EXCLUIR</a>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>


    <#-- CIDADE-->

    <div class="container-cidade>
        <div class="container-fluid">      
            <div class="jumbotron mt-5">
                <h1>GERENCIAMENTO DE CIDADES</h1>
                <p>UM CRUD PARA CRIAR, ALTERAR, EXCLUIR E LISTAR CIDADES</p>
            </div>

                <#if cidadeAtual??>
                    <form action="/alterarCidade" method="POST" class="needs-validation" novalidate>
                    <input type="hidden" name="nomeAtual" value="${(cidadeAtual.nome)!}"/>
                    <input type="hidden" name="estadoAtual" value="${(cidadeAtual.estado)!}"/>
                <#else>
                    <form action="/criarCidade" method="POST" class="needs-validation" novalidate>
                </#if>
            
                <div class="form-group">
                    <label for="nome">Cidade:</label>
                    <input 
                        value="${(cidadeAtual.nome)!}${nomeInformado!}" 
                        name="nome" 
                        type="text" 
                        class="form-control ${(nome??)?then('is-invalid', '')}" 
                        placeholder="Informe o nome da cidade" 
                        id="nome">
                    
                    <div class="invalid-feedback">
                        ${nome!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="estado">Estado:</label>
                    <select name="estado">
                        <option value="??">Selecione o Estado disponiveis:</option>
                        <#list listaEstados as estado>
                        <option value=${(estado.sigla)!}>${estado.nome}</option>
                        </#list>
                    </select> 
                <#--
                    <input 
                        value="${(cidadeAtual.estado)!}${estadoInformado!}" 
                        name="estado" 
                        type="text" 
                        class="form-control ${(estado??)?then('is-invalid', '')}" 
                        placeholder="Informe o estado ao qual a cidade pertence"
                        id="estado">
                    -->
                    <#--
                    <select name="estado"> 
                        <option value="estado">Selecione o Estado</option> 
                        <option value="AC">Acre</option> 
                        <option value="AL">Alagoas</option> 
                        <option value="AM">Amazonas</option> 
                        <option value="AP">Amapá</option> 
                        <option value="BA">Bahia</option> 
                        <option value="CE">Ceará</option> 
                        <option value="DF">Distrito Federal</option> 
                        <option value="ES">Espírito Santo</option> 
                        <option value="Go">Goiás</option> 
                        <option value="MA">Maranhão</option> 
                        <option value="MT">Mato Grosso</option> 
                        <option value="MS">Mato Grosso do Sul</option> 
                        <option value="MG">Minas Gerais</option> 
                        <option value="PA">Pará</option> 
                        <option value="PB">Paraíba</option> 
                        <option value="PR">Paraná</option> 
                        <option value="PE">Pernambuco</option> 
                        <option value="PI">Piauí</option> 
                        <option value="RJ">Rio de Janeiro</option> 
                        <option value="RN">Rio Grande do Norte</option> 
                        <option value="RO">Rondônia</option> 
                        <option value="RS">Rio Grande do Sul</option> 
                        <option value="RR">Roraima</option> 
                        <option value="SR">Santa Catarina</option> 
                        <option value="SE">Sergipe</option> 
                        <option value="SP">São Paulo</option> 
                        <option value="TO">Tocantins</option> 
                    </select>
                    -->

                    <div class="invalid-feedback">
                        ${estado!}
                    </div>
                </div>

                <#if cidadeAtual??>
                    <button type="submit" class="btn btn-warning">CONCLUIR ALTERAÇÃO</button>
                <#else>
                    <button type="submit" class="btn btn-primary">CRIAR</button>
                </#if>
                
            </form>
            <table class="table table-striped table-hover mt-5">
                <thead class="thead-dark">
                    <tr>
                        <th>Nome</th>
                        <th>Estado</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <#list listaCidades as cidade >
                        <tr>
                            <td>${cidade.nome}</td>
                            <td>${cidade.estado}</td>
                            <td>
                                <div class="d-flex d-justify-content-center">
                                <a href="/preparaAlterarCidade?nome=${cidade.nome}&estado=${cidade.estado}" class="btn btn-warning mr-3">ALTERAR</a>
                                <a href="/excluirCidade?nome=${cidade.nome}&estado=${cidade.estado}" class="btn btn-danger">EXCLUIR</a>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>


</body>
</html>