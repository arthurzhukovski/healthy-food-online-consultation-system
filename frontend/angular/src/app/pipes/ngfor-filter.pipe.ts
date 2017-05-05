import { Injectable } from '@angular/core';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filter'
})
@Injectable()
export class NgForFilter implements PipeTransform {
    transform(items: any[], field : any, value : string): any[] {
        if (!items) return [];
        if (field.includes('.')){
            let fields = field.split('.');
            console.log('Splitted fields: '+fields);

            console.log('Original field: '+field);
            return items.filter(it => it[fields[0]][fields[1]]  == value);

        }
        else {
            console.log('Non-splitted field: '+field);

            return items.filter(it => it[field] == value);
        }
    }
}