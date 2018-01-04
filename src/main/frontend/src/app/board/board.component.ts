import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';

import { BoardService } from './board.service';
import { Board } from './board';

declare var $: any; //jquery 사용

@Component({
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  messageForm: FormGroup;
  board: Board[] = [];
  lastBrdid: number = 0;

  constructor(
    private boardService: BoardService,
    private fb: FormBuilder
  ) {
    this.messageForm = this.fb.group({
      regnm:['', Validators.required],
      pwd:['', Validators.required],
      contents:['', Validators.required]
    });
  }

  ngOnInit() {
    document.getElementById("mainNav").style.display = "none";
    document.getElementById("subNav").style.display = "block";
    window.scrollTo(0,0);

    //처음 페이지 로딩 시 데이터 가져오기
    this.getBoard();
    
    //message modal hide 시
    $('#writeModal').on('hide.bs.modal', function (e) {
      // do something...
      $("#messageForm")[0].reset();
    })
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

  //메시지 등록
  fn_saveMessage(): void {

  }

  onSubmit(): void {
    console.log('you submitted value:', this.messageForm);
    alert("valid = " + this.messageForm.valid);
   
  }


}
