import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formataNumeroDecimal'
})
export class FormataNumeroDecimalPipe implements PipeTransform {

  transform(valor: number | string): string {
    if (!valor) {
      return '';
    }

    const valorNumerico = parseFloat(valor.toString());
    return valorNumerico.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
  }

}
