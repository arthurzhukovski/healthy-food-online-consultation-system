import { Component, OnInit } from '@angular/core';

import { UserService, AlertService } from '../../services/index';
import { User } from '../../models/index';
import  {GroupSelectComponent} from  '../group-select/index';

@Component({
    moduleId: module.id,
    selector: 'group-assignment',
    templateUrl: 'group-assignment.component.html',
    styleUrls: ['group-assignment.component.scss']
})

export class GroupAssignmentComponent {
    private users: User[];
    private currentUser: User;
    constructor(private userService: UserService, private alertService: AlertService) {
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }
    ngOnInit(){
        this.loadUsers();
    }
    private loadUsers() {
        this.userService.getAll().subscribe(
            data => {
                console.log(data);
                this.users = data;
            },
            error => {
                this.alertService.error(error);
            });
    }
    onSelectNotification(selectedGroup: number, user){
        user.shadowGroup = selectedGroup;
    }
    onGroupSubmit(user){
        var userWithNewGroup = Object.assign({}, user);
        userWithNewGroup.groupId = userWithNewGroup.shadowGroup;
        delete userWithNewGroup.shadowGroup;
        this.applyNewGroup(userWithNewGroup);
    }
    applyNewGroup(userWithNewGroup: User) {
        this.userService.update(userWithNewGroup).subscribe(
            data => {
                this.alertService.success('User updated successfully', true);
                this.loadUsers();
            },
            error => {
                this.alertService.error(error);
            });
    }

}