import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {User} from "../../models/user";
import {AlertService} from "../alert/alert.service";
import {UserService} from "../user/user.service";

@Injectable()
export class AdminAuthGuard implements CanActivate {

    constructor(private router: Router,private userService: UserService, private alertService: AlertService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        this.refreshLocalStorage();
        if ( this.userIsLoggedIn() ) {
            if (this.userHasAdminRights()){
                return true;
            }
            this.router.navigate([''], { queryParams: { returnUrl: state.url }});
            return false;
        }

        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
        return false;
    }

    private userHasAdminRights(): boolean{
        if (this.userIsLoggedIn() ){

            var user: User = JSON.parse(localStorage.getItem('currentUser'));
            if (user.role === 'ADMIN')
                return true;
        }
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