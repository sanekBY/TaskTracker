/**
 * Created by sashqua on 4/6/17.
 */

'use strict';

var service = angular.module('myservices', ['ngRoute', 'ngCookies']);

service.service('Session',['$http', function () {
            this.create = function (data) {
                this.id = data.id;
                this.login = data.login;
                this.firstName = data.firstName;
                this.lastName = data.familyName;
                this.email = data.email;
                this.userRoles = [];
                angular.forEach(data.authorities, function (value, key) {
                    this.push(value.name);
                }, this.userRoles);
            };
            this.invalidate = function () {
                this.id = null;
                this.login = null;
                this.firstName = null;
                this.lastName = null;
                this.email = null;
                this.userRoles = null;
            };
            return this;
}]);





service.service(
    'UserService',['$http','$cookies', function($http, $cookies) {

            this.authenticate = function(name, password,
                                         remember, callback) {

                if (!name || !password) {
                    callback(false);
                    return;
                }
                var headers = {
                    authorization : "Basic "
                    + btoa(name + ":" + password)
                };

                console.log(headers);
                $http({
                    method: 'GET',
                    url: 'auth/login',
                    headers: headers
                }).then(function successCallback(response) {
                    if (response.data.username) {
                        if (remember) {
                            createRememberMeCookie(response);
                        }
                        callback(true);
                    } else {
                        callback(false);
                    }
                }, function errorCallback(response) {
                    callback(false);
                });
            };

            this.getUserInfo = function(callback) {
                $http({
                    method: 'GET',
                    url: 'auth/login'
                }).then(function successCallback(response) {
                    if (response.data.username) {
                        callback(response);
                    } else {
                        callback(null);
                    }
                }, function errorCallback(response) {
                    callback(null);
                });
            };

            this.logout = function(callback) {
                console.log("asdasdsd");
                removeRememberMeCookie();
                // return  $http({
                //     method: 'POST',
                //     url: 'auth/logout'
                // });
                $http({
                    method: 'POST',
                    url: 'auth/logout'
                }).then(function successCallback() {
                        callback(true);
                }, function errorCallback() {
                    callback(null);
                });

            };

            var createRememberMeCookie = function(userdetials) {
                var name = userdetials.username;
                var pwd = userdetials.password;
                var expireDate = new Date();
                expireDate.setDate(expireDate.getDate() + 30);

                var cookieValue = btoa(name
                    + ":"
                    + expireDate.getTime().toString()
                    + ":"
                    + md5(name
                        + ":"
                        + expireDate.getTime()
                            .toString() + ":" + pwd
                        + ":"
                        + "DEVELNOTES_REMEMBER_TOKEN"));
                $cookies.put('DEVELNOTES_REMEMBER_ME_COOKIE',
                    cookieValue, {
                        'expires' : expireDate,
                        'path' : '/'
                    });
            };

            var removeRememberMeCookie = function() {
                var expireDate = new Date();
                expireDate.setDate(expireDate.getDate() - 1);
                $cookies.put('DEVELNOTES_REMEMBER_ME_COOKIE',
                    '', {
                        'expires' : expireDate,
                        'path' : '/'
                    });
            };

        } ]);

