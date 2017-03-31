import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { Router } from '@angular/router';

import { AppComponent }            from './app.component';
import { AppRoutingModule }        from './app-routing.module';


import { ComposeMessageComponent } from './compose-message.component';
import { LoginRoutingModule }      from './login-routing.module';
import { LoginComponent }          from './login.component';
import { PageNotFoundComponent }   from './not-found.component';

import { DialogService }           from './dialog.service';
import {AutomatsModule} from "./automats/automats.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    LoginRoutingModule,
    AppRoutingModule,
    AutomatsModule,
  ],
  declarations: [
    AppComponent,
    ComposeMessageComponent,
    LoginComponent,
    PageNotFoundComponent
  ],
  providers: [
    DialogService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
  // Diagnostic only: inspect router configuration
  constructor(router: Router) {
    console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
  }
}
