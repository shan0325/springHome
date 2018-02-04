import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Board } from './board';

@Injectable()
export class BoardService {

    private headers: Headers = new Headers({'Content-Type': 'application/json'});
    
    constructor(private http: Http) {}

    listSize: number = 5;
    moreListSize: number = 3;

    getBoard(categorycd: number): Promise<Board[]> {
        return this.http.get('/board?categorycd=' + categorycd + '&size=' + this.listSize)
                    .toPromise()
                    .then(response => response.json().content as Board[])
                    .catch(this.handleError);
    }

    getBoardMore(brdid: number, categorycd: number): Promise<Board[]> {
        return this.http.get('/board?size=' + this.moreListSize + '&lastBrdid=' + brdid + "&categorycd=" + categorycd)
                    .toPromise()
                    .then(response => response.json().content as Board[])
                    .catch(this.handleError);
    }

    getBoardOne(brdid: number): Promise<Board> {
        return this.http.get('/board/' + brdid)
                        .toPromise()
                        .then(response => response.json() as Board)
                        .catch();
    }

    insert(board: Board): Promise<Board> {
        return this.http.post("/board", JSON.stringify(board), {headers: this.headers})
                        .toPromise()
                        .then(response => response.json().content as Board)
                        .catch(this.handleError);
    }

    checkPassword(brdid: number, board: Board): Promise<boolean> {
        return this.http.post("/board/" + brdid + "/checkPwd", JSON.stringify(board), {headers: this.headers})
                        .toPromise()
                        .then(response => response.json() as boolean)
                        .catch(this.handleError);
    }

    deleteBoard(brdid: number): Promise<Response> {
        return this.http.delete("/board/" + brdid)
                    .toPromise()
                    .catch(this.handleError);
    }

    update(board: Board): Promise<Board> {
        return this.http.put("/board/" + board.brdid, JSON.stringify(board), {headers: this.headers})
                        .toPromise()
                        .then(response => response.json().content as Board)
                        .catch(this.handleError);
    }

    handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        let bodyObj = JSON.parse(error._body);
        alert(bodyObj.message);

        return Promise.reject(error.message || error);
    }
}