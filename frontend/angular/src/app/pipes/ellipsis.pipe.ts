import { Pipe } from '@angular/core';

@Pipe({
    name: 'ellipsis'
})
export class EllipsisPipe {
    transform(val, length) {
        if (length === undefined) {
            return val;
        }

        if (val.length > length) {
            return val.substring(0, length) + '...';
        } else {
            return val;
        }
    }
}