import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formataNumeroMoeda'
})
export class FormataNumeroMoedaPipe implements PipeTransform {

  transform(valor: number | string): string {
    if (!valor) {
      return '';
    }

    const valorNumerico = parseFloat(valor.toString());
    return valorNumerico.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }
}
