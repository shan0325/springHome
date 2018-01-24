import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Album } from './album';

@Injectable()
export class AlbumService {

    private headers: Headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) { }

    listSize: number = 5;
    moreListSize: number = 3;

    getAlbum(): Promise<Album[]> {
        return this.http.get('/album?size=' + this.listSize)
                        .toPromise()
                        .then(response => response.json().content as Album[])
                        .catch(this.handleError);

    }

    getAlbumMore(brdid: number): Promise<Album[]> {
        return this.http.get('/album?size=' + this.moreListSize + '&lastBrdid=' + brdid)
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