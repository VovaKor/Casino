/**
 * Created by Вова on 31.03.2017.
 */
import { Component, OnInit, Input, Output, OnChanges, EventEmitter } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
    selector: 'app-dialog',
    template: `        
    <div [@dialog] *ngIf="visible" class="dialog">
    <ng-content></ng-content>
    </div>
    <div *ngIf="visible" class="overlay"></div>
    `,
    styleUrls: ['app/roles/popup2add.component.css'],
    animations: [
        trigger('dialog', [
            transition('void => *', [
                style({ transform: 'scale3d(.3, .3, .3)' }),
                animate(100)
            ]),
            transition('* => void', [
                animate(100, style({ transform: 'scale3d(.0, .0, .0)' }))
            ])
        ])
    ]
})
export class Popup2AddComponent implements OnInit {
    @Input() visible: boolean;
    @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();

    constructor() { }

    ngOnInit() { }

    close() {
        this.visible = false;
        this.visibleChange.emit(this.visible);
    }
}