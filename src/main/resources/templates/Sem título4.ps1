function CopiarEAtualizarArquivo {
    param (
        [string]$objeto,
        [string]$classe,
        [string]$packge,
        [string]$sufixo,
        [string]$modelo
    )

    $caminhoRef      = (get-item $scriptPath ).parent.FullName+"\java\br\brn\x3Integrator" #'C:\Users\bruno\IdeaProjects\brnApis\brnApis\src\main\java\br'
    $caminhoModelo   =$PSScriptRoot+'\basic'

    $caminhoRefPadrao=$caminhoModelo+'\'+$modelo+$sufixo
    $caminhoDestino  =$caminhoRef+'\'+$packge+'\'+$classe+$sufixo+'.java'

    # Verifique se o arquivo de destino já existe
    if (Test-Path -Path $caminhoDestino -PathType Leaf) {
        Write-Host "O arquivo de destino já existe: $caminhoDestino"
        return
    }

   # Verifique se o arquivo de origem existe
   if (Test-Path -Path $caminhoRefPadrao -PathType Leaf) {
   #    # Leia o conteúdo do arquivo de origem
       $conteudo = Get-Content -Path $caminhoRefPadrao
   
   #    # Substitua os textos desejados
       $conteudo = $conteudo -creplace $modelo          , $classe
       $conteudo = $conteudo -creplace $modelo.ToLower(), $objeto
   #
   #    # Salve o conteúdo no arquivo de destino
       $conteudo | Set-Content -Path $caminhoDestino
   #
       Write-Host "Arquivo copiado e texto substituído com sucesso. Caminho do arquivo de destino: $caminhoDoArquivoDestino"
   } else {
       Write-Host "O arquivo de origem não foi encontrado: $caminhoDoArquivoOrigem"
   }
}

function CriaEstrtura {
    param (
        [string]$class
    )

    $object = $class.ToLower()

    # Chame a função com os parâmetros desejados  
    CopiarEAtualizarArquivo -modelo "Basic" -objeto $object -classe $class -packge "mapper"        -sufixo "Mapper"
    CopiarEAtualizarArquivo -modelo "Basic" -objeto $object -classe $class -packge "bo"            -sufixo "BO"
    CopiarEAtualizarArquivo -modelo "Basic" -objeto $object -classe $class -packge "repository"    -sufixo "Repository"
    CopiarEAtualizarArquivo -modelo "Basic" -objeto $object -classe $class -packge "rest"          -sufixo "Resource"
    CopiarEAtualizarArquivo -modelo "Basic" -objeto $object -classe $class -packge "service\impl"  -sufixo "ServiceImpl"
    CopiarEAtualizarArquivo -modelo "Basic" -objeto $object -classe $class -packge "service"       -sufixo "Service"

}
Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned

$entrada                  = "Customer"

foreach ($classe in $entrada){
    Write-Host $classe
    CriaEstrtura -class $classe
}

# Defina o caminho para o Maven
#$mavenHome = "C:\Users\bruno\.m2\wrapper\dists\apache-maven-3.9.5-bin\2adeog8mj13csp1uusqnc1f2mo\apache-maven-3.9.5"

# Comando Maven com as opções clean e install
$mvnCommand = "clean install"

# Caminho para o diretório do projeto Maven
#$projectDirectory = "C:\Users\bruno\OneDrive\PROJETOS\JAVA\matrix_erp\matrix_erp"

# Execute o Maven usando o IntelliJ IDEA
Start-Process -FilePath "cmd.exe" -ArgumentList "/c $mavenHome\bin\mvn.cmd $mvnCommand" -WorkingDirectory $projectDirectory
