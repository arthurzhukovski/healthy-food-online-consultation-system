"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var GroupCreatorComponent = (function () {
    function GroupCreatorComponent(userService, alertService, groupService) {
        this.userService = userService;
        this.alertService = alertService;
        this.groupService = groupService;
        this.notify = new core_1.EventEmitter();
        this.groups = [];
        this.selectedGroupId = 0;
    }
    GroupCreatorComponent.prototype.ngOnInit = function () {
        this.loadActiveGroups();
        console.log(this.selectedGroupId);
    };
    GroupCreatorComponent.prototype.notifyGroupChanged = function () {
        console.log('Selected: ' + this.selectedGroupId);
        this.notify.emit(this.selectedGroupId);
    };
    GroupCreatorComponent.prototype.loadActiveGroups = function () {
        var _this = this;
        this.groupService.getAllByCoachId(1)
            .subscribe(function (groups) {
            console.log('Loaded from service: ' + groups);
            _this.groups = groups;
        });
    };
    __decorate([
        core_1.Output()
    ], GroupCreatorComponent.prototype, "notify", void 0);
    __decorate([
        core_1.Input()
    ], GroupCreatorComponent.prototype, "selectedGroupId", void 0);
    GroupCreatorComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'group-select',
            templateUrl: 'group-creator.component.html',
            styleUrls: ['group-creator.component.scss'],
        })
    ], GroupCreatorComponent);
    return GroupCreatorComponent;
}());
exports.GroupCreatorComponent = GroupCreatorComponent;
