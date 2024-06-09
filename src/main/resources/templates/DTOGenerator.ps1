# Função para verificar se a palavra está na lista especificada
function PalavraEstaNaLista($palavra, $lista) {
    foreach ($item in $lista) {
        if ($palavra.TrimStart() -eq $item) {
            return $true
        }
    }
    return $false
}

function GeraDTO($model){
    $nomeModelRef = $model
    $base="C:\Users\bruno\IdeaProjects\x3Integrator\x3Integrator\src\main\java\br\brn\x3Integrator"
    $orgiem=$base+"\model\"+ $nomeModelRef + ".java"
    $nomeModelRefDTO=$nomeModelRef+"DTO"
    $destino=$base+"\dto\"+ $nomeModelRefDTO+".java"

    if (Test-Path -Path $destino -PathType Leaf) {
        Write-Host "O DTO já existe: $nomeModelRefDTO"
        return
    }


    $classesPadrao = @("Long", "Double", "LocalDate", "LocalDateTime", "String", "Boolean")


    # Ler o conteúdo do arquivo
    $fileContent = Get-Content -Path $orgiem -Raw

    # Dividir o conteúdo do arquivo em linhas
    $lines = $fileContent -split '\r?\n'

    # Variável para armazenar o nome da classe atual
    $className = @()

    # Variável para armazenar as linhas da classe
    $classLines = @()

    # Percorrer cada linha do arquivo
    foreach ($line in $lines) {
        # Verificar se a linha contém a declaração de uma classe
        if ($line -match "private\s+") {
            # Obter o nome da classe atual
            # Dividir o texto em palavras
            $palavras = $line.TrimStart() -split '\s+'
            if ($palavras[1] -ne "static"){
                # Verificar se há pelo menos duas palavras
                if ($palavras.Count -ge 2) {
                    # Se a segunda palavra não estiver na lista de tipos permitidos, adicionar "DTO" após a segunda palavra
                    if (-not (PalavraEstaNaLista $palavras[1] $classesPadrao)) {
                        $palavras[1] += "DTO"
                    }
                    # Reconstruir o texto com as modificações
                    $line = $palavras -join ' '
                }

                $className += $line
            }
        }

        # Adicionar a linha atual às linhas da classe
        $classLines += $line
    }

    # Criar o conteúdo da classe DTO
$dtoContent = "br.brn.x3Integrator.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class $nomeModelRefDTO implements Serializable {
$(foreach ($line in $className) {
"`t$line`n"
})
}"
  
# Salvar o conteúdo da classe DTO em um novo arquivo
$dtoFilePath = $destino
$dtoContent | Out-File -FilePath $dtoFilePath -Encoding utf8

    # Obtém o conteúdo UTF-8 com o BOM
    $utf8WithBom = New-Object System.Text.UTF8Encoding $true

    # Cria o conteúdo do arquivo sem o BOM
    $withoutBOMContent = $utf8WithBom.GetString([Text.Encoding]::UTF8.GetBytes($dtoContent))

    # Salva o conteúdo no arquivo sem o BOM
    [System.IO.File]::WriteAllText($destino, $withoutBOMContent)
}

$entrada = $args
Write-Host $entrada
foreach ($classe in $entrada){
    Write-Host $classe
    GeraDTO -model $classe
}



