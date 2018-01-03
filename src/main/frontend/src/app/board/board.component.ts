import { Component, OnInit, Input } from '@angular/core';
import { FacebookService, InitParams, LoginResponse } from 'ngx-facebook';
import { DialogService } from 'ng2-bootstrap-modal';

import { BoardWriteComponent } from './boardWrite.component';

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
    private fb: FacebookService,
    private dialogService: DialogService
  ) { }

  ngOnInit() {
    document.getElementById("mainNav").style.display = "none";
    document.getElementById("subNav").style.display = "block";
    window.scrollTo(0,0);

    this.getBoard();
  }

  //board 리스트 가져오기
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

  //글쓰기 버튼 클릭시
  fn_openWriteModal(): void {
    alert(1);
    let disposable = this.dialogService.addDialog(BoardWriteComponent, {
      title:'Confirm title', 
      message:'Confirm message'})
      .subscribe((isConfirmed)=>{
          //We get dialog result
          if(isConfirmed) {
              alert('accepted');
          }
          else {
              alert('declined');
          }
      });
    //We can close dialog calling disposable.unsubscribe();
    //If dialog was not closed manually close it by timeout
    setTimeout(()=>{
        disposable.unsubscribe();
    },10000);
  }

}
