import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {User} from "../../models/user";
import {AlertService} from "../alert/alert.service";
import {UserService} from "../user/user.service";

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router, private alertService: AlertService, private userService: UserService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        this.refreshLocalStorage();
        if ( this.userIsLoggedIn() ) {
            return true;
        }

        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
        return false;
    }

    public userIsLoggedIn(): boolean{

        if (localStorage.getItem('currentUser')){
            return true;
        }
        else{
            return false;
        }
    }

    refreshLocalStorage(){

        var currentUser: User = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser == null)
            return;
        var currentUser: User = JSON.parse(localStorage.getItem('currentUser'));
        this.userService.getById(currentUser.id).subscribe(
            data =>{
                if (currentUser.role != data.role)
                    this.router.navigate(['']);
                localStorage.setItem('currentUser', JSON.stringify(data));
                currentUser = JSON.parse(localStorage.getItem("currentUser"));
            },
            error => {
                this.alertService.error('Не удалось загрузить информацию о пользователе. ' + error);
                localStorage.removeItem('currentUser');
                this.router.navigate(['']);
            });
    }

}