import {Component} from '@angular/core';
import { UserService } from './services/index';
import { User } from './models/index';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {

  private navbar = {"users":"Пользователи", "main":"Главная", "login":"Вход", "logout":"Выход"};
  private currentUser: User;


  private userIsLoggedIn(){
    this.currentUser = UserService.getCurentUser();
    if (this.currentUser){
      return true;
    }
    else {
      return false;
    }
  }

}
