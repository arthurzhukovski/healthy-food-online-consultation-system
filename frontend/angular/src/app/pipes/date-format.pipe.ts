import { Injectable } from '@angular/core';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filter'
})
@Injectable()
export class NgForFilter implements PipeTransform {
    transform(items: any[], field : string, value : string): any[] {
        if (!items) return [];
        return items.filter(it => it[field] == value);
    }
}