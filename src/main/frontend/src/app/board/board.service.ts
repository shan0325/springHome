import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Board } from './board';

@Injectable()
export class BoardService {

    private headers: Headers = new Headers({'Content-Type': 'application/json'});
    
    constructor(private http: Http) {}

    getBoard(): Promise<Board[]> {
        return this.http.get('/board')
                    .toPromise()
                    .then(response => response.json().content as Board[])
                    .catch(this.handleError);
    }

    handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}