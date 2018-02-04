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
  categorycd: number = 0;
  message: Board = new Board();
  pwdBrdid: number;
  flag: string;

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

    $('#pwdForm').on('shown.bs.modal', function(e) {
      $("#checkPwd").focus();
    });

    //password modal hide 시
    $('#pwdForm').on('hide.bs.modal', function(e) {
      $("#pwdForm")[0].reset();
    });
  }

  //board 리스트 가져오기
  getBoard(): void {
    this.boardService.getBoard(this.categorycd).then(board => {
      board.map(brd => {
        brd.contents = brd.contents.replace(/\n/g, "<br/>");
      });
      this.board = board
    });
  }

  //board 한건 가져오기
  getBoardOne(brdid: number): Promise<Board> {
    return this.boardService.getBoardOne(brdid).then(board => this.message = board);
  }

  //더보기 버튼 클릭 시
  fn_moreClick(): void {
    this.board.forEach(element => {
      this.lastBrdid = element.brdid;
    });
    
    this.boardService.getBoardMore(this.lastBrdid, this.categorycd).then(board => {
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
      if(this.flag == "INSERT") {
        this.boardService.insert(this.message)
                          .then(() => {
                            window.location.reload();
                            //this.goBack()
                          });
      } else if(this.flag == "UPDATE") {
        this.boardService.update(this.message)
                          .then(() => {
                            window.location.reload();
                          });

      } 
              
    }
  }

  pwdOnSubmit(form: NgForm): void {
    //console.log('you submitted value:', form);
    this.boardService.checkPassword(this.pwdBrdid, this.message)
        .then(result => {
          //비밀번호가 맞을 시
          if(result) {
            if(this.flag == "DELETE") {
              this.boardService.deleteBoard(this.pwdBrdid)
                  .then(() => {
                    window.location.reload();
                  });
            } else if(this.flag == "UPDATE") {
              this.getBoardOne(this.pwdBrdid);
              $('#pwdModal').modal('hide');
              $('#writeModal').modal();
            }
          } else {
            alert("비밀번호가 맞지않습니다.");
            $('#checkPwd').focus();
          }
        });
  }

  fn_insertBtnClick(): void {
    this.flag = "INSERT";
    $("#writeModal").modal();
  }

  fn_deleteBtnClick(brdid: number): void {
    this.pwdBrdid = brdid;
    this.flag = "DELETE";
    $('#pwdModal').modal();
  }

  fn_updateBtnClick(brdid: number): void {
    this.pwdBrdid = brdid;
    this.flag = "UPDATE";
    $('#pwdModal').modal();
  }

  fn_commentBtnClick(brdid: number): void {
    this.getBoardOne(brdid).then(board => {
      this.message.contents = board.contents.replace(/\n/g, '<br/>');
    });
    $('#commentModal').modal();
  }

  goBack(): void {
    this.location.back();
  }
  
}
