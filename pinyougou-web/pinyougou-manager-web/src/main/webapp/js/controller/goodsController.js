/** 定义控制器层 */
app.controller('goodsController', function($scope, $controller, baseService){

    /** 指定继承baseController */
    $controller('baseController',{$scope:$scope});

    /** 查询条件对象 */
    $scope.searchEntity = {};
    /** 分页查询(查询条件) */
    $scope.search = function(page, rows){
        baseService.findByPage("/goods/findByPage", page,
			rows, $scope.searchEntity)
            .then(function(response){
                /** 获取分页查询结果 */
                $scope.dataList = response.data.rows;
                /** 更新分页总记录数 */
                $scope.paginationConf.totalItems = response.data.total;
            });
    };


    /** 添加或修改 */
    $scope.saveOrUpdate = function(){
        var url = "save";
        if ($scope.entity.id){
            url = "update";
        }
        /** 发送post请求 */
        baseService.sendPost("/goods/" + url, $scope.entity)
            .then(function(response){
                if (response.data){
                    /** 重新加载数据 */
                    $scope.reload();
                }else{
                    alert("操作失败！");
                }
            });
    };

    /** 显示修改 */
    $scope.show = function(entity){
       /** 把json对象转化成一个新的json对象 */
       $scope.entity = JSON.parse(JSON.stringify(entity));
    };

    /** 批量删除 */
    $scope.delete = function(){
        if ($scope.ids.length > 0){
            baseService.deleteById("/goods/delete", $scope.ids)
                .then(function(response){
                    if (response.data){
                        /** 重新加载数据 */
                        $scope.reload();
                    }else{
                        alert("删除失败！");
                    }
                });
        }else{
            alert("请选择要删除的记录！");
        }
    };

    /** 定义商品状态数组 */
    $scope.status = [' 未审核',' 已审核',' 审核未通过',' 关闭'];



    //商品审核与驳回
    //1 定义一个ids数组 和 updateSelection方法 (在base.js里)
   
    //
    $scope.updateStatus = function (status) {
        //别忘了判断用户选择的id是否为空
        if($scope.ids.length > 0){
        baseService.sendGet("/goods/updateStatus?ids="+$scope.ids+"&status="+status)
            .then(function (response) {
                if(response.data){
                    //重新加载数据
                    $scope.reload();
                    //清空ids数组
                    $scope.ids = [];
                }else {
                    alert("操作失败!");
                }
            });
        }else {
            alert("请选择要操作的商品!");
        }
    };

    //逻辑删除
    $scope.updateIsDelete = function (isDelete) {
        if($scope.ids.length > 0){
            baseService.sendGet("/goods/updateIsDelete?ids="+$scope.ids+"&isDelete="+isDelete)
                .then(function (response) {
                    if(response.data){
                        //重新加载数据
                        $scope.reload();
                        //清空ids数组
                        $scope.ids = [];
                    }else {
                        alert("操作失败!");
                    }
                });
        }else {
            alert("请选择要操作的商品!");
        }
    }

});