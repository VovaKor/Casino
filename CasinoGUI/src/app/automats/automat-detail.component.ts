import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { slideInDownAnimation } from '../animations';

import { AutomatService }  from './automat.service';
import { Automat } from "./automat";

@Component({
  template: `
  
  <div *ngIf="automat">
    <h3>"{{ automat.name }}"</h3>
    <div>
        <h4>{{ automat.description }}</h4>
    </div>
      <div *ngIf="automat.isWon">
          <h1>You WON!!!!</h1>
      </div>
    <div *ngFor="let slot of automat.slots">
      <label><span class="badge">{{ slot }}</span></label>
      
    </div>
      <p>
          <button class="banner" (click)="play(automat.id)">Play</button>
      </p>  
    <p>
      <button (click)="gotoAutomats()">Choose another automat</button>
    </p>
  </div>
  `,
  animations: [ slideInDownAnimation ]
})
export class AutomatDetailComponent implements OnInit {
  @HostBinding('@routeAnimation') routeAnimation = true;
  @HostBinding('style.display')   display = 'block';
  @HostBinding('style.position')  position = 'absolute';

  automat: Automat;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: AutomatService
  ) {}

  ngOnInit() {
    this.route.params
      // (+) converts string 'roleId' to a number
      .switchMap((params: Params) => this.service.getAutomat(+params['id']))
      .subscribe((automat: Automat) => this.automat = automat);
  }
  play(id: number){
    this.service.getGameResult(id)
        .subscribe(a => this.automat = a);
  }
  gotoAutomats() {
   this.router.navigate(['']);
  }
}
