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
    <div>
      <label><span class="badge">{{ automat.slot1 }}</span></label>
      <label><span class="badge">{{ automat.slot2 }}</span></label>
      <label><span class="badge">{{ automat.slot3 }}</span></label>
    </div>
      <p>
          <button class="menu-button" (click)="play()">Play</button>
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
      // (+) converts string 'id' to a number
      .switchMap((params: Params) => this.service.getAutomat(+params['id']))
      .subscribe((automat: Automat) => this.automat = automat);
  }
  play(){

  }
  gotoAutomats() {
    let automatId = this.automat ? this.automat.id : null;
    // Pass along the hero id if available
    // so that the HeroList component can select that hero.
    // Include a junk 'foo' property for fun.
    this.router.navigate(['/automats', automatId]);
  }
}
