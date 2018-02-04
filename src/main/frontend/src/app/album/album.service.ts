import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Album } from './album';
import { Category } from '../category/category';

@Injectable()
export class AlbumService {

    private headers: Headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) { }

    listSize: number = 5;
    moreListSize: number = 3;

    getAlbum(categorycd: number): Promise<Album[]> {
        return this.http.get('/album?categorycd=' + categorycd + '&size=' + this.listSize)
                        .toPromise()
                        .then(response => response.json().content as Album[])
                        .catch(this.handleError);

    }

    getAlbumMore(brdid: number, categorycd: number): Promise<Album[]> {
        return this.http.get('/album?size=' + this.moreListSize + '&lastBrdid=' + brdid + "&categorycd=" + categorycd)
                        .toPromise()
                        .then(response => response.json().content as Album[])
                        .catch(this.handleError);
    }

    getCategorys(): Promise<Category[]> {
        return this.http.get('/categorys/5')
                        .toPromise()
                        .then(response => response.json() as Category[])
                        .catch(this.handleError)
    }

    getCategoryAlbum(categorycd): Promise<Category[]> {
        return this.http.get('/album?categorycd=' + categorycd + '&size=' + this.listSize)
                        .toPromise()
                        .then(response => response.json().content as Album[])
                        .catch(this.handleError);
    }

    handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        let bodyObj = JSON.parse(error._body);
        alert(bodyObj.message);

        return Promise.reject(error.message || error);
    }
}