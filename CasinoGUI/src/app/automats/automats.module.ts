import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { AutomatListComponent }    from './automat-list.component';
import { AutomatDetailComponent }  from './automat-detail.component';

import { AutomatService } from './automat.service';

import {AutomatRoutingModule} from './automats-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AutomatRoutingModule
  ],
  declarations: [
    AutomatListComponent,
    AutomatDetailComponent
  ],
  providers: [ AutomatService ]
})
export class AutomatsModule {}
