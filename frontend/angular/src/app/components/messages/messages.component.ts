import {Component, OnInit, ViewChild} from '@angular/core';

import { UserService, AlertService } from '../../services/index';
import { User, Message } from '../../models/index';
import {MessageService} from "../../services/message/message.service";
import {ModalComponent} from "../modal/modal.component";

@Component({
    moduleId: module.id,
    selector: 'messages',
    templateUrl: 'messages.component.html',
    styleUrls: ['messages.component.scss']
})

export class MessagesComponent {
    @ViewChild(ModalComponent)
    public readonly modal: ModalComponent;

    private currentUser: User;
    private receivers: User[] = []
    private newMessage: Message = new Message();
    private incomingMessages: any;
    private outgoingMessages: any;
    constructor(private userService: UserService, private alertService: AlertService, private messageService: MessageService) {
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));

        this.newMessage.sender.id = this.currentUser.id;
    }
    ngOnInit(){
        this.loadIncomingMessages();
        this.loadOutgoingMessages();
        this.loadUsers();
    }
    private loadIncomingMessages() {
        this.messageService.getIncoming(this.currentUser.id).subscribe(
            data => {
                console.log(data);
                this.incomingMessages = data;
            },
            error => {
                this.alertService.error(error);
            });
    }
    private loadOutgoingMessages() {
        this.messageService.getOutgoing(this.currentUser.id).subscribe(
            data => {
                console.log(data);
                this.outgoingMessages = data;

            },
            error => {
                this.alertService.error(error);
            });
    }
    onSelectNotification(selectedReceiver: number){
        this.newMessage.receiver.id = selectedReceiver;
        console.log('receiver id: ' + selectedReceiver);
    }

    private sendMessage(){
        this.messageService.create(this.newMessage).subscribe(
            data => {
                this.loadOutgoingMessages();
                this.alertService.success('Сообщение отправлено.');
                this.modal.hide();
            },
            error => {
                this.alertService.error(error);
            });
    }
    private loadUsers() {
        this.userService.getAll()
            .subscribe(users => {
                console.log('Loaded from service: ' + users);
                this.receivers = users;
            })
    }

}