import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { Ng2PageScrollModule } from 'ng2-page-scroll';
import { FacebookModule } from 'ngx-facebook';
import { BootstrapModalModule } from 'ng2-bootstrap-modal';

import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { BoardComponent } from './board/board.component';
import { BoardWriteComponent } from './board/board-write.component';
import { AlbumComponent } from './album/album.component';

import { BoardService } from './board/board.service';


@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    Ng2PageScrollModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    FacebookModule.forRoot(),
    BootstrapModalModule,
    BootstrapModalModule.forRoot({container:document.body})
  ],
  declarations: [
    AppComponent,
    MainComponent,
    BoardComponent,
    BoardWriteComponent,
    AlbumComponent
  ],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy},
    BoardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
