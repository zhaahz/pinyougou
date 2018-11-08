app.controller("baseController",function ($scope) {
    


    //定义分页指令需要配置的信息对象
    $scope.paginationConf = {
        currentPage : 1,      //当前页码
        perPageOptions : [10,15,20], //页码下拉列表框需要数据
        itemsPerPage : 10,       //每页显示的记录数
        totalItems : 0,        //总记录数
        onChange :function () {//当页码发生改变时,需要调用的函数

            $scope.reload();//重新加载

            //$scope.paginationConf.currentPage

        }
    };

    $scope.reload=function () {

        $scope.search($scope.paginationConf.currentPage,
            $scope.paginationConf.itemsPerPage);
    };




    //定义一个ids数组
    $scope.ids = [];

    //为复选框绑定点击事件
    $scope.updateSelection=function ($event,id) {

        //点击选中,添加到数组
        if($event.target.checked){
            $scope.ids.push(id);
        }else {
            //没被选中,从数组中移除(防止点击又取消操作)
            var idx = $scope.ids.indexOf(id);
            $scope.ids.splice(idx,1);
        }
    };

    /** 提取数组中json某个属性，返回拼接的字符串(逗号分隔) */
    $scope.jsonArr2Str = function(jsonArrStr, key){
        // 把jsonArrStr转化成JSON数组对象
        var jsonArr = JSON.parse(jsonArrStr);
        // 定义新数组
        var resArr = [];
        // 迭代json数组
        for (var i = 0; i < jsonArr.length; i++){
            // 取数组中的一个元素
            var json = jsonArr[i];
            // 把json对象的值添加到新数组
            resArr.push(json[key]);
        }
        // 返回数组中的元素用逗号分隔的字符串
        return resArr.join(",");
    };


});


