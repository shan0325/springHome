import { Component, OnInit, Input } from '@angular/core';
import { FacebookService, InitParams, LoginResponse } from 'ngx-facebook';

import { BoardService } from './board.service';
import { Board } from './board';

@Component({
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  board: Board[] = [];
  lastBrdid: number = 0;

  constructor(
    private boardService: BoardService,
    private fb: FacebookService
  ) {
     
    let initParams: InitParams = {
      appId: '1452331958145067',
      xfbml: true,
      version: 'v2.8'
    };
 
    fb.init(initParams);

    this.fb.login()
    .then((response: LoginResponse) => console.log(response))
    .catch((error: any) => console.error(error));
  }

  ngOnInit() {
    document.getElementById("mainNav").style.display = "none";
    document.getElementById("subNav").style.display = "block";
    window.scrollTo(0,0);

    this.getBoard();
  }

  getBoard(): void {
    this.boardService.getBoard().then(board => this.board = board);
  }

  //더보기 버튼 클릭 시
  fn_moreClick(): void {
    this.board.forEach(element => {
      this.lastBrdid = element.brdid;
    });

    this.boardService.getBoardMore(this.lastBrdid).then(board => {
      if(board.length == 0) {
        alert("내용이 없습니다.");
        return;
      }

      board.forEach(element => {
        this.board.push(element);
      });
    });
  }

  

}
