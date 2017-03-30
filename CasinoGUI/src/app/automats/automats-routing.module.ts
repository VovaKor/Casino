import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AutomatListComponent }    from './automat-list.component';
import { AutomatDetailComponent }  from './automat-detail.component';

const automatsRoutes: Routes = [
  { path: '',
    component: AutomatListComponent,
    // children: [
    //
    // ]
  },
    {
        path: ':id',
        component: AutomatDetailComponent
    }
];

@NgModule({
  imports: [
    RouterModule.forChild(automatsRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AutomatRoutingModule { }
