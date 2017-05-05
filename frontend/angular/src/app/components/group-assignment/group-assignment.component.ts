import { Component, OnInit } from '@angular/core';

import { UserService, AlertService, GroupService } from '../../services/index';
import { User, Group } from '../../models/index';
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
    private groups: Group[];
    constructor(private userService: UserService, private alertService: AlertService, private groupService: GroupService) {
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }
    ngOnInit(){
        this.loadUsers();
        this.loadActiveGroups();
    }
    private loadUsers() {
        this.userService.getAll().subscribe(
            data => {
                console.log(data);
                this.users = data;
            },
            error => {
                this.alertService.error('Ошибка. ' + error._body);
            });
    }

    private loadActiveGroups() {
    this.groupService.getAll()
        .subscribe(groups => {
            console.log('Loaded from service: ' + groups);
            this.groups = groups;
        })
    }

    onSelectNotification(selectedGroup: number, user){
        user.shadowGroup = selectedGroup;
    }
    onGroupsUpdatedNotification(groupCreatorNotification: string){
        if (groupCreatorNotification)
            this.loadActiveGroups();
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
                this.alertService.error('Ошибка. ' + error._body);
            });
    }

}