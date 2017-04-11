/**
 * Created by sashqua on 4/5/17.
 */

'use strict';

/* Controllers */

var app = angular.module('mycontrollers', ['ngRoute']);

app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

app.controller('LoginController', ['$rootScope', '$scope',  '$location', 'UserService', function ( $rootScope, $scope, $location, UserService) {
    $scope.login = function() {
        UserService
            .authenticate(
                $scope.username,
                $scope.password,
                $scope.remember,
                function(success) {
                    if (success) {
                        $location.path('/');
                        $rootScope.authenticated = true;
                        $rootScope.manager = false;

                        UserService.getUserInfo(function(userInfo) {
                            if (userInfo.data.authorities[0].authority === "ROLE_MANAGER")  $rootScope.manager = true;
                        });
                        console.log("SUCCESS")
                    } else {
                        $rootScope.authenticated = false;
                        $scope.loginError = true;
                        console.log("ERROR")
                    }
                });
    };
}]);

app.controller('RegisterController', ['$scope', '$routeParams', '$location', 'UserFactory',
    function ($scope, $routeParams, $location, UserFactory) {
        $scope.asManager = false;
        $scope.register = function () {
            if ($scope.password != null && $scope.password === $scope.passwordConfirm) {
                var role = {};
                $scope.asManager ?  role = {"id":1,"name":"Manager"} : role = {"id":2,"name":"Developer"};
                console.log("WORKING");
                var user = {"id": 1, "firstName" : $scope.firstName, "lastName" : $scope.lastName, "email" : $scope.email,
                    "password" : $scope.password, "role" : role, "projectList" : null};
                UserFactory.create(user);
                $location.path('/');
            }
        }
    }]);

app.controller('MainController', ['$rootScope', '$scope', '$route', '$location', 'UserService', function ( $rootScope, $scope, $route, $location, UserService ) {
    $rootScope.authenticated = false;

    UserService.getUserInfo(function(userInfo) {
        $rootScope.authenticated = userInfo ? true : false;
    });

    $rootScope.authenticated = true;

    $rootScope.logout = function() {
        UserService.logout(function(success) {
            if (success) {
                console.log("LOGOUT SUCCESS");
                $location.path('/');
                $route.reload();
            } else {
               console.log("LOGOUT ERROR");
            }
       });
    };
}]);

app.controller('ProjectCreateController', ['$rootScope', '$scope',  '$location', 'ProjectsFactory',
    function ( $rootScope, $scope, $location, ProjectsFactory ) {

    $scope.createNewProject = function () {
        var project = {"id": 1, "name" : $scope.name, "description" : $scope.description, "user_id" : null};
        ProjectsFactory.create(project);
        $location.path('/developer/projects');
    }
}]);

app.controller('ProjectsController', ['$scope',  '$route', 'ProjectsFactory',
    function ( $scope, $route, ProjectsFactory ) {
        $scope.projects = ProjectsFactory.query();
}]);

app.controller('ProjectInfoController', ['$scope',  '$routeParams', 'ProjectFactory', 'UsersFactory',
    function ( $scope, $routeParams, ProjectFactory, UsersFactory) {

        $scope.users = UsersFactory.show();
        $scope.project = ProjectFactory.show({id: $routeParams.id});

        $scope.showOptions = false;
        $scope.toggle = function(){
            $scope.showOptions = !$scope.showOptions;
        };

        $scope.selected = [];
        var selectedUsers = [];
        $scope.save = function(){
            for (var i = 0; i < $scope.selected.length; i++) {
                if (isSelected($scope.selected[i])) {
                    selectedUsers.push($scope.users[i]);
                }
            }
            ProjectFactory.update({id: $routeParams.id}, selectedUsers);
        };
        function isSelected(element) {return element;}
}]);

app.controller('TaskCreateController', ['$scope', '$routeParams', '$location', 'TasksFactory', 'StatusFactory',
    function ($scope, $routeParams, $location, TasksFactory, StatusFactory) {

        $scope.statuses = StatusFactory.show();

        $scope.createNewTask = function () {
            var task = {"id": 1, "name" : $scope.name, "description" : $scope.description, "project" : null, "userList":null,"commentList":null, "taskStatus": $scope.statuses[$scope.tstatus-1]};
            TasksFactory.create({id: $routeParams.id}, task);
            $location.path('/developer/project/' +  $routeParams.id);
        }
}]);

app.controller('TaskInfoController', ['$scope',  '$routeParams', 'TaskFactory', 'UsersFactory', 'CommentFactory',
    function ( $scope, $routeParams, TaskFactory, UsersFactory, CommentFactory ) {
        $scope.users = UsersFactory.show();
        $scope.task = TaskFactory.show({id: $routeParams.id});

        $scope.showOptions = false;
        $scope.toggle = function(){
            $scope.showOptions = !$scope.showOptions;
        };

        $scope.selected = [];
        var selectedUsers = [];
        $scope.save = function(){
            for (var i = 0; i < $scope.selected.length; i++) {
                if (isSelected($scope.selected[i])) {
                    selectedUsers.push($scope.users[i]);
                }
            }
            TaskFactory.update({id: $routeParams.id}, selectedUsers);
        };
        function isSelected(element) {return element;}
        $scope.comment = "";
        $scope.addComment = function () {
            console.log("asdasd");
            $scope.task.commentList.push({"id" : 1, "text" : $scope.comment, "owner" : null, "task" : null});
            CommentFactory.add({id: $routeParams.id}, {"id" : null, "text" : $scope.comment, "owner" : null, "task" : null});
            $scope.comment = "";
            console.log($scope.task.commentList);
        };

}]);