import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formataNumeroInteiro'
})
export class FormataNumeroInteiroPipe implements PipeTransform {

  transform(valor: number | string): string {
    if (!valor) {
      return '';
    }

    const valorNumerico = parseFloat(valor.toString());
    return valorNumerico.toLocaleString('pt-BR', { minimumFractionDigits: 0, maximumFractionDigits: 0 });
  }

}
