import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'offerPipe'})
export class OfferPipe implements PipeTransform {
  transform(value: string): string {
    if (value === 'ORDERED') {
      return 'Naručena';
    } else if (value === 'ACCEPTED') {
      return 'U pripremi';
    } else if (value === 'DELIVERING') {
      return 'U dostavi';
    } else if (value === 'PAID') {
      return 'Plaćeno';
    } else if (value === 'NOT_PAID') {
      return 'Nije plaćeno';
    } else {
      return 'Nedefinirano';
    }
  }

}
