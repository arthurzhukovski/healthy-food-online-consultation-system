import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from "../../models/user";
import {ModalComponent} from "../modal/modal.component";
import {UserService} from "../../services/user/user.service";
import {AlertService} from "../../services/alert/alert.service";
import {Config} from "../../Config";
import {DocumentService} from "../../services/document/document.service";
import * as $ from 'jquery';
import 'rxjs/Rx' ;
@Component({
  selector: 'document',
  templateUrl: 'document.component.html',
  styleUrls: ['document.component.scss']
})



export class DocumentComponent implements OnInit {
  private docType = "pdf";
  private statType = "users";
  constructor (private documentService: DocumentService, private alertService: AlertService) {

  }

  ngOnInit() {  }

  downloadFile(){
    var docUrl = Config.BASE_API_URL+this.statType+'/stat/';
    if (this.docType == "pdfsec"){
      docUrl += 'pdf?encrypt=true';
    }
    else{
      docUrl += this.docType + '?encrypt=false';
    }
    window.open(`${docUrl}&auth=Bearer ${JSON.parse(localStorage.getItem('token'))}`, '_blank');
  }

}
