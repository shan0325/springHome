import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';
import { Location } from '@angular/common';

import { BoardService } from './board.service';
import { Board } from './board';

declare var $: any; //jquery 사용

@Component({
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  board: Board[] = [];
  lastBrdid: number = 0;
  message: Board = new Board();

  constructor(
    private boardService: BoardService,
    private fb: FormBuilder,
    private location: Location
  ) { }

  ngOnInit() {
    document.getElementById("mainNav").style.display = "none";
    document.getElementById("subNav").style.display = "block";
    window.scrollTo(0,0);

    //처음 페이지 로딩 시 데이터 가져오기
    this.getBoard();

    //message modal show 시
    $('#writeModal').on('shown.bs.modal', function(e) {
      $("#regnm").focus();
    });
    
    //message modal hide 시
    $('#writeModal').on('hide.bs.modal', function(e) {
      $("#messageForm")[0].reset();
    });
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

  onSubmit(form: NgForm): void {
    //console.log('you submitted value:', form);
    console.log("regnm = " + this.message.regnm);

    if(!form.controls.regnm.valid) {
      $("#regnm").focus();
      return;
    } 
    else if(!form.controls.pwd.valid) {
      $("#pwd").focus();
      return;
    }
    else if(!form.controls.contents.valid) {
      $("#contents").focus();
      return;
    }

    if(form.valid) {
      this.boardService.insert(this.message)
          .then(() => this.goBack());
    }    
  }

  goBack(): void {
    this.location.back();
  }


}
