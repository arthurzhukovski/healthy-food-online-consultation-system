import {Component, OnInit, EventEmitter, Output, Input} from '@angular/core';

import { UserService, AlertService, GroupService } from '../../services/index';
import {Group} from "../../models/group";

@Component({
    moduleId: module.id,
    selector: 'group-creator',
    templateUrl: 'group-creator.component.html',
    styleUrls: ['group-creator.component.scss'],
})

export class GroupCreatorComponent {
    private newGroup: Group = new Group;
    @Output() notify: EventEmitter<string> = new EventEmitter<string>();

    constructor(private userService: UserService, private alertService: AlertService, private groupService: GroupService) {
    }
    ngOnInit(){

    }
    onSelectNotification(selectedCoach: number){
        this.newGroup.coach.id = selectedCoach;
        console.log('coach id: ' + selectedCoach);
    }

    createNewGroup(){
        console.log(this.newGroup);
        this.groupService.create(this.newGroup).subscribe(
            data => {
                this.alertService.success('Группа создана', true);
                this.notify.emit('New group has been added to the group list.');
                console.log(this.newGroup);
            },
            error => {
                this.alertService.error(error);
            });
    }
}

