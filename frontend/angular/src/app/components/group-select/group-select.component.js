"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var GroupSelectComponent = (function () {
    function GroupSelectComponent(userService, alertService, groupService) {
        this.userService = userService;
        this.alertService = alertService;
        this.groupService = groupService;
        this.notify = new core_1.EventEmitter();
        this.groups = [];
        this.selectedGroupId = 0;
    }
    GroupSelectComponent.prototype.ngOnInit = function () {
        this.loadActiveGroups();
        console.log(this.selectedGroupId);
    };
    GroupSelectComponent.prototype.notifyGroupChanged = function () {
        console.log('Selected: ' + this.selectedGroupId);
        this.notify.emit(this.selectedGroupId);
    };
    GroupSelectComponent.prototype.loadActiveGroups = function () {
        var _this = this;
        this.groupService.getAllByCoachId(1)
            .subscribe(function (groups) {
            console.log('Loaded from service: ' + groups);
            _this.groups = groups;
        });
    };
    __decorate([
        core_1.Output()
    ], GroupSelectComponent.prototype, "notify", void 0);
    __decorate([
        core_1.Input()
    ], GroupSelectComponent.prototype, "selectedGroupId", void 0);
    GroupSelectComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'group-select',
            templateUrl: 'group-select.component.html',
            styleUrls: ['group-select.component.scss'],
        })
    ], GroupSelectComponent);
    return GroupSelectComponent;
}());
exports.GroupSelectComponent = GroupSelectComponent;
