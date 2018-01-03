import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { Ng2PageScrollModule } from 'ng2-page-scroll';
import { FacebookModule } from 'ngx-facebook';
import { BootstrapModalModule } from 'ng2-bootstrap-modal';

import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { BoardComponent } from './board/board.component';
import { BoardWriteComponent } from './board/boardWrite.component';
import { AlbumComponent } from './album/album.component';

import { BoardService } from './board/board.service';


@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    BoardComponent,
    BoardWriteComponent,
    AlbumComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    Ng2PageScrollModule,
    HttpModule,
    FormsModule,
    FacebookModule.forRoot(),
    BootstrapModalModule,
    BootstrapModalModule.forRoot({container:document.body})
  ],
  providers: [
    BoardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
