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
                        console.log("SUCCESS")
                    } else {
                        $rootScope.authenticated = false;
                        $scope.loginError = true;
                        console.log("ERROR")
                    }
                });
    };
}]);

app.controller('MainController', ['$rootScope', '$scope',  '$route', 'UserService', function ( $rootScope, $scope, $route, UserService ) {
    $rootScope.authenticated = false;

    UserService.getUserInfo(function(userInfo) {
        $rootScope.authenticated = userInfo ? true : false;
    });

    $rootScope.authenticated = true;

    $rootScope.logout = function() {
        UserService.logout(function(success) {
            if (success) {
                console.log("LOGOUT SUCCESS");
                $route.reload();
            } else {
               console.log("LOGOUT ERROR");
            }
       });
    };
}]);