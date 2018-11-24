app.controller("indexController", function($scope, baseService) {

    //定义获取用户登录名方法
    $scope.showName = function () {
        baseService.sendGet("/user/showName")
            .then(function (response) {
                $scope.loginName = response.data.loginName;
            });
    };
});