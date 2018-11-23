/** 定义控制器层 */
app.controller('userController', function($scope, baseService){

    //定义用户对象
    $scope.user = {};

    //用户注册
    $scope.save = function () {
        if($scope.password && $scope.password == $scope.user.password){
            baseService.sendPost("/user/save?smsCode"+$scope.smsCode,$scope.user)
                .then(function (response) {
                    if(response.data){
                        alert("注册成功!");
                        $scope.user = {};
                        $scope.password = "";
                        $scope.smsCode = "";
                    }else {
                        alert("注册失败!")
                    }

                });
        }else{
            alert("两次密码不一致!")
        }
    }

    //发送短信验证吗到用户手机
    $scope.sendCode = function () {
        if($scope.user.phone && /^1[3|5|7|8|9]\d{9}$/.test($scope.user.phone)){
            baseService.sendGet("/user/sendCode?phone="+$scope.user.phone)
                .then(function (response) {
                    // if(response.data){
                    //     alert("发送成功!");
                    // }else {
                    //     alert("发送失败!");
                    // }
                    alert(response.data?"发送成功!":"发送失败!");
                });
        }else {
            alert("手机号码格式不正确!");
        }
    };

});