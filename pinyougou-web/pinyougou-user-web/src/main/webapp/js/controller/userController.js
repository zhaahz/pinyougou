/** 定义控制器层 */
app.controller('userController', function($scope, baseService){

    $scope.save = function () {
        if($scope.password && $scope.password == $scope.user.password){
            baseService.sendPost("/user/save",$scope.user)
                .then(function (response) {
                    if(response.data){
                        alert("注册成功!")
                        $scope.user = {};
                        $scope.password = "";
                    }else {
                        alert("注册失败!")
                    }

                });
        }else{
            alert("两次密码不一致!")
        }
    }
});