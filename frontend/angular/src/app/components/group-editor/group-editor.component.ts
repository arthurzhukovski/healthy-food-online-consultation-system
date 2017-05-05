import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from "../../models/user";
import {ModalComponent} from "../modal/modal.component";
import {UserService} from "../../services/user/user.service";
import {AlertService} from "../../services/alert/alert.service";
import {GroupService} from "../../services/group/group.service";
import {Group} from "../../models/group";


@Component({
  selector: 'group-editor',
  templateUrl: 'group-editor.component.html',
  styleUrls: ['group-editor.component.scss']
})



export class GroupEditorComponent implements OnInit {
  @ViewChild(ModalComponent)
  public readonly editGroupModal: ModalComponent;
  private groups: Group[] = [];
  private  coaches: User[] = [];
  mode = 'Observable';
  private selectedGroup: Group = new Group();
  constructor (private userService: UserService, private alertService: AlertService, private groupService: GroupService) {

  }

  ngOnInit() {
      this.loadGroups();
      this.loadCoaches();
  }
  loadGroups() {
    this.groupService.getAll()
        .subscribe(
            groups => {this.groups = groups;  console.log(this.groups);},
            error =>  this.alertService.error('Ошибка. ' + error._body));
  }
    onSelectNotification(selectedCoach: number){
        if (this.selectedGroup.coach == null)
            this.selectedGroup.coach = {};
        this.selectedGroup.coach.id = selectedCoach;
        console.log('coach id: ' + selectedCoach);
    }
  applyChangesToGroup(){
    console.log(this.selectedGroup);
    this.groupService.update(this.selectedGroup).subscribe(
        success =>{
          this.alertService.success('Изменения сохранены.');
          this.loadGroups();
          this.editGroupModal.hide();
        },
        error => {
          let msg = (error._body != '')? error._body : error;
                this.alertService.error('Ошибка. ' +msg);
        }
    );
    this.editGroupModal.hide();
  }
  editGroup(group: Group){
    this.selectedGroup = Object.assign({}, group);
    this.editGroupModal.show();
  }
  deleteGroup(id) {
    this.groupService.delete(id).subscribe(
        success => {
          this.alertService.success('Группа удалена.');
          this.loadGroups();
        },
        error => {
          let msg = (error._body != '')? error._body : error;
                this.alertService.error('Ошибка. ' +msg);
        }
    );
  }
    private loadCoaches() {
        this.userService.getCoaches()
            .subscribe(coaches => {
                console.log('Loaded coaches from service: ' + coaches);
                this.coaches = coaches;
            })
    }
}
